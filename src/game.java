import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JPanel;

public class game extends JPanel implements Runnable
{
	private static ArrayList<gameobject> gameobjects = new ArrayList<gameobject>();
	
	public game() {}
	
	public void run() 
	{	
		input inp = new input();
		gameobjects.add(new background(0, 0, 0));
		gameobjects.add(new myship(250, 600, 0));
		
		int tick = 0;
		int lasttick = 0;
		
		while (true)
		{
			myship ms = (myship)gameobjects.get(1);
			
			// Code that gets executed as often as possible
			ms.move(inp.GetMyShipPos().x, inp.GetMyShipPos().y);
			
			// Code that gets executed x ticks per second
			tick = GetTick(32);
			
			if (tick != lasttick)
			{
				if (inp.GetMousePressed() == true)
				{
					ms.ShootMainlaser();
				}
				
				for (int i = 0; i < gameobjects.size(); i++)
				{
					if (gameobjects.get(i).go_class == 3)
					{
						gameobjects.get(i).move(gameobjects.get(i).GetX(), gameobjects.get(i).GetY() - 30);
						
						if (gameobjects.get(i).GetY() < 0)
							gameobjects.remove(i);
					}
				}
				
				lasttick = tick;
			}
			
			
			repaint();
			game.SleepDelay(5);
		}
	}
	
	public void AddObject(gameobject go)
	{
		gameobjects.add(go);
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
		
		for (int i2 = 0; i2 <= 2; i2++)
		{
			for (int i = 0; i < gameobjects.size(); i++)
			{
				if (gameobjects.get(i).layer == i2)
				{
					g.drawImage(gameobjects.get(i).img, gameobjects.get(i).GetX(), gameobjects.get(i).GetY(), null);
				}
			}
		}
		
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
