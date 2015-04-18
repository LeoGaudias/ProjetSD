package Client;

import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public Maps m;
	
	public MainFrame()
	{
		super();
		m = new Maps();
		this.setTitle("Project");
		this.getContentPane().setPreferredSize(new Dimension(800,600));
		this.getContentPane().add(m.canvas);
		
		
	}
}