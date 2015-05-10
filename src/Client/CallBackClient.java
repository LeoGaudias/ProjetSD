package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CallBackClient extends Remote
{
	public String notifyMe(String message) throws RemoteException;
	public ArrayList<Homme> selection() throws RemoteException;
	public boolean ClientIsOk() throws RemoteException;
}
