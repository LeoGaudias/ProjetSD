package Client;

import java.rmi.RemoteException;

public interface CallBackClient extends java.rmi.Remote
{
	public String notifyMe(String message) throws RemoteException;
}
