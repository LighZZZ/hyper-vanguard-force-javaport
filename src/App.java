import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class App extends JFrame
{
	public App()
	{
		InitUI();
	}
	
	public void InitUI()
	{	
		// Image Panel
		Images imgs = new Images();
		imgs.setBorder(new EmptyBorder(5, 5, 5, 5));
		imgs.setVisible(true);
		add(imgs);
		
		// Game thread
		game g = new game();
		Thread thr = new Thread(g);
		thr.start();
		setLayout(new BorderLayout());
		add(g, BorderLayout.CENTER);
		
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
