import java.awt.Image;

public class gameobject
{
	protected int pos_x = 0;
	protected int pos_y = 0;
	protected double r_angle = 0;
	protected int layer = 0;
	protected int go_class = 0;
	protected Image img = null;
	private double at_angle = 0;
	
	public gameobject(int x, int y, double angle)
	{
		pos_x = x;
		pos_y = y;
		r_angle = angle;
		at_angle = angle;
		Init();
	}
	
	protected void Init()
	{
		System.out.println(this + " has been created with " + pos_x + "|" + pos_y + " and angle " + r_angle);
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
	
	public void SetAngle(double db)
	{	
		if (db != r_angle)
		{
			r_angle = db;
			
			if (r_angle - at_angle > 0)
			{
				Init();
				at_angle = at_angle - (r_angle - at_angle);
			}
			else
			{
				Init();
				at_angle = r_angle;
			}
		}
	}
	
	public double GetAngle()
	{
		return r_angle;
	}
	
	public Image GetImage()
	{
		return img;
	}
	
	public int GetLayer()
	{
		return layer;
	}
	
	public int GetGOClass()
	{
		return go_class;
	}
}
