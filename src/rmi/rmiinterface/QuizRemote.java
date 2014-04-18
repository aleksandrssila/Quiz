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
	 * Show all available quiz games
	 * @throws RemoteException
	 */
	void showQuizGames() throws RemoteException;
	/**
	 * Get quiz game from database
	 * @param num
	 * @throws RemoteException
	 */
	void getQuizGame() throws RemoteException;
	/**
	 * Ask user to type action 
	 * @return
	 * @throws RemoteException
	 */
	String askLandingPage() throws RemoteException;
	/**
	 * Ask user to type myQuiz action
	 * @return option selected by user
	 * @throws RemoteException
	 */
	String askMyQuizOption() throws RemoteException;
	/**
	 * Activate create user functions
	 * @throws RemoteException
	 */
	void createQuiz() throws RemoteException;
	/**
	 * Show user quiz scores
	 * @throws RemoteException
	 */
	void seeQuizScore() throws RemoteException;
	
}
