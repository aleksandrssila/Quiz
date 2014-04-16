package rmi.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import application.Controllers.QuizGameController;
import application.Controllers.UserController;
import application.Entities.User;
import rmi.rmiinterface.QuizRemote;

public class RemoteServerImpl extends UnicastRemoteObject implements QuizRemote{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserController 	  userC;
	public QuizGameController qgameC;

	protected RemoteServerImpl() throws RemoteException {
		super();
		this.userC  = new UserController();
		this.qgameC = new QuizGameController();
	}

	@Override
	public boolean getUserDetails() throws RemoteException {
		
		return (this.userC.user.getId()>0) ? true : this.userC.askLoginDetails();
	}
	/**
	 * 
	 * @param user
	 */
	public void setUser(User user){
		this.userC.user = user;
	}

	@Override
	public void showQuizGames() throws RemoteException {
		this.qgameC.getAllQuizez();
	}
	/**
	 * 
	 */
	@Override
	public void getQuizGame() throws RemoteException {
		if(this.qgameC.loadQuiz()){
			this.qgameC.playGame();
		}
	}

	@Override
	public String askActionOption() throws RemoteException {
		String action = this.userC.askAction();
		return action;
	}

}
