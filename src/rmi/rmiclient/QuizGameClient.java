package rmi.rmiclient;

import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import core.UserInputManager;
import application.Entities.User;
import rmi.rmiinterface.Constant;
import rmi.rmiinterface.QuizRemote;

public class QuizGameClient {
	
	public static void main (String[] args) throws AlreadyBoundException, RemoteException{
		
		try{
				
			Registry registry 	   = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
			QuizRemote  quizRemote = (QuizRemote) registry.lookup(Constant.RMI_ID);
			
			while(true){
				// get user details
				boolean auth = quizRemote.getUserDetails();	
				// if user is authenticated, show available quiz games
				if(auth){	
					
					String action = quizRemote.askActionOption();
					
					if(action.equals("play")){
						quizRemote.showQuizGames();
						quizRemote.getQuizGame();
					}
					if(action.equals("see score")){
											
					}
					if(action.equals("remove quiz")){
						
					}
				}	
			}					
		}
		catch(NotBoundException e){
			System.out.print("Please Check your settings");
		}
		catch(ConnectException e){
			System.out.print("Please start server");
		}
		
	
	}

}
