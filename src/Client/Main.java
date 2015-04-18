package Client;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		// TODO implémantation CORBA
		JFrame jf = new MainFrame();
		jf.setLocation(100, 100);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout());
		jf.setVisible(true);
	}
}
