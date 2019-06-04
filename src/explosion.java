import java.awt.Image;
import java.util.SplittableRandom;

public class explosion extends gameobject 
{
	private int animationstage = 0;
	private Images images = new Images();

	public explosion(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init();
		layer = 2;
		go_class = 5;
	}
	
	protected void Init()
	{
		int xywh_explosion1[] = {983, 520, 40, 40};
		img = Images.GetImage("res/SCShmup_texture_0.png", 110, r_angle, xywh_explosion1);
	}
	
	public void Animate()
	{
		int randomangle = new SplittableRandom().nextInt(0, 180);
		Image exp = null;
		
		switch (animationstage)
		{	
			case 0:
				int xywh_explosion1[] = {983, 600, 40, 40};
				exp = Images.GetImage("res/SCShmup_texture_0.png", 110, randomangle, xywh_explosion1);
				break;
			case 1:
				int xywh_explosion2[] = {983, 755, 40, 30};
				exp = Images.GetImage("res/SCShmup_texture_0.png", 110, randomangle, xywh_explosion2);
				break;
			case 2:
				int xywh_explosion3[] = {991, 0, 33, 30};
				exp = Images.GetImage("res/SCShmup_texture_2.png", 120, randomangle, xywh_explosion3);
				break;
			case 3:
				int xywh_explosion4[] = {983, 520, 40, 40};
				exp = Images.GetImage("res/SCShmup_texture_0.png", 110, randomangle, xywh_explosion4);
		}
		
		for (int i = 0; i < 2; i++)
		{
			int randomspread_x = new SplittableRandom().nextInt(-5, 5);
			int randomspread_y = new SplittableRandom().nextInt(-5, 5);
			exp = images.MergePictures(exp, exp, randomspread_x, randomspread_y);
		}
		
		img = exp;
		
		animationstage++;
	}
	
	public int GetAnimationstage()
	{
		return animationstage;
	}
	
	public void SetAnimationstage(int i)
	{
		animationstage = i;
	}
}
