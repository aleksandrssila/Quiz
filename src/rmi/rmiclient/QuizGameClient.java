package rmi.rmiclient;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import core.UserInputManager;
import application.Controllers.QuizGameController;
import application.Controllers.UserController;
import rmi.rmiinterface.Constant;
import rmi.rmiinterface.QuizRemote;

public class QuizGameClient {
	
	public static void main (String[] args) throws RemoteException, AlreadyBoundException, NotBoundException{
		
		Registry registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
		QuizRemote  quizRemote = (QuizRemote) registry.lookup(Constant.RMI_ID);
		
		UserController userControl = new UserController();
		boolean status = userControl.authenticateUser();
		
		if(status){ 
			
			QuizGameController qgameC = new QuizGameController();
			qgameC.getAllQuizez();
			qgameC.loadQuiz();
			qgameC.playGame();
		}	
	
	}

}
