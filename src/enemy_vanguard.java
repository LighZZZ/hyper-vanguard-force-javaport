import java.util.SplittableRandom;

public class enemy_vanguard extends enemyship
{
	private game g = new game();
	
	public enemy_vanguard(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init();
		damage = 25;
		hp = 100;
		es_class = 4;
	}
	
	protected void Init()
	{
		int xywh_spaceship[] = {790, 942, 57, 74};
		img = Images.GetImage("res/SCShmup_texture_2.png", 120, r_angle, xywh_spaceship);
	}
	
	public void Shoot()
	{
	    double degrees = (double)r_angle + 120;
	    double radians = Math.toRadians(degrees);
		double x = 30 * Math.cos(radians) + pos_x + img.getWidth(null) / 2;
		double y = 30 * Math.sin(radians) + pos_y + img.getHeight(null) / 2;
		
		int randomchance = new SplittableRandom().nextInt(1, 80);
		if (randomchance == 16)
		{
			lasershot ls = new lasershot(3, (int)x, (int)y,  r_angle);
			ls.SetFiredBy(1);
			ls.SetDamage(damage);
			g.AddObject(ls);
		}
	}
}
