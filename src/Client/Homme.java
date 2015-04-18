package Client;

import java.util.ArrayList;

public class Homme {

	private ArrayList<Integer> adn;
	
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
}
