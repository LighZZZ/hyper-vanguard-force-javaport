import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class input implements MouseListener, MouseMotionListener, KeyListener
{
	private static java.awt.Point mousepos = new java.awt.Point();
	private static java.awt.Point myshippos_old = new java.awt.Point(250, 600);
	private static java.awt.Point myshippos = new java.awt.Point(250, 600);
	private static boolean mouse1_pressed = false;
	private static boolean key_pressed = false;
	
	public input(){}
	
	public java.awt.Point CalcShipPos()
	{	
		int midshippos_x = (int)(30 * 1.4);
		int midshippos_y = (int)(30 * 1.4);
		
		java.awt.Point myshippos_new = new java.awt.Point(mousepos.x - midshippos_x, mousepos.y - midshippos_y);
		java.awt.Point shippos_downunder = new java.awt.Point(mousepos.x + midshippos_x, mousepos.y + midshippos_y);
		
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
	
	public java.awt.Point GetMyShipPos()
	{
		return myshippos;
	}
	
	public boolean GetMousePressed()
	{
		return mouse1_pressed;
	}
	
	public boolean GetKeyPressed()
	{
		return key_pressed;
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		mousepos = new java.awt.Point(arg0.getX(), arg0.getY());
		myshippos = CalcShipPos();
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		mousepos = new java.awt.Point(arg0.getX(), arg0.getY());
		myshippos = CalcShipPos();
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		mouse1_pressed = true;
		mousepos = new java.awt.Point(arg0.getX(), arg0.getY());
		myshippos = CalcShipPos();
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		mouse1_pressed = false;
		mousepos = new java.awt.Point(arg0.getX(), arg0.getY());
		myshippos = CalcShipPos();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		key_pressed = true;
	}
	
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		key_pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent e){}
}
