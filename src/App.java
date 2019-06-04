import java.awt.BorderLayout;
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
		//Input
		input inp = new input();
		
		//Game Thread
		game g = new game();
		g.addMouseListener(inp);
		g.addMouseMotionListener(inp);
		g.addKeyListener(inp);
		g.setFocusable(true);
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
