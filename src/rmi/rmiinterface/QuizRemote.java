package rmi.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizRemote extends Remote{
	
	public boolean isValidLogin(String username, String password) throws RemoteException;

}
