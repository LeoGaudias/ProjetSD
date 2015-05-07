package Obstacle;

public class Rectangle 
{
	private int coordonnees[] = new int[4];
	
	public Rectangle(){}
	
	public Rectangle(int c1,int c2,int c3, int c4)
	{
		this.coordonnees[0] = c1;
		this.coordonnees[1] = c2;
		this.coordonnees[2] = c3;
		this.coordonnees[3] = c4;
	}
	
	public boolean isInside(int coord)
	{
		//TODO
		return false;
	}

	// getter - setter
	//-----------------------------------------------------
	
	public int[] getCoordonnees()
	{
		return coordonnees;
	}

	public void setCoordonnees(int c1,int c2,int c3, int c4)
	{
		this.coordonnees[0] = c1;
		this.coordonnees[1] = c2;
		this.coordonnees[2] = c3;
		this.coordonnees[3] = c4;
	}
}
