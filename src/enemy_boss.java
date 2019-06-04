import java.awt.Image;
import java.util.SplittableRandom;

public class enemy_boss extends enemyship
{
	private game g = new game();
	private gameevents ge = new gameevents();
	private Images images = new Images();
	private double cannon_angle = 90;
	
	public enemy_boss(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init();
		damage = 20;
		hp = 1500;
		es_class = 5;
	}
	
	protected void Init()
	{
		int xywh_spaceship[] = {910, 193, 80, 63};
		img = Images.GetImage("res/SCShmup_texture_2.png", 200, r_angle, xywh_spaceship);
	}
	
	public void Shoot()
	{	
		Init();
		
		double perfectangle = ge.GetClosestAngle(this, g.GetObject(1).GetX(), g.GetObject(1).GetY(), 5);
		
		if (cannon_angle > perfectangle)
			cannon_angle = cannon_angle - 3;
		
		if (cannon_angle < perfectangle)
			cannon_angle = cannon_angle + 3;
		
		int xywh_cannon[] = {990, 128, 30, 30};
		Image cannon = Images.GetImage("res/SCShmup_texture_2.png", 150, cannon_angle, xywh_cannon);
		img = images.MergePictures(img, cannon, img.getWidth(null) / 2 - 22, img.getHeight(null) / 2 - 22);
		
	    double degrees = (double)cannon_angle + 5;
	    double radians = Math.toRadians(degrees);
		double x = 45 * Math.cos(radians) + pos_x + img.getWidth(null) / 2;
		double y = 45 * Math.sin(radians) + pos_y + img.getHeight(null) / 2;
		
		int randomchance = new SplittableRandom().nextInt(1, 6);
		
		if (randomchance == 5)
		{
			for (int i = 0; i < 2; i++)
			{
				int randomspread = new SplittableRandom().nextInt(-10, 10);
				lasershot ls = new lasershot(2, (int)x + randomspread * -i, (int)y + randomspread * -i, 180 + cannon_angle);
				ls.SetFiredBy(1);
				ls.SetDamage(damage);
				g.AddObject(ls);
			}
		}
	}
}
