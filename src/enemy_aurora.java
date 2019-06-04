import java.awt.Image;
import java.util.SplittableRandom;

public class enemy_aurora extends enemyship
{
	private game g = new game();
	private gameevents ge = new gameevents();
	private Images images = new Images();
	private double cannon_angle = 90;
	
	public enemy_aurora(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init();
		damage = 15;
		hp = 180;
		es_class = 3;
	}
	
	protected void Init()
	{
		int xywh_spaceship[] = {910, 215, 75, 47};
		img = Images.GetImage("res/SCShmup_texture_3.png", 140, r_angle, xywh_spaceship);
	}
	
	public void Shoot()
	{	
		Init();
		
		double perfectangle = ge.GetClosestAngle(this, g.GetObject(1).GetX(), g.GetObject(1).GetY(), 5);
		
		if (cannon_angle > perfectangle)
			cannon_angle--;
		
		if (cannon_angle < perfectangle)
			cannon_angle++;
		
		int xywh_cannon[] = {985, 337, 35, 25};
		Image cannon = Images.GetImage("res/SCShmup_texture_3.png", 100, cannon_angle, xywh_cannon);
		img = images.MergePictures(img, cannon, img.getWidth(null) / 2 - 18, img.getHeight(null) / 2 - 18);
		
	    double degrees = (double)cannon_angle + 5;
	    double radians = Math.toRadians(degrees);
		double x = 45 * Math.cos(radians) + pos_x + img.getWidth(null) / 2;
		double y = 45 * Math.sin(radians) + pos_y + img.getHeight(null) / 2;
		
		int randomchance = new SplittableRandom().nextInt(1, 12);
		
		if (randomchance == 11)
		{
			int randomspread = new SplittableRandom().nextInt(-3, 3);
			lasershot ls = new lasershot(2, (int)x + randomspread, (int)y + randomspread, 180 + cannon_angle);
			ls.SetFiredBy(1);
			ls.SetDamage(damage);
			g.AddObject(ls);
		}
	}
}
