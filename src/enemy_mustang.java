
public class enemy_mustang extends enemyship
{
	public enemy_mustang(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init();
		damage = 0;
		hp = 30;
		es_class = 1;
	}
	
	protected void Init()
	{
		int xywh_spaceship[] = {982, 936, 38, 29};
		img = Images.GetImage("res/SCShmup_texture_1.png", 120, r_angle, xywh_spaceship);
	}
}
