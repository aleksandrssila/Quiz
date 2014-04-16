package rmi.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizRemote extends Remote{
	/**
	 * Get user from database
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	boolean getUserDetails() throws RemoteException;
	/**
	 * 
	 * @throws RemoteException
	 */
	void showQuizGames() throws RemoteException;
	/**
	 * 
	 * @param num
	 * @throws RemoteException
	 */
	void getQuizGame() throws RemoteException;
	
	String askActionOption() throws RemoteException;
	
}
