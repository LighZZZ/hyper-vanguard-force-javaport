import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class game extends JPanel implements Runnable
{
	private static ArrayList<gameobject> gameobjects = new ArrayList<gameobject>();	
	private static Images images = new Images();
	private static input inp = new input();
	private static int gamestate = 0;
	private static audiooutput music = null;
	
	public game() {}
	
	public void run()
	{
		while (true)
		{
			if (gamestate == 0 || gamestate == 2 || gamestate == 3)
			{
				switch (gamestate)
				{
					case 0:
						gameobjects.add(new background(1, 0, 0, 0));
						PlayMusic("mainmenu");
						break;
					case 2:
						gameobjects.add(new background(2, 0, 0, 0)); break;
					case 3:
						gameobjects.add(new background(3, 0, 0, 0));
				}
				
				repaint();
				
				while (gamestate != 1)
				{
					if (gamestate == 0)
					{
						if (inp.GetMousePressed() == true || inp.GetKeyPressed() == true)
						{
							gamestate = 1;
						}
					}
					else
					{
						if (inp.GetKeyPressed() == true)
						{
							gamestate = 1;
						}
					}
					
					SleepDelay(1);
				}
			}
			
			else if (gamestate == 1)
			{
				StopMusic();
				PlayMusic("inlevel");
				
				gameevents ge = new gameevents();
				
				gameobjects.add(new background(0, 0, 0, 0));
				gameobjects.add(new myship(250, 600, 0));
				
				int tick[] = new int[2];
				int lasttick[] = new int[2];
				int roundtick = 0;
				
				myship ms = (myship)gameobjects.get(1);
				
				while (gamestate == 1)
				{
					//Code that gets executed as often as possible (1000 times per second)
					
					if (ms.GetHP() <= 0)
						gamestate = 2;
					
					ms.Animate(inp.GetMousePressed());
					
					if (ms.IsShieldActivated())										// Animation 1: Shield
						ms.move(inp.GetMyShipPos().x - 6, inp.GetMyShipPos().y);
					else if (inp.GetMousePressed())									// Animation 2: Laserfire
						ms.move(inp.GetMyShipPos().x, inp.GetMyShipPos().y - 26);
					else															// Animation 3: only emissions
						ms.move(inp.GetMyShipPos().x, inp.GetMyShipPos().y);
					
					ge.ExecuteGameEvent(roundtick);
					HitRegistration();
					
					// Code that gets executed x ticks per second
					
					tick[0] = GetTick(32);
					tick[1] = GetTick(8);
					
					if (tick[1] != lasttick[1])
					{
						ShipCollision();
						RenderExplosions();
						
						lasttick[1] = tick[1];
					}
					
					if (tick[0] != lasttick[0])
					{	
						if (inp.GetMousePressed() == true)
						{
							ms.ShootMainlaser();
						}
						
						ProcessLasershots();
						
						roundtick++;
						
						lasttick[0] = tick[0];
					}
					
					repaint();
					SleepDelay(1);
				}
			}
			
			gameobjects.clear();
		}
	}
	
	private void PlayMusic(String type)
	{
		String path = "res/" + type + ".wav";
		
		try 
		{
			music = new audiooutput(path);
			music.Start();
		} 
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void StopMusic()
	{
		if (music != null)
		{
			music.Stop();
			music = null;
		}
	}
	
  	private void ProcessLasershots()
	{
		for (int i = 0; i < gameobjects.size(); i++)
		{
			if (gameobjects.get(i).GetGOClass() != 3)
				continue;
			
			lasershot ls = (lasershot)gameobjects.get(i);
			
			if (ls.GetType() == 3)
			{
				ls.Persecution();
				if (ls.ShouldBeDeleted())
				{
					gameobjects.remove(i);
					continue;
				}
			}
			
		    double degrees = (double)gameobjects.get(i).GetAngle();
		    
		    if (ls.GetFiredBy() == 1 && ls.GetType() != 3)
		    	degrees = degrees + 180;
		    
		    double radians = Math.toRadians(degrees);
			double x = 30 * Math.cos(radians) + gameobjects.get(i).GetX();
			double y = 30 * Math.sin(radians) + gameobjects.get(i).GetY();
			
			if (ls.GetType() == 3)
			{
				x = 5 * Math.cos(radians) + gameobjects.get(i).GetX();
				y = 5 * Math.sin(radians) + gameobjects.get(i).GetY();
			}
			
			ls.move((int)x, (int)y);
				
			if (ls.GetY() < 0 || ls.GetY() > 800)
				gameobjects.remove(i);
		}
	}
  	
  	private void ShipCollision()
  	{
  		myship ms = (myship)gameobjects.get(1);
  		
  		for (int i = 0; i < gameobjects.size(); i++)
  		{
  			if (gameobjects.get(i).GetGOClass() == 4)
  			{
  				enemyship es = (enemyship)gameobjects.get(i);
  				
  				if (DoGOsIntersect(ms, gameobjects.get(i)))
  				{
  					ms.ProcessDamage(10);
  					es.SetHP(es.GetHP() - 15);
  					
  					int pos_x_exp = gameobjects.get(i).GetX() + gameobjects.get(i).img.getWidth(this) / 2 - 20;
  					int pos_y_exp = gameobjects.get(i).GetY() + gameobjects.get(i).img.getHeight(this) / 2 - 20;
  					
  					explosion exp = new explosion(pos_x_exp, pos_y_exp, 90);
  					exp.SetAnimationstage(2);
  					gameobjects.add(exp);
  					
  					ManageDestruction();
  				}
  			}
  		}
  	}
 	
 	private void HitRegistration()
 	{	
 		for (int i = 0; i < gameobjects.size(); i++)
 		{
			if (gameobjects.get(i).GetGOClass() != 3)
				continue;
			
			lasershot ls = (lasershot)gameobjects.get(i);
 			
 			for (int j = 0; j < gameobjects.size(); j++)
 			{
				if (gameobjects.get(j).GetGOClass() != 2 && gameobjects.get(j).GetGOClass() != 4)
					continue;
				
 				if (DoGOsIntersect(gameobjects.get(i), gameobjects.get(j)))
 				{					
 					if (gameobjects.get(j).GetGOClass() == 2 && ls.GetFiredBy() == 1)
 					{
 						myship ms = (myship)gameobjects.get(j);
 						ms.ProcessDamage(15);
 							
 						ManageDestruction();
 						gameobjects.remove(i); break;
 					}
 							
 					else if (gameobjects.get(j).GetGOClass() == 4 && ls.GetFiredBy() == 0)
 					{
 						enemyship es = (enemyship)gameobjects.get(j);
 						es.SetHP(es.GetHP() - ls.GetDamage());
 							
 						ManageDestruction();
 						gameobjects.remove(i); break;
 					}
 				}
 			}
 		}
 	}
	
	private void ManageDestruction()
	{
		for (int i = 0; i < gameobjects.size(); i++)
		{
			int pos_x = gameobjects.get(i).GetX() + gameobjects.get(i).GetImage().getWidth(this) / 2 - 20;
			int pos_y = gameobjects.get(i).GetY() + gameobjects.get(i).GetImage().getHeight(this) / 2 - 20;
			
			if (gameobjects.get(i).GetGOClass() == 2)
			{
				myship ms = (myship)gameobjects.get(i);
				if (ms.GetHP() <= 0)
				{
					gameobjects.add(new explosion(pos_x, pos_y, 90));
				}
			}
			
			else if (gameobjects.get(i).GetGOClass() == 4)
			{
				enemyship es = (enemyship)gameobjects.get(i);
				if (es.GetHP() <= 0)
				{		
					gameobjects.remove(i);
					gameobjects.add(new explosion(pos_x, pos_y, 90));
				}
			}
		}
	}
	
	private void RenderExplosions()
	{
		for (int i = 0; i < gameobjects.size(); i++)
		{
			if (gameobjects.get(i).GetGOClass() == 5)
			{
				explosion exp = (explosion)gameobjects.get(i);
				
				if (exp.GetAnimationstage() == 3)
					gameobjects.remove(i);
				
				exp.Animate();
			}
		}
	}
	
	private boolean DoGOsIntersect(gameobject go1, gameobject go2)
	{
		Rectangle r_go1 = new Rectangle(go1.GetX(), go1.GetY(), go1.GetImage().getWidth(this), go1.GetImage().getHeight(this));
		Rectangle r_go2 = new Rectangle(go2.GetX(), go2.GetY(), go2.GetImage().getWidth(this), go2.GetImage().getHeight(this));
		
		if (r_go1.intersects(r_go2))
		{
			int max_x = Math.max((int)r_go1.getMaxX(), (int)r_go2.getMaxX());
			int max_y = Math.max((int)r_go1.getMaxY(), (int)r_go2.getMaxY());
			int min_x = Math.min(r_go1.x, r_go2.x);
			int min_y = Math.min(r_go1.y, r_go2.y);
			
			for (int x = min_x; x < max_x; x++)
			{
				for (int y = min_y; y < max_y; y++)
				{
					java.awt.Point p = new java.awt.Point(x, y);
					
					if (r_go1.contains(p) && r_go2.contains(p))
					{
						if (!images.isPixelTransperent(go1, p.x, p.y) && !images.isPixelTransperent(go2, p.x, p.y))
							return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public int GetGamestate()
	{
		return gamestate;
	}
	
	public void SetGamestate(int i)
	{
		gamestate = i;
	}
	
	public void AddObject(gameobject go)
	{
		gameobjects.add(go);
	}
	
	public void RemoveObject(int idx)
	{
		gameobjects.remove(idx);
	}
	
	public gameobject GetObject(int idx)
	{
		return gameobjects.get(idx);
	}
	
	public ArrayList<gameobject> GetGOList()
	{
		return gameobjects;
	}
	
	public static void SleepDelay(long milliseconds)
	{
		try 
		{
			Thread.sleep(milliseconds);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int GetTick(int maxtick)
	{
		long milliseconds = Calendar.getInstance().get(Calendar.MILLISECOND);
		int curtick = 0;
		double curmilliseconds = 0;
		
		for (int i = 1; i <= maxtick; i++)
		{
			curmilliseconds = curmilliseconds + (1000 / maxtick);
			if ((milliseconds - curmilliseconds) < (1000 / maxtick))
			{
				curtick = i;
				break;
			}
		}
		
		return curtick;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for (int i2 = 0; i2 <= 3; i2++)
		{
			for (int i = 0; i < gameobjects.size(); i++)
			{
				if (gameobjects.get(i).GetLayer() == i2)
				{
					g.drawImage(gameobjects.get(i).GetImage(), gameobjects.get(i).GetX(), gameobjects.get(i).GetY(), null);
				}
			}
		}
		
		if (gameobjects.size() > 0 && gamestate == 1)
		{	
			myship ms = (myship)gameobjects.get(1);
			
			g.drawRect(100, 720, 350, 5);							// Ship Stats Outline
			g.drawRect(100, 726, 350, 5);
			
			int bluecolor = (int)((350.0 / 100.0) * ms.GetSP());
			int greencolor = (int)((350.0 / 100.0) * ms.GetHP());
			Color myblue = new Color(51, 51, 255);
			Color mygreen = new Color(51, 204, 51);
			g.setColor(myblue);
			g.fillRect(100, 720, bluecolor, 5);						// Ship Stats Fill
			g.setColor(mygreen);
			g.fillRect(100, 726, greencolor, 5);
		}
	}
}
