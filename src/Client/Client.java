package Client;

import java.awt.BorderLayout;
import java.awt.Point;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import Obstacle.Rectangle;
import Server.Serveur;

public class Client extends UnicastRemoteObject implements CallBackClient
{
	
	private static final long serialVersionUID = 1L;
	
	Serveur serv;
	
	int width;
	int height;
	int pas;
	Point depart;
	Point arrivee;
	ArrayList<Rectangle> obstacles;
	ArrayList<Homme> list;
	int nb_indiv;
	
	boolean isOk;
	boolean estArrive;
	
	public Client(Serveur s, int w, int h, int p, Point dep, Point arri, ArrayList<Rectangle> rec, int indiv) throws RemoteException
	{
		super();
		this.serv = s;
		this.width = w;
		this.height = h;
		this.pas = p;
		this.depart = dep;
		this.arrivee = arri;
		this.obstacles = rec;
		this.nb_indiv = indiv;
		this.list = initListHomme(dep,arri,rec,nb_indiv,p);
		
		this.isOk = false;
		this.estArrive = false;
	}
	
	ArrayList<Homme> initListHomme(Point dep,Point arri,ArrayList<Rectangle> rec,int nb_indiv, int pas)
	{
		ArrayList<Homme> res = new ArrayList<Homme>();
		for (int i = 0; i < nb_indiv; i++)
		{
			res.add(new Homme(dep,arri,rec,pas,width, height));
		}
		
		return res;
	}
	
	ArrayList<Homme> classement() // à modifier pour prendre en compte la collision
	{
		ArrayList<Homme> res=new ArrayList<Homme>();
		for(int i=0;i<list.size();i++)
		{
			if(res.size()==0)
			{
				res.add(list.get(i));
			}
			else
			{
				boolean in=false;
				for(int j=0;j<res.size();j++)
				{
					if(list.get(i).distance_actuel_arrive()<=res.get(j).distance_actuel_arrive())
					{
						res.add(j,list.get(i));
						in=true;
						break;
					}
				}
				
				if(in==false)
				{
					res.add(list.get(i));
				}
				
			}
		}
		
		return res;
	}
	
