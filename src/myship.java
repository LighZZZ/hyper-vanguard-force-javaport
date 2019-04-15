import java.awt.Image;
import java.util.SplittableRandom;

public class myship extends gameobject
{	
	private int hp = 100;
	private int sp = 100;
	
	public myship(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init(r_angle);
		layer = 2;
		go_class = 2;
	}

	private void Init(double r_angle)
	{
		int xywh_spaceship[] = {943, 0, 60, 60};
		img = Images.GetImage("res/SCShmup_texture_3.png", 140, r_angle, xywh_spaceship);
	}
	
	public void ShootMainlaser()
	{
		int randomspread = new SplittableRandom().nextInt(-3, 3);
		game g = new game();
		g.AddObject(new lasershot(pos_x + (36 + randomspread), pos_y, 270));
	}
	
	public int GetHP()
	{
		return hp;
	}
	
	public int GetSP()
	{
		return sp;
	}
}
