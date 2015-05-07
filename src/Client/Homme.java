package Client;

import java.util.ArrayList;

public class Homme {

	private ArrayList<Integer> adn;
	int longueur;
	int largeur;
	public Homme()
	{
		adn = new ArrayList<Integer>();
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
		return null;
	}
}
