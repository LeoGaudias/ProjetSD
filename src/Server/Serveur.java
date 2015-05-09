package Server;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.ArrayList;
import Obstacle.Rectangle;

public interface Serveur extends java.rmi.Remote
{
//	public ArrayList<Rectangle> getRectangle() throws RemoteException;
//	public Point getDepart() throws RemoteException;
//	public Point getArrivee() throws RemoteException;
	public int getNb_obstacle() throws RemoteException;
	public int getM_x() throws RemoteException;
	public int getM_y() throws RemoteException;
	public int getPas() throws RemoteException;
	public int getNb_individus() throws RemoteException;
	public ArrayList<Rectangle> getListRect() throws RemoteException;
	public Point getDepart() throws RemoteException;
	public Point getArrivee() throws RemoteException;
}
