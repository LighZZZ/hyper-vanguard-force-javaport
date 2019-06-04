
public class enemyship extends gameobject
{
	protected int damage = 0;
	protected int hp = 100;
	protected boolean isfiring = false;
	protected boolean canfire = false;
	protected int es_class = 0;
	
	public enemyship(int x, int y, double r_angle) 
	{
		super(x, y, r_angle);
		layer = 1;
		go_class = 4;
	}
	
	public int GetHP()
	{
		return hp;
	}
	
	public void SetHP(int i)
	{
		hp = i;
	}
	
	public int GetESClass()
	{
		return es_class;
	}
}
