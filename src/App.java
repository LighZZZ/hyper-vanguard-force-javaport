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
		add(new Images("res/SCShmup_texture_1.png"));	// Test Pictures
		add(new Images("res/SCShmup_texture_2.png"));
		
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
