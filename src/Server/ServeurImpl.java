package Server;
import java.awt.Point;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Obstacle.Rectangle;;

public class ServeurImpl extends UnicastRemoteObject implements Serveur
{
	private static final long serialVersionUID = 1L;
	private int nb_carre;
	private int m_x;
	private int m_y;
	private int higher_x;
	private int lower_x;
	private int higher_y;

	protected ServeurImpl() throws RemoteException 
	{
		super();
	}
	
	protected ServeurImpl(int nb,int x,int y) throws RemoteException 
	{
		super();
		this.setNb_carre(nb);
		this.setM_x(x);
		this.setM_y(y);
		// margin de 50 pixel pour la map en x et y
		// pour que le départ ne soit pas collé à l'origine où au bord de la map
		setHigher_x((getM_x()/2) - 50);
		setLower_x(50);
		setHigher_y((getM_y()/2) - 50);
	}
	
	// fonctions RMI
	//-----------------------------------------------------
	
	public Rectangle[] getCarre() throws RemoteException
	{
		//TODO
		Rectangle tab [] = new Rectangle[this.nb_carre];
		int range_x_max = (getM_x()/6);
		
		int c1,c2,c3,c4;
		for(int i = 0 ; i < this.nb_carre; ++i)
		{
			tab[i] = new Rectangle();
		}
		
		return tab;
	}
	
	// depart à gauche de la map
	public Point getDepart() throws RemoteException
	{
		Point p = new Point();
		int x = 0, y = 0;
		// margin de 50 pixel pour la map en x et y
		// pour que le départ ne soit pas collé à l'origine où au bord de la map
		
		
		x -= (int)(Math.random() * (higher_x-lower_x)) + lower_x;
		
		if(Math.random() <= 0.5)
		{
			y += (int)(Math.random() * higher_y);
		}
		else
		{
			y -= (int)(Math.random() * higher_y);
		}
		
		p.setLocation(x, y);
		return p;
	}
	
	// arrivee à droite de la map
	public Point getArrivee() throws RemoteException
	{
		Point p = new Point();
		int x = 0, y = 0;
		
		// margin de 50 pixel pour la map en x et y
		// pour que l'arrivée ne soit pas collé à l'origine où au bord de la map 
		int higher_x = (getM_x()/2) - 50;
		int lower_x = 50;
		int higher_y = (getM_y()/2) - 50;
		
		x += (int)(Math.random() * (higher_x-lower_x)) + lower_x;
		
		if(Math.random() <= 0.5)
		{
			y += (int)(Math.random() * higher_y);
		}
		else
		{
			y -= (int)(Math.random() * higher_y);
		}
		
		p.setLocation(x, y);
		return p;
	}
	
	//-----------------------------------------------------
	// getter-setter
	//-----------------------------------------------------

	public int getNb_carre() 
	{
		return this.nb_carre;
	}
	
	public void setNb_carre(int nb_carre) 
	{
		this.nb_carre = nb_carre;
	}
	
	public int getM_x()
	{
		return m_x;
	}

	public void setM_x(int m_x)
	{
		this.m_x = m_x;
	}

	public int getM_y()
	{
		return m_y;
	}

	public void setM_y(int m_y)
	{
		this.m_y = m_y;
	}
	
	public int getHigher_x()
	{
		return higher_x;
	}

	public void setHigher_x(int higher_x)
	{
		this.higher_x = higher_x;
	}

	public int getLower_x()
	{
		return lower_x;
	}

	public void setLower_x(int lower_x)
	{
		this.lower_x = lower_x;
	}

	public int getHigher_y()
	{
		return higher_y;
	}

	public void setHigher_y(int higher_y)
	{
		this.higher_y = higher_y;
	}
	
	//-----------------------------------------------------
	
	public static void main(String[] args)
	{
		if(args.length != 4)
		{
			System.out.println("Usage : java ServerImpl <port> <nb Carre> <Size x> <Size y>");
			System.exit(0);
		}
		try
		{
			ServeurImpl serv = new ServeurImpl ();
			Naming.rebind("rmi://localhost:"+ args[0] + "/Serveur", serv);
			System.out.println("Serveur en service");
		}
		catch (Exception e)
		{
			System.out.println("ServeurImpl : " + e.getMessage());
			e.printStackTrace();
		}
	}	
}
