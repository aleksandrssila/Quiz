package rmi.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.rmiinterface.QuizRemote;

public class RemoteServerImpl extends UnicastRemoteObject implements QuizRemote{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RemoteServerImpl() throws RemoteException {
		super();
	}

	@Override
	public boolean isValidLogin(String username, String password) throws RemoteException {
		
		if(username.equals("test")){
			return true;
		}
		return false;
	}

}
