import java.awt.Image;
import java.util.SplittableRandom;

public class myship extends gameobject
{	
	private int hp = 100;
	private int sp = 100;
	private int damage = 20;
	private boolean shieldactivated = true;
	private Images images = new Images();
	private game g = new game();
	
	public myship(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init(r_angle);
		layer = 1;
		go_class = 2;
	}

	private void Init(double r_angle)
	{
		int xywh_spaceship[] = {943, 0, 60, 60};
		img = Images.GetImage("res/SCShmup_texture_3.png", 140, r_angle, xywh_spaceship);
	}
	
	public void Animate(boolean isshooting)
	{
		int randomspread_x = new SplittableRandom().nextInt(-1, 1);
		int randomspread_y = new SplittableRandom().nextInt(-2, 3);
		int randomnumber_middleemission = new SplittableRandom().nextInt(2, 4);
		int randomnumber_smallemission = new SplittableRandom().nextInt(2, 8);
		
		int xywh_spaceship[] = {943, 0, 60, 60};
		Image spaceship = Images.GetImage("res/SCShmup_texture_3.png", 140, 0, xywh_spaceship);
		int xywh_bigemission[] = {846, 1013, 6, 10};
		Image bigemission = Images.GetImage("res/SCShmup_texture_1.png", 92, 270, xywh_bigemission);
		int xywh_middleemission[] = {844, 1013, 5, 7};
		Image middleemission = Images.GetImage("res/SCShmup_texture_1.png", 85, 270, xywh_middleemission);
		int xywh_smallemission[] = {843, 1013, 5, 7};
		Image smallemission = Images.GetImage("res/SCShmup_texture_1.png", 50, 270, xywh_smallemission);
		
		Image newimage = images.MergePictures(spaceship, bigemission, 49, 82);
		newimage = images.MergePictures(newimage, bigemission, 26, 82);
		
		for (int i = 1; i <= randomnumber_middleemission; i++)
		{
			if (i % 2 == 0)
				newimage = images.MergePictures(newimage, middleemission, 27 + randomspread_x, 89 + randomspread_y);
			else
				newimage = images.MergePictures(newimage, middleemission, 49 + randomspread_x, 89 + randomspread_y);
		}
		
		for (int i = 1; i <= randomnumber_smallemission; i++)
		{
			if (i % 2 == 0)
				newimage = images.MergePictures(newimage, smallemission, 51 + randomspread_x, 94 + randomspread_y);
			else
				newimage = images.MergePictures(newimage, smallemission, 30 + randomspread_x, 94 + randomspread_y);
		}
		
		shieldactivated = (!isshooting && sp > 0);
		
		if (isshooting)
		{
			int xywh_laserfire[] = {172, 630, 24, 26};
			Image laserfire = Images.GetImage("res/SCShmup_texture_3.png", 100, 0, xywh_laserfire);
			newimage = images.MergePictures(newimage, laserfire, 32, -26);
		}
		
		if (shieldactivated)
		{
			int xywh_shield[] = {894, 330, 63, 63};
			Image shield  = Images.GetImage("res/SCShmup_texture_2.png", 150, 0, xywh_shield);
			shield = images.SetImageTransperancy(shield, 25);
			newimage = images.MergePictures(newimage, shield, -6, 0);
		}
		
		img = newimage;
	}
	
	public void ShootMainlaser()
	{
		int randomspread = new SplittableRandom().nextInt(-3, 3);
		lasershot ls = new lasershot(pos_x + (36 + randomspread), pos_y, 270);
		ls.SetFiredBy(0);
		ls.SetDamage(damage);
		g.AddObject(ls);
	}
	
	public int GetHP()
	{
		return hp;
	}
	
	public int GetSP()
	{
		return sp;
	}
	
	public void SetHP(int i)
	{
		hp = i;
	}
	
	public void SetSP(int i)
	{
		sp = i;
	}
	
	public boolean IsShieldActivated()
	{
		return shieldactivated;
	}
}
