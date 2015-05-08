package Obstacle;

import java.awt.Point;

public class Rectangle 
{
	private Point coordonnees[] = new Point[4];
	
	public Rectangle(){}
	
	public Rectangle(Point c1,Point c2,Point c3, Point c4)
	{
		this.coordonnees[0] = c1;
		this.coordonnees[1] = c2;
		this.coordonnees[2] = c3;
		this.coordonnees[3] = c4;
	}
	
	public boolean isInside(Point coord)
	{
		boolean res=false;
		
		if(coordonnees[0].x<coordonnees[1].x)
		{
			if(coordonnees[1].y>coordonnees[2].y) // 0 à gauche, 1 à droite haut, 2 à droite bas
			{
				if(coord.x<=coordonnees[1].x && coord.x>=coordonnees[0].x && coord.y<=coordonnees[1].y && coord.y>=coordonnees[2].y)
				{
					res=true;
				}
			}
			else // 0 à gauche, 1 à droite bas, 2 à droite haut
			{
				if(coord.x<=coordonnees[1].x && coord.x>=coordonnees[0].x && coord.y>=coordonnees[1].y && coord.y<=coordonnees[2].y)
				{
					res=true;
				}
			}
		}
		else
		{
			if(coordonnees[1].y>coordonnees[2].y) // 0 à droite, 1 à gauche haut, 2 à gauche bas
			{
				if(coord.x>=coordonnees[1].x && coord.x<=coordonnees[0].x && coord.y<=coordonnees[1].y && coord.y>=coordonnees[2].y)
				{
					res=true;
				}
			}
			else  // 0 à droite, 1 à gauche bas, 2 à gauche haut
			{
				if(coord.x>=coordonnees[1].x && coord.x<=coordonnees[0].x && coord.y>=coordonnees[1].y && coord.y<=coordonnees[2].y)
				{
					res=true;
				}
			}
		}
		return res;
	}

	// getter - setter
	//-----------------------------------------------------
	
	public Point[] getCoordonnees()
	{
		return coordonnees;
	}
	
	public Point getCoord(int i) {
		return coordonnees[i];
	}

	public void setCoordonnees(Point c1,Point c2,Point c3, Point c4)
	{
		this.coordonnees[0] = c1;
		this.coordonnees[1] = c2;
		this.coordonnees[2] = c3;
		this.coordonnees[3] = c4;
	}
}
