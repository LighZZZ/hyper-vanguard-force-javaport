
public class myship extends gameobject
{
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
	
	public void ShootMainlaser(int x, int y)
	{
		game g = new game();
		g.AddObject(new lasershot(x, y, 270));
	}
}
