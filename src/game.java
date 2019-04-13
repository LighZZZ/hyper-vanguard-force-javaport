import java.awt.Graphics;
import java.awt.MouseInfo;
import java.util.ArrayList;
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
		
		while (true)
		{
			System.out.println(inp.GetMyShipPos());
			
			gameobjects.get(1).move(inp.GetMyShipPos().x, inp.GetMyShipPos().y);
			
			repaint();
			game.SleepDelay(5);
		}
	}
	
	public static void SleepDelay(long milliseconds)
	{
		try 
		{
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < gameobjects.size(); i++)
		{
			g.drawImage(gameobjects.get(i).img, gameobjects.get(i).GetX(), gameobjects.get(i).GetY(), null);
		}
	}
}
