package Client;

import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame
{
	public Maps m;
	
	public MainFrame()
	{
		super();
		m = new Maps();
		this.setTitle("Project");
		this.getContentPane().setPreferredSize(new Dimension(200,200));
		this.getContentPane().add(m.canvas);
		
		
	}
}