package application.Controllers;

import core.UserInputManager;
import application.Entities.User;
import application.Models.UserModel;
/**
 * @CLASS   UserController
 * This class is controlling user behaviour on the system
 * 
 * @VAR 	user - user entity from class User @see User
 * @VAR 	UuserModel - UserModel class       @see Usermodel 
 */
public class UserController {
	
	public  User 			 user;
	public  UserModel   	 userModel;
	
	public UserController(){
		this.user 	    = new User();
		this.userModel  = new UserModel();
	}
	public static void main (String[] args){
		
		UserController userC = new UserController();
		String action = userC.askMyQuiz();
		
	    System.out.println(action);
	}
	/**
	 * User Authentication
	 * @return boolean true if user is logged in, and false if not
	 */
	public boolean askLoginDetails(){
		// customer is logged in
		boolean loged = false;				
		while(!loged){
			UserInputManager  inpM = new UserInputManager("USERNAME_PATTERN");				
			// ask for login
			inpM.setMessage("Please type your login: ");
			String login = inpM.askString();		
			// ask for password
			inpM.setMessage("Please type your password: ");
			String pass = inpM.askString();
			// get user details
			this.setUser(this.userModel.getUserByLogin(login));
			// if username does not exist in database					
			if(this.user.getName() == null){
				// print error message
				System.out.println("You are not in the database.");
				// input manager
				UserInputManager  inpReg = new UserInputManager("USERNAME_PATTERN");
				// set values for action
				inpReg.setMessage("Would you like to register?");
				inpReg.setOption("yes");
				inpReg.setOption("no");
				// ask user
				String register = inpReg.askAction();
				// start user registration
				if(register.equals("yes")){
					loged = this.registerUser();
					break;
				}
			}
			else if(this.user.getId() > 0){
				if(this.user.getPassword().equals(pass)){
					loged = true;
					break;
				}
			}
			else{
				System.out.println("Wrong Details");
				loged = false;
			}
		}
		
		return loged;	
	}
	/**
	 * User Registration
	 * @return boolean true if user was created, false if the wan an error
	 */
	public boolean registerUser(){
		// user entity
		User newuser = new User();
		// input manager
		UserInputManager inpM = new UserInputManager("USERNAME_PATTERN");
		// ask for login
		inpM.setMessage("Please type your login: ");
		newuser.setName(inpM.askString());		
		// ask for password
		inpM.setMessage("Please type your password: ");
		newuser.setPassword(inpM.askString()); 
		// create new user
		this.user = this.userModel.createUser(newuser);
		// return status
		return (this.user.getId() > 0) ? true : false;	
	}
	/**
	 * 
	 * @return
	 */
	public String askMyQuiz(){
		String answer = null;
		// Initialise input manager class 
		UserInputManager inpManager = new UserInputManager("USERNAME_PATTERN");
		// set requirements
		inpManager.setMessage("-My Quiz Games Profile -");
		inpManager.setOption("manage-quiz");
		inpManager.setOption("add-quiz");
		inpManager.setOption("see-score");
		// get answer from user
		answer = inpManager.askAction();
		
		return answer;
	}
	public String askLandingPage(){
		String answer = null;
		// Initialise input manager class 
		UserInputManager inpManager = new UserInputManager("USERNAME_PATTERN");
		// set requirements
		inpManager.setMessage("-What you would like to do today? -");
		inpManager.setOption("play-quiz");
		inpManager.setOption("my-profile");
		// get answer from user
		answer = inpManager.askAction();
		
		return answer;
	}
	/**
	 * Set user
	 * @param user
	 */
	public void setUser(User user){
		this.user = user;
	}
	
}
