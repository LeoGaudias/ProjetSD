package Server;

import java.awt.Point;
import java.rmi.RemoteException;
import Obstacle.Rectangle;

public interface Serveur extends java.rmi.Remote
{
	public Rectangle[] getCarre() throws RemoteException;
	public Point getDepart() throws RemoteException;
	public Point getArrivee() throws RemoteException;
}
