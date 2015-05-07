package Client;

import java.awt.Point;
import java.util.ArrayList;

public class Homme {

	private ArrayList<Integer> adn;
	int longueur;
	int largeur;
	boolean mort;
	Point depart;
	Point arrivee;
	
	public Homme(Point dep,Point arr)
	{
		mort=false;
		adn = new ArrayList<Integer>();
		depart=dep;
		arrivee=arr;
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
			adn.add(deplacementRandom());
		}
	}

	Point calculDestinationFinale() {
		Point p = new Point(0,this.largeur/2);
		for(int i=0; i < adn.size(); i++) {
			switch(adn.get(i)) {
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
					p.setLocation(p.x--,p.y--);
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
