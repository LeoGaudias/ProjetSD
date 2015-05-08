package Client;

import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public Maps m;
	
	public MainFrame(int width, int height,int nb_rectangles,Homme h)
	{
		super();
		m = new Maps(width, height,nb_rectangles,h);
		this.setTitle("Project");
		this.getContentPane().setPreferredSize(new Dimension(width,height));
		this.getContentPane().add(m.canvas);
		
		
	}
}