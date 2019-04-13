
public class myship extends gameobject
{
	public myship(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init(r_angle);
	}

	private void Init(double r_angle)
	{
		int xywh_spaceship[] = {943, 0, 60, 60};
		img = Images.GetImage("res/SCShmup_texture_3.png", 140, r_angle, xywh_spaceship);
	}
}
