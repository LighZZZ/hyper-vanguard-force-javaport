import java.awt.EventQueue;
import javax.swing.JFrame;

public class App extends JFrame
{
	public App()
	{
		InitUI();
	}
	
	public void InitUI()
	{
		int xywh[] = {0,0,450,700};
		add(new Images("res/SCShmup_texture_1.png", 140, 0, 0, 0, xywh));	// Test Rendering
		
        setSize(550, 800);

        setTitle("Hyper Vanguard Force");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
	}
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(() -> {
			App a = new App();
			a.setVisible(true);
		});
	}
}
