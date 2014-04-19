package rmi.client;

import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.rmiinterface.Constant;
import rmi.rmiinterface.QuizRemote;

public class Client {
	
public static void main (String[] args) throws AlreadyBoundException, RemoteException{
		
		try{
				
			Registry registry 	   = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
			QuizRemote  quizRemote = (QuizRemote) registry.lookup(Constant.RMI_ID);
			
			while(true){
				// get user details
				boolean auth = quizRemote.getUserDetails();	
				// if user is authenticated, show available quiz games
				if(auth){	

					String action = quizRemote.askLandingPage();
			
					if(action.equals("play-quiz")){
						quizRemote.getQuizGame();
					}
					if(action.equals("my-profile")){
						
						String option = quizRemote.askMyQuizOption();
						
						if(option.equals("manage-quiz")){
							quizRemote.manageQuiz();
						}
						if(option.equals("add-quiz")){
							quizRemote.createQuiz();
						}
						if(option.equals("see-score")){
							quizRemote.seeQuizScore();
						}
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
		catch(UnmarshalException e){
			System.out.println("Thank you for visiting! Bye!");
		}
	
	}

}
