public class gameevents 
{
	private static game g = new game();
	private boolean created = false;
	private int lvlstage = 0;
	private int starttick = -1;
	private int lasttickdifference = 0;
	
	public void ExecuteGameEvent(int roundtick)
	{
		switch(lvlstage)
		{
			case 0:
				MustangCurveConstellation(550, 800, 400, 700, roundtick); break;
			case 1:
				MustangCurveConstellation(0, 800, 340, 800, roundtick); break;
			case 2:
				MustangCurveConstellation(100, 800, 300, 1000, roundtick); break;
			case 3:
				MustangCurveConstellation(250, 800, 300, 600, roundtick); break;
			case 4:
				HeraldConstellation(roundtick); break;
			case 5:
				AuroraConstellation(roundtick); break;
			case 6:
				VanguardCurveConstellation(550, 800, 400, 700, roundtick); break;
			case 7:
				HeraldConstellation(roundtick); break;
			case 8:
				AuroraConstellation(roundtick); break;
			case 9:
				HeraldConstellation(roundtick); break;
			case 10:
				BossFight(roundtick); break;
			case 11:
				g.SetGamestate(3);
		}
	}
	
	private void BossFight(int roundtick)
	{
		if (starttick == -1)
			starttick = roundtick;
		
		if (!created)
		{
			CreateSpaceships(1, "boss");
			created = true;
		}

		int spaceships = 0;
		int tickdifference = (starttick - roundtick) * -1;
		
		for (int i = 0; i < g.GetGOList().size(); i++)
		{	
			if (g.GetObject(i).GetGOClass() == 4)
			{
				enemyship es = (enemyship)g.GetObject(i);
				
				if (es.GetESClass() == 5)
				{
					spaceships++;
					
					int x = 200;
					int y = g.GetObject(i).GetY() - ((starttick - roundtick) % 2);
					
					if (tickdifference >= 18)
					{
						enemy_boss eb = (enemy_boss)g.GetObject(i);
						
						if (lasttickdifference != tickdifference)
						{
							eb.Shoot();
						}
					}
					else
					{
						g.GetObject(i).move(x, y);
					}
				}
			}
			
			if (i == g.GetGOList().size() - 1)
				lasttickdifference = tickdifference;
		}
		
		if (spaceships == 0)
		{
			DeleteSpaceships(5);
			created = false;
			starttick = -1;
			lvlstage++;
		}
	}
	
 	private void VanguardCurveConstellation(int pos_x, int pos_y, int radius_x, int radius_y, int roundtick)
	{
		if (starttick == -1)
			starttick = roundtick;
		
		if (!created)
		{
			CreateSpaceships(4, "vanguard");
			created = true;
		}
		
		int spaceships = 0;
		int tickdifference = (starttick - roundtick) * -1;
		
		for (int i = 0; i < g.GetGOList().size(); i++)
		{
			if (g.GetObject(i).GetGOClass() == 4)
			{
				enemyship es = (enemyship)g.GetObject(i);
				
				if (es.GetESClass() == 4)
				{
					spaceships++;
					
				    double degrees = (starttick - roundtick) + (spaceships * 10);
				    double radians = Math.toRadians(degrees);
					double x = radius_x * Math.cos(radians) + pos_x;
					double y = radius_y * Math.sin(radians) + pos_y;
					
					if (lasttickdifference != tickdifference)
					{
						enemy_vanguard ev = (enemy_vanguard)g.GetObject(i);
						ev.Shoot();
					}
					
					g.GetObject(i).SetAngle(degrees - 90);
					g.GetObject(i).move((int)x, (int)y);
				}
			}
			
			if (i == g.GetGOList().size() - 1)
				lasttickdifference = tickdifference;
		}
		
		if ((starttick - roundtick) + (5 * 7) <= -200)
		{
			DeleteSpaceships(4);
			lvlstage++;
			starttick = -1;
			created = false;
		}
	}
	
	private void AuroraConstellation(int roundtick)
	{
		if (starttick == -1)
			starttick = roundtick;
		
		if (!created)
		{
			CreateSpaceships(3, "aurora");
			created = true;
		}
		
		int spaceships = 0;
		int tickdifference = (starttick - roundtick) * -1;
		
		for (int i = 0; i < g.GetGOList().size(); i++)
		{	
			if (g.GetObject(i).GetGOClass() == 4)
			{
				enemyship es = (enemyship)g.GetObject(i);
				
				if (es.GetESClass() == 3)
				{
					spaceships++;
					
					int x = 130 * spaceships;
					int y = g.GetObject(i).GetY() - ((starttick - roundtick) % 2);
					
					if (tickdifference >= 25)
					{
						enemy_aurora ea = (enemy_aurora)g.GetObject(i);
						
						if (lasttickdifference != tickdifference)
						{
							ea.Shoot();
						}
					}
					else
					{
						g.GetObject(i).move(x, y);
					}
				}
			}
			
			if (i == g.GetGOList().size() - 1)
				lasttickdifference = tickdifference;
		}
		
		if (spaceships == 0)
		{
			DeleteSpaceships(3);
			created = false;
			starttick = -1;
			lvlstage++;
		}
	}
	
