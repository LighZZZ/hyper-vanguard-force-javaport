import java.awt.Image;

public class gameobject extends game 
{
	protected int pos_x = 0;
	protected int pos_y = 0;
	protected int layer = 0;
	protected int go_class = 0;
	protected Image img = null;
	
	public gameobject(int x, int y, double r_angle)
	{
		pos_x = x;
		pos_y = y;
		Init(r_angle);
	}
	
	private void Init(double r_angle)
	{
		/*System.out.println(this + " has been created with " + pos_x + "|" + pos_y + " and angle " + r_angle);*/
	}
	
	public void move(int x, int y)
	{
		pos_x = x;
		pos_y = y;
	}
	
	public int GetX()
	{
		return pos_x;
	}
	
	public int GetY()
	{
		return pos_y;
	}
}