	void reproduction()
	{
		list=classement();
		ArrayList<Homme> select;
		isOk = true;
		try 
		{
			select = selection();
			// envoye serveur
			
			// réception du serveur des meilleurs des autres clients
			ArrayList<ArrayList<Homme>> recep = serv.getDonnees((CallBackClient)this);
			//recep=new ArrayList<ArrayList<Homme>>(); // temporaire
			
			ArrayList<Homme> res = new ArrayList<Homme>();
			
			if(recep.size()==0) // pas d'autre client
			{
				for(int i=0;i<select.size();i++)
				{
					Random rand = new Random(); 
					int rnd1 = rand.nextInt(select.size()); 
					int rnd2 = rand.nextInt(select.size()); 
					while(rnd2 == rnd1)
					{
						rnd2 = rand.nextInt(select.size());
					}
					
					Homme temp = croissement(select.get(rnd1),select.get(rnd2));
					res.add(mutation(temp));
					
					// suppression de ceux croisés comme ça par de réutilisation :
					// à voir si un homme n'a pas le droit d'avoir plus d'un enfant
					// s'il a le droit il suffit de vérifier que l'enfant créé n'est pas déjà présent dans res
				//	select.remove(rnd1);
				//	select.remove(rnd2);
				}
			}
			else
			{
				recep.add(select); // ajout de nos meilleurs
				for(int i = 0; i < recep.get(0).size(); i++)
				{
					Random rand = new Random();
					int client = rand.nextInt(recep.size()); // choix du client
					int client2 = rand.nextInt(recep.size());
					while(client2 == client)
					{
						client2 = rand.nextInt(recep.size());
					}
					
					int rnd1 = rand.nextInt(recep.get(client).size());
					int rnd2 = rand.nextInt(recep.get(client2).size());
					
					Homme temp = croissement(recep.get(client).get(rnd1),recep.get(client2).get(rnd2));
					res.add(mutation(temp));
				}
			}
			
			renouvellement(res);
			isOk = false;
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	Homme croissement(Homme h1,Homme h2)
	{
		Homme res=new Homme(h1.depart,h1.arrivee,h1.obstacles,h1.pas,width, height);
		ArrayList<Integer> adn=new ArrayList<Integer>();
		
		Random rand = new Random();
		int coupe=rand.nextInt(h1.getAdn().size()-2)+1; 
		for(int i=0;i<h1.getAdn().size();i++)
		{
			if(i<=coupe)
			{
				adn.add(h1.getAdn().get(i));
			}
			else
			{
				adn.add(h2.getAdn().get(i));
			}
		}
		res.setAdn(adn);
		return res;
	}
	
	Homme mutation(Homme h)
	{
		Homme res=h;
		int pourcent = (int) (0.04*res.getAdn().size());
		for(int i=0; i<pourcent; i++)
		{
			Random rand = new Random();
			int pos=rand.nextInt(h.getAdn().size());
			Integer val=(int)Math.random()*7;
			res.changer1Adn(pos, val);
		}
		
		return res;
	}
	
	void renouvellement(ArrayList<Homme> enfants)
	{
		int taille=list.size();
		for(int i=list.size()-enfants.size();i<taille;i++) // suppression des plus mauvais
		{
			list.remove(list.size()-1);
		}
		
		for(int i=0;i<enfants.size();i++)
		{
			list.add(enfants.get(i));
		}
	}
	
	//-----------------------------------------------------
	// fonctions RMI
	//-----------------------------------------------------
	
	public String notifyMe(String message) throws RemoteException
	{
		String returnMessage = "Call back received: " + message + " width du client " + this.width;
		System.out.println(returnMessage);
		return returnMessage;
	}
	
	public ArrayList<Homme> selection() throws RemoteException 
	{
		ArrayList<Homme> select=new ArrayList<Homme>();
		for(int i=0;i<list.size();i++)
		{
			double rand = Math.random();
			if(rand<=1./(i+1))
			{
				select.add(list.get(i));
			}
		}
		
		return select;
	}
	
	//est ok quand tout les calculs sont ok
	public boolean ClientIsOk() throws RemoteException
	{
		return this.isOk;
	}
	
	//-----------------------------------------------------
	
	public static void main(String[] args)
	{
		try
		{
			Serveur serv = (Serveur) Naming.lookup("//127.0.0.1:5000/Serveur");
			Client cl = new Client(serv,serv.getM_x(),serv.getM_y(),
						serv.getPas(),serv.getDepart(),
						serv.getArrivee(),serv.getListRect(),
						serv.getNb_individus());
			// L'interface peut peut être suffir au lieu d'envoyer tout l'objet client
			
			//cl.reproduction();
			
			JFrame jf = new MainFrame(cl.width, cl.height,serv.getNb_obstacle(),cl.list);
			jf.setLocation(100, 100);
			jf.pack();
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setLayout(new BorderLayout());
			jf.setVisible(true);
			
			serv.registerForCallback((CallBackClient)cl);
			while(!serv.EverybodyIsRegister());
			/*
			 * while(!atteint la sortie)
			 * {
			 * 	ArrayList<ArrayList<Homme>> = serv.getDonnees;
			 *  puis traité ces données, je ne sais pas si la fonction est faite ou non
			 * }
			 * 
			*/
			
			int compteur = 0;
			
			while(!cl.estArrive) {
				cl.reproduction();
				System.out.println("Taille liste"+cl.list.size());
				compteur++;
				System.out.println(compteur);
				if(compteur%100==0) {
					((MainFrame) jf).refresh(cl.list);
				}
				for(Homme l : cl.list)
				{
					if(l.estArrive)
					{
						cl.estArrive = true;
					}
				}
			}

		}
		catch (NotBoundException re) { System.out.println(re) ; }
		catch (RemoteException re) { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ; }
	}
}
