import java.util.SplittableRandom;

public class enemy_herald extends enemyship
{
	private game g = new game();
	
	public enemy_herald(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init();
		damage = 10;
		hp = 80;
		es_class = 2;
	}
	
	protected void Init()
	{
		int xywh_spaceship[] = {273, 974, 49, 44};
		img = Images.GetImage("res/SCShmup_texture_2.png", 120, r_angle, xywh_spaceship);
	}
	
	public void Shoot()
	{
	    double degrees = (double)r_angle + 12;
	    double radians = Math.toRadians(degrees);
		double x = 55 * Math.cos(radians) + pos_x + img.getWidth(null) / 2;
		double y = 55 * Math.sin(radians) + pos_y + img.getHeight(null) / 2;
		
		int randomspread = new SplittableRandom().nextInt(-3, 3);
		lasershot ls = new lasershot(1, (int)x + randomspread, (int)y + randomspread, 180 + r_angle);
		ls.SetFiredBy(1);
		ls.SetDamage(damage);
		g.AddObject(ls);
	}
}
