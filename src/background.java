
public class background extends gameobject
{
	public background(int type, int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		Init(type);
		layer = 0;
		go_class = 1;
	}

	protected void Init(int type)
	{
		switch (type)
		{
			case 0:
				int xywh_background[] = {0, 0, 450, 700};
				img = Images.GetImage("res/SCShmup_texture_1.png", 125, r_angle, xywh_background); break;
			case 1:
				int xywh_background2[] = {30, 50, 327, 494};
				img = Images.GetImage("res/mainscreen.png", 175, r_angle, xywh_background2); break;
			case 2:
				int xywh_background3[] = {0, 0, 540, 770};
				img = Images.GetImage("res/lossscreen.png", 100, r_angle, xywh_background3); break;
			case 3:
				int xywh_background4[] = {0, 0, 540, 770};
				img = Images.GetImage("res/winscreen.png", 100, r_angle, xywh_background4);
		}
	}
}
