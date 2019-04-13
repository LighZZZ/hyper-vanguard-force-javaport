import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class input extends JPanel implements Runnable, MouseListener
{
	private static java.awt.Point myshippos_old = new java.awt.Point(250, 600);
	private static java.awt.Point myshippos = new java.awt.Point(250, 600);
	private static boolean mouse1_pressed = false;
	
	public input() 
	{
		addMouseListener(this);
	}
	
	public void run()
	{
		while (true)
		{
			myshippos = CalcShipPos();
			
			game.SleepDelay(5);
		}
	}
	
	public java.awt.Point CalcShipPos()
	{
		int midshippos = (int)(30 * 1.4);
		java.awt.Point mousepos = GetMousePos();
		java.awt.Point myshippos_new = new java.awt.Point(mousepos.x - midshippos, mousepos.y - midshippos);
		java.awt.Point shippos_downunder = new java.awt.Point(mousepos.x + midshippos, mousepos.y + midshippos);
		
		if (myshippos_new.x < 0 || shippos_downunder.x > 550)
		{
			if (myshippos_new.y > 0 && shippos_downunder.y < 800)
			{
				myshippos_old = new java.awt.Point(myshippos_old.x, myshippos_new.y);
			}
			
			return myshippos_old;
		}
		
		else if (myshippos_new.y < 0 || shippos_downunder.y > 800)
		{
			if (myshippos_new.x > 0 && shippos_downunder.x < 550)
			{
				myshippos = new java.awt.Point(myshippos_new.x, myshippos_old.y);
			}
			
			return myshippos_old;
		}
		
		else
		{
			myshippos_old = myshippos_new;
			return myshippos_new;
		}
	}
	
	public java.awt.Point GetMousePos()
	{
		int midshippos = (int)(30 * 1.4);
		
		if (isShowing() == false)
			return new java.awt.Point(myshippos_old.x + midshippos, myshippos_old.y + midshippos);
		
		java.awt.Point mouse_pos = MouseInfo.getPointerInfo().getLocation(); 			//https://stackoverflow.com/questions/12396066/how-to-get-location-of-a-mouse-click-relative-to-a-swing-window
		java.awt.Point app_startloc = getLocationOnScreen();							//https://stackoverflow.com/questions/7950726/find-the-location-position-of-jframe-in-the-window
		java.awt.Point app_endloc = new java.awt.Point(app_startloc.x + 550, app_startloc.y + 800);
		java.awt.Point result_pos = new java.awt.Point();
		
		if (mouse_pos.x > app_endloc.x || mouse_pos.x < app_startloc.x || mouse_pos.y > app_endloc.y || mouse_pos.y < app_startloc.y)
		{
			return new java.awt.Point(myshippos_old.x + midshippos, myshippos_old.y + midshippos);
		}
		else
		{
			result_pos = new java.awt.Point(mouse_pos.x - app_startloc.x, mouse_pos.y - app_startloc.y);
			return result_pos;
		}
	}
	
	public java.awt.Point GetMyShipPos()
	{
		return myshippos;
	}
	
	public boolean GetMousePressed()
	{
		return mouse1_pressed;
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		mouse1_pressed = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		mouse1_pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
}
