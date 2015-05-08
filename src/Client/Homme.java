package Client;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import Obstacle.Rectangle;

public class Homme {

	private ArrayList<Integer> adn;
	int longueur;
	int largeur;
	boolean mort;
	Point depart;
	Point arrivee;
	Point act;
	ArrayList<Rectangle> obstacles;
	
	public Homme(Point dep,Point arr, ArrayList<Rectangle> obs)
	{
		mort=false;
		adn = new ArrayList<Integer>();
		depart=dep;
		arrivee=arr;
		act=depart;
		obs=obstacles;
	}
	
	public ArrayList<Integer> getAdn()
	{
		return adn;
	}
	
	public void setAdn(ArrayList<Integer> a)
	{
		adn=a;
	}
	
	public void changer1Adn(int pos,Integer val)
	{
		adn.set(pos, val);
	}
	
	// retourne un un déplacement entre 0 et 7
	// on utilise normalement Min + (Math.random() * (Max - Min))
	// mais Max = 7 et Min = 0
	int deplacementRandom()
	{
		return (int)(Math.random() * 7);
	}
	
	void attributionAdn(int taille)
	{
		for(int i = 0; i < taille; ++i)
		{
			if(mort==false && act!=arrivee)
			{
				int deplacement=deplacementRandom();
				switch(deplacement)
				{
					case 0:
						act.y++;
						break;
					case 1:
						act.x++;
						act.y++;
						break;
					case 2:
						act.x++;
						break;
					case 3:
						act.x++;
						act.y--;
						break;
					case 4:
						act.y--;
						break;
					case 5:
						act.x--;
						act.y--;
						break;
					case 6:
						act.x--;
						break;
					case 7:
						act.x--;
						act.y++;
						break;
					default:
						System.out.println("Erreur dans le switch, valeur incorrecte !");
						break;
				}
				
				Iterator<Rectangle> it=obstacles.iterator();
				while(it.hasNext())
				{
					if(it.next().isInside(act))
					{
						System.out.println("Collision : you died !");
						mort=true;
						break;
					}
				}
				
				adn.add(deplacementRandom());
				
				if(act==arrivee)
				{
					System.out.println("Vous êtes arrivé à destionation !! bien :o");
					break;
				}
			}
		}
	}

	Point calculDestinationFinale() {
		Point p = new Point(0,this.largeur/2);
		for(int i=0; i < adn.size(); i++) {
			switch(adn.get(i))
			{
				case 0:
					p.setLocation(p.x,p.y++);
					break;
				case 1:
					p.setLocation(p.x++,p.y++);
					break;
				case 2:
					p.setLocation(p.x++,p.y);
					break;
				case 3:
					p.setLocation(p.x++,p.y--);
					break;
				case 4:
					p.setLocation(p.x,p.y--);
					break;
				case 5:
					p.setLocation(p.x--,p.y--);
					break;
				case 6:
					p.setLocation(p.x--,p.y);
					break;
				case 7:
					p.setLocation(p.x--,p.y++);
					break;
				default:
					System.out.println("Erreur dans le switch, valeur incorrecte !");
					break;
			}
		}
		return p;
	}
	
	double distance_depart_arrive()
	{
		return Math.sqrt(Math.pow(arrivee.x-depart.x, 2)+Math.pow(arrivee.y-depart.y, 2));
	}
}
