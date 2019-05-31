
public class enemy_mustang extends enemyship
{
	public enemy_mustang(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init(r_angle);
		damage = 5;
		hp = 20;
	}
	
	private void Init(double r_angle)
	{
		int xywh_spaceship[] = {964, 936, 60, 29};
		img = Images.GetImage("res/SCShmup_texture_1.png", 120, r_angle, xywh_spaceship);
	}
}