	private void HeraldConstellation(int roundtick)
	{
		if (starttick == -1)
			starttick = roundtick;
		
		if (!created)
		{
			CreateSpaceships(2, "herald");
			created = true;
		}
		
		int spaceships = 0;
		int tickdifference = (starttick - roundtick) * -1; 
		
		for (int i = 0; i < g.GetGOList().size(); i++)
		{	
			if (g.GetObject(i).GetGOClass() == 4)
			{
				enemyship es = (enemyship)g.GetObject(i);
				
				if (es.GetESClass() == 2)
				{
					spaceships++;
					
					int x = 180 * spaceships;
					int y = g.GetObject(i).GetY() - ((starttick - roundtick) % 2);
					
					if (tickdifference > 15 && tickdifference < 19)
					{
						double closestangle = GetClosestAngle(g.GetObject(i), g.GetObject(1).GetX(), g.GetObject(1).GetY(), 12);
						g.GetObject(i).SetAngle(closestangle);
						enemy_herald eh = (enemy_herald)g.GetObject(i);
						if (lasttickdifference != tickdifference)
						{
							eh.Shoot();
						}
					}
					else
					{
						double nextangle = 90;
						
						if (g.GetObject(i).GetAngle() > 90)
							nextangle = g.GetObject(i).GetAngle() - 1;
						if (g.GetObject(i).GetAngle() < 90)
							nextangle = g.GetObject(i).GetAngle() + 1;
						
						g.GetObject(i).SetAngle(nextangle);
						g.GetObject(i).move(x, y);
					}
				}
			}
			
			if (i == g.GetGOList().size() - 1)
				lasttickdifference = tickdifference;
		}
		
		for (int i = 0; i < g.GetGOList().size(); i++)
		{
			if (g.GetObject(i).go_class == 4)
			{
				if (g.GetObject(i).GetY() == 800)
				{
					DeleteSpaceships(2);
					lvlstage++;
					System.out.println(roundtick);
					created = false;
					starttick = -1;
					break;
				}
			}
		}
		
		if (spaceships == 0)
		{
			DeleteSpaceships(2);
			lvlstage++;
			System.out.println(roundtick);
			created = false;
			starttick = -1;
		}
	}
	
	private void MustangCurveConstellation(int pos_x, int pos_y, int radius_x, int radius_y, int roundtick)
	{
		if (starttick == -1)
			starttick = roundtick;
		
		if (!created)
		{
			CreateSpaceships(5, "mustang");
			created = true;
		}
		
		int spaceships = 0;
		
		for (int i = 0; i < g.GetGOList().size(); i++)
		{
			if (g.GetObject(i).GetGOClass() == 4)
			{
				enemyship es = (enemyship)g.GetObject(i);
				
				if (es.GetESClass() == 1)
				{
					spaceships++;
					
				    double degrees = (starttick - roundtick) + (spaceships * 7);
				    double radians = Math.toRadians(degrees);
					double x = radius_x * Math.cos(radians) + pos_x;
					double y = radius_y * Math.sin(radians) + pos_y;
					
					g.GetObject(i).SetAngle(degrees - 90);
					g.GetObject(i).move((int)x, (int)y);
				}
			}
		}
		
		if ((starttick - roundtick) + (5 * 7) <= -200)
		{
			DeleteSpaceships(1);
			lvlstage++;
			starttick = -1;
			created = false;
		}
	}

	public double GetClosestAngle(gameobject go, int pos_x, int pos_y, int deg_add)
	{
		double bestdegree = 999999999;
		double bestdistance = 99999999;
		
		for (int i = 1; i < 360; i++)
		{
		    double degrees = (double)i + deg_add;
		    double radians = Math.toRadians(degrees);
			double x = 55 * Math.cos(radians) + go.GetX() + go.GetImage().getWidth(null) / 2;
			double y = 55 * Math.sin(radians) + go.GetY() + go.GetImage().getHeight(null) / 2;
			
			double distance = Math.sqrt(Math.pow((double)(pos_x - x), (double)2) + Math.pow((double)(pos_y - y), (double)2));
			
			if (distance < bestdistance)
			{
				bestdegree = i;
				bestdistance = distance;
			}
		}
		
		return bestdegree;
	}
	
 	private void CreateSpaceships(int number, String type)
	{
		for (int i = 0; i < number; i++)
		{
			switch (type)
			{
				case "mustang":
					g.AddObject(new enemy_mustang(600, 900, 90)); break;
				case "herald":
					g.AddObject(new enemy_herald(150, -10, 90)); break;
				case "aurora":
					g.AddObject(new enemy_aurora(150, -10, 90)); break;
				case "vanguard":
					g.AddObject(new enemy_vanguard(600, 900, 90)); break;
				case "boss":
					g.AddObject(new enemy_boss(150, -10, 90));
			}
		}
	}
	
	private void DeleteSpaceships(int es_class)
	{
		for (int i = 0; i < g.GetGOList().size(); i++)
		{
			if (g.GetObject(i).GetGOClass() == 4)
			{
				enemyship es = (enemyship)g.GetObject(i);
				if (es.GetESClass() == es_class)
					g.RemoveObject(i);
			}
		}
	}

}
