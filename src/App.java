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
		//Input Thread
		input inp = new input();
		Thread thr2 = new Thread(inp);
		setLayout(new BorderLayout());
		add(inp, BorderLayout.CENTER);
		thr2.start();
		
		//Game Thread
		game g = new game();
		Thread thr = new Thread(g);
		setLayout(new BorderLayout());
		add(g, BorderLayout.CENTER);
		thr.start();
		
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
