package application.Controllers;

import core.UserInputManager;
import application.Entities.User;
import application.Models.UserModel;

public class UserController {
	
	public  User 			 user;
	public  UserModel   	 userModel;
	public  int         	 lAttempts;
	
	public UserController(){
		this.user 	    = new User();
		this.userModel  = new UserModel();
		this.lAttempts  = 0;
	}
	/*
	public static void main (String[] args){
		
		UserController userC = new UserController();
		String action = userC.askAction();
		
		System.out.println(action);
	}
	*/
	public String askAction(){
		
		System.out.println("---------------------");
		System.out.println("What you want to do ?");
		System.out.println("---------------------");
		
		UserInputManager inpManager = new UserInputManager();
		String action = null;
		boolean act = false;
		
		while(!act){
			
			try{
				inpManager.askAction();
				if(inpManager.action !=null){
					action = inpManager.action;
					act = true;
				}
			}
			catch(NullPointerException e){
				System.out.println(e.getMessage());
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}	
		}
			
		return action;
	}
	/**
	 * 
	 * @return
	 */
	public boolean askLoginDetails(){
				
		boolean loged = false;
		boolean newUser = false;
		
		while(!loged){
						
			try{
				
				UserInputManager inpManager = new UserInputManager();
				
				inpManager.askLoginDetails();
				
				if(!(inpManager.username.equals(null))&&!(inpManager.password.equals(null))){
					
					this.setUser(this.userModel.getUserByLogin(inpManager.username));
										
					if(this.user.getName() == null){
						
						System.out.println("You are not in the database.");
						newUser = inpManager.askRegistration();
						
						if(newUser){
							System.out.println("Registration details");
							loged = this.registerUser();
							break;
						}
					}
					else if(this.user.getId() > 0){
						if(this.user.getPassword().equals(inpManager.password)){
							loged = true;
							break;
						}
					}
					else{
						System.out.println("Wrong Details");
						loged = false;
					}
				}
			}
			catch(NullPointerException e){
				System.out.println(e.getMessage());
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}	
		}
		
		return loged;	
	}
	/**
	 * 
	 * @return
	 */
	public boolean registerUser(){
		
		
		User newuser = new User();
		boolean complete = false;
		
		while(!complete){
			UserInputManager inpM = new UserInputManager();
			inpM.askLoginDetails();
			
			if((inpM.password !=null)&&(inpM.username != null)){
				newuser.setName(inpM.username);
				newuser.setPassword(inpM.password);
			
				this.user = this.userModel.createUser(newuser);
				complete = true;
			}

		}
		
		return (this.user.getId() > 0) ? true : false;	
	}
	/**
	 * 
	 * @param user
	 */
	public void setUser(User user){
		this.user = user;
	}
	
}
