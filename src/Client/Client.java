package Client;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Client
{
	ArrayList<Homme> list;
	
	public Client()
	{
		list=new ArrayList<Homme>();
		list.add(new Homme(new Point(0,0),new Point(15,15)));
		list.add(new Homme(new Point(0,0),new Point(15,15)));
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
				if(list.get(i).distance_depart_arrive()<res.get(0).distance_depart_arrive())
				{
					res.add(0,list.get(i));
				}
			}
		}
		
		return res;
	}
	
	void reproduction()
	{
		list=classement();
		ArrayList<Homme> select=selection();
		
		// envoye serveur
		
		// réception du serveur des meilleurs des autres clients
		ArrayList<ArrayList<Homme>> recep;
		recep=new ArrayList<ArrayList<Homme>>(); // temporaire
		
		ArrayList<Homme> res=new ArrayList<Homme>();
		
		if(recep.size()==0) // pas d'autre client
		{
			for(int i=0;i<select.size();i++)
			{
				int rnd1=(int)Math.random()*select.size();
				int rnd2=(int)Math.random()*select.size();
				if(rnd2==rnd1)
				{
					rnd2=(int)Math.random()*select.size();
				}
				
				Homme temp=croissement(select.get(rnd1),select.get(rnd2));
				res.add(mutation(temp));
				
				// suppression de ceux croisés comme ça par de réutilisation :
				// à voir si un homme n'a pas le droit d'avoir plus d'un enfant
				// s'il a le droit il suffit de vérifier que l'enfant créé n'est pas déjà présent dans res
				select.remove(rnd1);
				select.remove(rnd2);
			}
		}
		else
		{
			recep.add(select); // ajout de nos meilleurs
			for(int i=0;i<recep.get(0).size();i++)
			{
				int client=(int)Math.random()*recep.size(); // choix du client
				int client2=(int)Math.random()*recep.size();
				if(client2==client)
				{
					client2=(int)Math.random()*recep.size();
				}
				
				int rnd1=(int)Math.random()*recep.get(client).size();
				int rnd2=(int)Math.random()*recep.get(client2).size();
				
				Homme temp=croissement(recep.get(client).get(rnd1),recep.get(client2).get(rnd2));
				res.add(mutation(temp));
			}
		}
		
		renouvellement(res);
	}
	
	ArrayList<Homme> selection()
	{
		ArrayList<Homme> select=new ArrayList<Homme>();
		for(int i=0;i<list.size();i++)
		{
			if(Math.random()*100>1/i*100)
			{
				select.add(list.get(i));
			}
		}
		
		return select;
	}
	
	Homme croissement(Homme h1,Homme h2)
	{
		Homme res=new Homme(h1.depart,h1.arrivee);
		ArrayList<Integer> adn=new ArrayList<Integer>();
		
		int coupe=(int)Math.random()*100;
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
		if(Math.random()*100<=7)
		{
			int pos=(int)Math.random()*h.getAdn().size();
			Integer val=(int)Math.random()*7;
			res.changer1Adn(pos, val);
		}
		
		return res;
	}
	
	void renouvellement(ArrayList<Homme> enfants)
	{
		for(int i=list.size()-enfants.size()-1;i<list.size();i++) // suppression des plus mauvais
		{
			list.remove(i);
		}
		
		for(int i=0;i<enfants.size();i++)
		{
			list.add(enfants.get(i));
		}
	}
	
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
