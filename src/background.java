
public class background extends gameobject
{
	public background(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init(r_angle);
		layer = 0;
		go_class = 1;
	}

	private void Init(double r_angle)
	{
		int xywh_background[] = {0, 0, 450, 700};
		img = Images.GetImage("res/SCShmup_texture_1.png", 125, r_angle, xywh_background);
	}
}
