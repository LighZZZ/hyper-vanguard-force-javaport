
public class lasershot extends gameobject 
{
	private int firedby = 0;
	private int damage = 0;
	private int type = 0;
	private int ticksexistent = 0;
	private game g = new game();
	private gameevents ge = new gameevents();
	
	public lasershot(int ls_type, int x, int y, double r_angle)
	{
		super(x, y, r_angle);
		Init(ls_type);
		layer = 3;
		go_class = 3;
		type = ls_type;
	}
	
	protected void Init(int type)
	{
		switch (type)
		{
			case 0:
				int xywh_lasershot[] = {735, 1013, 17, 10};
				img = Images.GetImage("res/SCShmup_texture_1.png", 100, r_angle, xywh_lasershot); break;
			case 1:
				int xywh_lasershot2[] = {718, 1013, 17, 10};
				img = Images.GetImage("res/SCShmup_texture_1.png", 100, r_angle, xywh_lasershot2); break;
			case 2:
				int xywh_lasershot3[] = {650, 1013, 17, 10};
				img = Images.GetImage("res/SCShmup_texture_1.png", 110, r_angle, xywh_lasershot3); break;
			case 3:
				int xywh_lasershot4[] = {1012, 260, 10, 10};
				img = Images.GetImage("res/SCShmup_texture_3.png", 140, r_angle, xywh_lasershot4);
		}
	}
	
	public void Persecution()
	{
		ticksexistent++;
		myship ms = (myship)g.GetObject(1);
		double closestangle = ge.GetClosestAngle(this, ms.GetX(), ms.GetY(), 0);
		r_angle = closestangle;
		Init(type);
	}
	
	public int GetFiredBy()
	{
		return firedby;
	}
	
	public void SetFiredBy(int i)
	{
		firedby = i;
	}
	
	public int GetDamage()
	{
		return damage;
	}
	
	public void SetDamage(int i)
	{
		damage = i;
	}
	
	public int GetType()
	{
		return type;
	}
	
	public boolean ShouldBeDeleted()
	{
		if (ticksexistent >= 160)
			return true;
		else
			return false;
	}
}
