package Client;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import Obstacle.Rectangle;

public class Homme implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> adn;
	private ArrayList<Point> parcours;
	Rectangle arr_re;
	int longueur;
	int largeur;
	boolean mort;
	Point depart;
	Point arrivee;
	Point act;
	ArrayList<Rectangle> obstacles;
	int pas;
	int arret;
	boolean estArrive;
	
	public Homme(Point dep,Point arr, ArrayList<Rectangle> obs,int pa,int width, int height)
	{
		mort=false;
		adn = new ArrayList<Integer>();
		parcours = new ArrayList<Point>();
		depart=new Point(dep.x, dep.y);
		arrivee=new Point(arr.x, arr.y);
		act=new Point(dep.x, dep.y);
		obstacles=obs;
		pas=pa;
		longueur = width;
		largeur = height;
		arret = width*2+height*2;
		arr_re = new Rectangle(new Point(arrivee.x-10, arrivee.y+10), new Point(arrivee.x+10, arrivee.y+10), new Point(arrivee.x+10, arrivee.y-10), new Point(arrivee.x-10, arrivee.y-10));
		attributionAdn(width*2+height*2);
		//estArrive = false;
	}
	
	public ArrayList<Integer> getAdn()
	{
		return adn;
	}
	
	public ArrayList<Point> getParcours() {
		return parcours;
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
		return (int)(Math.random() * 8);
	}
	
	void attributionAdn(int taille)
	{
		parcours.add(new Point(act.x, act.y));
		for(int i = 0; i < taille; ++i)
		{
			int deplacement=deplacementRandom();
			adn.add(deplacementRandom());
			
			if(mort==false && !act.equals(arrivee) && !estArrive)
			{
				switch(deplacement)
				{
					case 0:
						act.y+=pas;
						break;
					case 1:
						act.x+=pas;
						act.y+=pas;
						break;
					case 2:
						act.x+=pas;
						break;
					case 3:
						act.x+=pas;
						act.y-=pas;
						break;
					case 4:
						act.y-=pas;
						break;
					case 5:
						act.x-=pas;
						act.y-=pas;
						break;
					case 6:
						act.x-=pas;
						break;
					case 7:
						act.x-=pas;
						act.y+=pas;
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
						System.out.println("Collision : you died !"+i);
						mort=true;
						arret = i;
						break;
					}
				}
				
				if(!(act.x <=longueur && act.x >=-longueur && act.y <=largeur && act.y >=-largeur))
				{
					//System.out.println("Collision avec le cadre à la "+ i + " itération");
					mort=true;
					arret=i;
				}
				
				if(act==arrivee || arr_re.isInside(act))
				{
					System.out.println("Vous êtes arrivés à destionation !! bien :o");
					estArrive = true;
					arret= i;
				}
				if(mort==false) {
					parcours.add(new Point(act.x, act.y));
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
					p.setLocation(p.x,p.y+pas);
					break;
				case 1:
					p.setLocation(p.x+pas,p.y+pas);
					break;
				case 2:
					p.setLocation(p.x+pas,p.y);
					break;
				case 3:
					p.setLocation(p.x+pas,p.y-pas);
					break;
				case 4:
					p.setLocation(p.x,p.y-pas);
					break;
				case 5:
					p.setLocation(p.x-pas,p.y-pas);
					break;
				case 6:
					p.setLocation(p.x-pas,p.y);
					break;
				case 7:
					p.setLocation(p.x-pas,p.y+pas);
					break;
				default:
					System.out.println("Erreur dans le switch, valeur incorrecte !");
					break;
			}
		}
		return p;
	}
	
	Point setPositionCourante(Point p_actuel ,int i) {
		Point p = p_actuel;
		switch(adn.get(i)) {
		case 0:
			p.setLocation(p.x,p.y+pas);
			break;
		case 1:
			p.setLocation(p.x+pas,p.y+pas);
			break;
		case 2:
			p.setLocation(p.x+pas,p.y);
			break;
		case 3:
			p.setLocation(p.x+pas,p.y-pas);
			break;
		case 4:
			p.setLocation(p.x,p.y-pas);
			break;
		case 5:
			p.setLocation(p.x-pas,p.y-pas);
			break;
		case 6:
			p.setLocation(p.x-pas,p.y);
			break;
		case 7:
			p.setLocation(p.x-pas,p.y+pas);
			break;
		default:
			System.out.println("Erreur dans le switch, valeur incorrecte !");
			break;
		}
		return p;
	}
	
	double distance_depart_arrive()
	{
		return Math.sqrt(Math.pow(arrivee.x-depart.x, 2)+Math.pow(arrivee.y-depart.y, 2));
	}
	
	double distance_actuel_arrive()
	{
		double distance = Math.sqrt(Math.pow(arrivee.x-act.x, 2)+Math.pow(arrivee.y-act.y, 2));
		double poids =0;
		if(mort==true) {
			poids = 0.1*distance;
		}
		return poids + distance;
	}
}
