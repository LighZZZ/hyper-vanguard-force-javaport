import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SplittableRandom;

import javax.swing.JPanel;

public class game extends JPanel implements Runnable
{
	private static ArrayList<gameobject> gameobjects = new ArrayList<gameobject>();
	
	public game() {}
	
	public void run() 
	{	
		int tick = 0;
		int lasttick = 0;
		input inp = new input();
		gameobjects.add(new background(0, 0, 0));
		gameobjects.add(new myship(250, 600, 0));
		gameobjects.add(new lasershot(250, 300, 270));
		
		while (true)
		{
			// Code that gets executed as often as possible
			gameobjects.get(1).move(inp.GetMyShipPos().x, inp.GetMyShipPos().y);
			
			// Code that gets executed x ticks per second
			tick = GetTick(16);
			
			if (tick != lasttick)
			{
				if (inp.GetMousePressed() == true)
				{
					int randomspread = new SplittableRandom().nextInt(-3, 3);
					gameobjects.add(new lasershot(inp.GetMyShipPos().x + (36 + randomspread), inp.GetMyShipPos().y, 270));
				}
				
				for (int i = 0; i < gameobjects.size(); i++)
				{
					if (gameobjects.get(i).go_class == 3)
					{
						gameobjects.get(i).move(gameobjects.get(i).GetX(), gameobjects.get(i).GetY() - 50);
						
						if (gameobjects.get(i).GetY() < 0)
							gameobjects.remove(i);
					}
				}
				
				lasttick = tick;
			}
			
			if (tick == 16)
				tick = 0;
			
			
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
		
		for (int i = 0; i < 16; i++)
		{
			curmilliseconds = curmilliseconds + (1000 / maxtick);
			if ((milliseconds - curmilliseconds) < Math.round(1000 / maxtick))
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
	}
}
