package Server;
import java.awt.Point;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import Client.CallBackClient;
import Client.Homme;
import Obstacle.Rectangle;;

public class ServeurImpl extends UnicastRemoteObject implements Serveur
{
	private static final long serialVersionUID = 1L;
	
	private int nb_obstacle;
	private int m_x;
	private int m_y;
	private int higher_x;
	private int lower_x;
	private int higher_y;
	private int pas;
	private int nb_individus;
	private ArrayList<Rectangle> listRect;
	private Point depart;
	private Point arrivee;
	private ArrayList<CallBackClient> listCl;
	int nb_client;
	
	protected ServeurImpl() throws RemoteException 
	{
		super();
	}
	
	protected ServeurImpl(int nb,int x,int y,int p,int indiv,int nbc) throws RemoteException 
	{
		super();
		this.setNb_obstacle(nb);
		this.setM_x(x);
		this.setM_y(y);
		// margin de 50 pixel pour la map en x et y
		// pour que le départ ne soit pas collé à l'origine où au bord de la map
		this.setHigher_x((getM_x()) - 50);
		this.setLower_x(50);
		this.setHigher_y((getM_y()) - 50);
		this.setPas(p);
		this.setNb_individus(indiv);
		this.setDepart(createDepart());
		this.setListRect(createRectangle());
		this.setArrivee(createArrivee());
		
		this.listCl = new ArrayList<CallBackClient>();
		this.nb_client = nbc;
	}
	
	//-----------------------------------------------------
	// fonctions création
	//-----------------------------------------------------
	
	public ArrayList<Rectangle> createRectangle()
	{
		ArrayList<Rectangle> tab = new ArrayList<Rectangle>();
		int range_x_max = 0;
		int range_y_max = 0;
		try 
		{
			range_x_max = (getM_x()/12);
			range_y_max = (getM_y()/6);
		} 
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		
		int x,y;
		Point c1,c2,c3,c4;;
		
		for(int i = 0 ; i < this.nb_obstacle; ++i)
		{
			c1 = new Point();
			c2 = new Point();
			c3 = new Point();
			c4 = new Point();
			if(Math.random() <= 0.5)
			{
				x = (int)(Math.random() * this.higher_x);
				if(Math.random() <= 0.5)
				{
					y = (int)(Math.random() * this.higher_y);
				}
				else
				{
					y = 0 - (int)(Math.random() * this.higher_y);
				}
				c1.setLocation(x, y);
				
			}
			else
			{
				x = 0 - (int)(Math.random() * this.higher_x);
				if(Math.random() <= 0.5)
				{
					y = (int)(Math.random() * this.higher_y);
				}
				else
				{
					y = 0 - (int)(Math.random() * this.higher_y);
				}
				c1.setLocation(x, y);
			}
			c2.setLocation((int)c1.getX() + (int)(Math.random() * range_x_max) , (int)c1.getY());
			c3.setLocation((int)c2.getX() , (int)c1.getY() + (int)(Math.random() * range_y_max));
			c4.setLocation((int)c1.getX(), (int)c3.getY());
			
			Rectangle rect=new Rectangle(c1,c2,c3,c4);
			
			try
			{
				if(rect.isInside(this.getDepart()))
				{
					i--;
				}
				else
				{
					tab.add(rect);
				}
			}
			catch(RemoteException e)
			{
				e.printStackTrace();
			}
			
		}
		return tab;
	}
	
	// depart à gauche de la map
	public Point createDepart()
	{
		
		Point p = new Point();
		int x = 0, y = 0;
		x -= (int)(Math.random() * (getHigher_x()-getLower_x())) + getLower_x();
		
		if(Math.random() <= 0.5)
		{
			y += (int)(Math.random() * getHigher_y());
		}
		else
		{
			y -= (int)(Math.random() * getHigher_y());
		}
		
		p.setLocation(x, y);
		return p;
	}
	
