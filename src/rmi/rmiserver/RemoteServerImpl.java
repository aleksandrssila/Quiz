package rmi.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi.rmiinterface.QuizRemote;
import application.Controllers.MyQuizController;
import application.Controllers.QuizGameController;
import application.Controllers.UserController;
import application.Entities.User;

public class RemoteServerImpl extends UnicastRemoteObject implements QuizRemote{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserController 	  userC;
	public QuizGameController qgameC;
	public MyQuizController   mquizC;

	protected RemoteServerImpl() throws RemoteException {
		super();
		this.userC  = new UserController();
		this.qgameC = new QuizGameController();
		this.mquizC = new MyQuizController();
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
		if(this.qgameC.loadQuiz(this.userC.user)){
			this.qgameC.playGame();
		}
	}

	@Override
	public String askLandingPage() throws RemoteException {
		String action = this.userC.askLandingPage();
		return action;
	}

	@Override
	public String askMyQuizOption() throws RemoteException {
		String option =  this.userC.askMyQuiz();
		return option;
	}

	@Override
	public void createQuiz() throws RemoteException {
		this.mquizC.createQuiz(this.userC.user);
	}

	@Override
	public void seeQuizScore() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
