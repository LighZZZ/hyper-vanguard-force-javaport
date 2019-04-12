import java.awt.Graphics;
import java.awt.MouseInfo;
import java.util.ArrayList;
import javax.swing.JPanel;

public class game extends JPanel implements Runnable
{
	static ArrayList<gameobject> gameobjects = new ArrayList<gameobject>();
	private java.awt.Point myshippos_old = new java.awt.Point();
	
	public game() {}
	
	public void run() 
	{
		while (true)
		{
			gameobjects.clear();
			gameobjects.add(new myship(GetMousePos().x, GetMousePos().y, 0));
			repaint();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(GetMousePos().x);
			System.out.println(GetMousePos().y);
		}
	}
	
	public java.awt.Point GetMousePos()
	{
		if (isShowing() == false)
			return myshippos_old;
		
		java.awt.Point mouse_pos = MouseInfo.getPointerInfo().getLocation(); 			//https://stackoverflow.com/questions/12396066/how-to-get-location-of-a-mouse-click-relative-to-a-swing-window
		java.awt.Point app_startloc = getLocationOnScreen();							//https://stackoverflow.com/questions/7950726/find-the-location-position-of-jframe-in-the-window
		java.awt.Point app_endloc = new java.awt.Point(app_startloc.x + 550, app_startloc.y + 800);
		java.awt.Point result_pos = new java.awt.Point();
		
		if (mouse_pos.x > app_endloc.x || mouse_pos.x < app_startloc.x || mouse_pos.y > app_endloc.y || mouse_pos.y < app_startloc.y)
		{
			return myshippos_old;
		}
		else
		{
			result_pos = new java.awt.Point(mouse_pos.x - app_startloc.x, mouse_pos.y - app_startloc.y);
			myshippos_old = result_pos;
			return result_pos;
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
