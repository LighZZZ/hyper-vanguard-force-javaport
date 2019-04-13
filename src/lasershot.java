
public class lasershot extends gameobject 
{
	public lasershot(int x, int y, double r_angle)
	{
		super(x, y, r_angle);
		Init(r_angle);
		layer = 1;
		go_class = 3;
	}
	
	private void Init(double r_angle)
	{
		int xywh_lasershot[] = {735, 1013, 17, 10};
		img = Images.GetImage("res/SCShmup_texture_1.png", 100, r_angle, xywh_lasershot);
	}
}
