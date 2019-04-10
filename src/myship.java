public class myship
{
	public myship(int x, int y, double r_angle)
	{
		int xywh_spaceship[] = {943, 0, 60, 60};
		Images.RenderImg("res/SCShmup_texture_3.png", 140, x, y, r_angle, xywh_spaceship);
	}
}
