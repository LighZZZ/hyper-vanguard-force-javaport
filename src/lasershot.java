
public class lasershot extends gameobject 
{
	private int firedby = 0;
	private int damage = 0;
	
	public lasershot(int x, int y, double r_angle)
	{
		super(x, y, r_angle);
		Init(r_angle);
		layer = 2;
		go_class = 3;
	}
	
	private void Init(double r_angle)
	{
		int xywh_lasershot[] = {735, 1013, 17, 10};
		img = Images.GetImage("res/SCShmup_texture_1.png", 100, r_angle, xywh_lasershot);
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
}