	// arrivee à droite de la map
	public Point createArrivee()
	{
		Point p = new Point();
		int x = 0, y = 0;
		int higher_x = 0;
		int higher_y = 0;
		try 
		{
			higher_x = (getM_x()/2) - 50;
			higher_y = (getM_y()/2) - 50;
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		}
		
		int lower_x = 50;
		
		
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
	// fonctions RMI
	//-----------------------------------------------------
	
	public int getNb_obstacle() throws RemoteException
	{
		return this.nb_obstacle;
	}
	
	public int getM_x() throws RemoteException
	{
		return m_x;
	}
	
	public int getM_y() throws RemoteException
	{
		return m_y;
	}
	
	public int getPas() throws RemoteException
	{
		return pas;
	}
	
	public int getNb_individus() throws RemoteException
	{
		return nb_individus;
	}
	
	public ArrayList<Rectangle> getListRect() throws RemoteException
	{
		return listRect;
	}
	
	public Point getDepart() throws RemoteException
	{
		return depart;
	}
	
	public Point getArrivee() throws RemoteException
	{
		return arrivee;
	}
	
	public synchronized boolean registerForCallback(CallBackClient callbackClientObject) throws RemoteException
	{	
		if(listCl.size() < this.nb_client)
		{
			if (!(listCl.contains(callbackClientObject))) 
			{
				listCl.add(callbackClientObject);
				System.out.println("Registered new client ");
				doCallbacks();
				return true;
			}
		}
		return false;
	}
	
	public boolean EverybodyIsRegister() throws RemoteException
	{
		if(listCl.size() == nb_client)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public ArrayList<ArrayList<Homme>> getDonnees(CallBackClient callbackClientObject) throws RemoteException
	{
		ArrayList<ArrayList<Homme>> res = new ArrayList<ArrayList<Homme>>();
		for(CallBackClient ccl : listCl)
		{
			//peut être faire un equals
			if(ccl != callbackClientObject)
			{
				while(!ccl.ClientIsOk());
				res.add(ccl.selection());
			}
		}
		return res;
	}
	
	// affiche le nombre de client connecté
	// effectué que par le serveur
	private synchronized void doCallbacks( ) throws RemoteException
	{
		System.out.println("**************************************");
		System.out.println("Callbacks initiated ---");
		for (int i = 0; i < listCl.size(); i++)
		{
			System.out.println("doing "+ i +"-th callback\n");      
			CallBackClient nextClient = (CallBackClient)listCl.get(i);
			System.out.println(nextClient.notifyMe("Number of registered clients=" +  listCl.size()));
		}
		System.out.println("********************************");
		System.out.println("Server completed callbacks ---");
	}
	
	// appelé que par le serveur lui même
	public void startRegistry(int PortNum)throws RemoteException
	{
		Registry registry;
		try
		{
			registry = LocateRegistry.getRegistry(PortNum);
			registry.list();
		}
		catch (RemoteException e)
		{ 
			registry = LocateRegistry.createRegistry(PortNum);
		}
	} 
	
	//-----------------------------------------------------
	// getter-setter
	//-----------------------------------------------------
	
	public void setNb_obstacle(int nb) 
	{
		this.nb_obstacle = nb;
	}

	public void setM_x(int m_x)
	{
		setHigher_x((m_x/2) - 50);
		this.m_x = m_x;
	}

	public void setM_y(int m_y)
	{
		setHigher_y((m_y/2) - 50);
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

	public void setPas(int pas) 
	{
		this.pas = pas;
	}

	public void setNb_individus(int nb_individus)
	{
		this.nb_individus = nb_individus;
	}

	public void setListRect(ArrayList<Rectangle> listRect)
	{
		this.listRect = listRect;
	}

	public void setDepart(Point depart)
	{
		this.depart = depart;
	}

	public void setArrivee(Point arriver)
	{
		this.arrivee = arriver;
	}
	
	//-----------------------------------------------------
	
	public static void main(String[] args)
	{
		if(args.length != 7)
		{
			System.out.println("Usage : java ServerImpl <port> <nb Obstacle> <Size x>"
					+ "<Size y> <taille pas> <nb individu> <nb Client>");
			System.exit(0);
		}
		try
		{
			ServeurImpl serv = new ServeurImpl(Integer.parseInt(args[1]),
					Integer.parseInt(args[2]),
					Integer.parseInt(args[3]),
					Integer.parseInt(args[4]),
					Integer.parseInt(args[5]),
					Integer.parseInt(args[6]));
			serv.startRegistry(Integer.parseInt(args[0]));
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
