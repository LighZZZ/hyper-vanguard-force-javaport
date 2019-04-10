import java.util.concurrent.TimeUnit;

public class game extends Thread
{
	public game()
	{
		InitGame();
	}
	
	public void run() 
	{
	}
	
	public void InitGame()
	{
		// Background
		int xywh_background[] = {0,0,450,700};
		Images.RenderImg("res/SCShmup_texture_1.png", 125, 0, 0, 0, xywh_background);
		
		// Own spaceship
		myship ms = new myship(230, 600, 0);
	}
}
