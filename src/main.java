import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;

public class main 
{
	static GraphicsConfiguration gc;
	
	public static void InitUI()
	{
		JFrame frame = new JFrame();
		frame.setTitle("Hyper Vanguard Force");
		frame.setSize(500, 850);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	
	public static void main(String[] args) 
	{
		InitUI();
	}
}
