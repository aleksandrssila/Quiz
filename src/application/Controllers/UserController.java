package application.Controllers;

import core.UserInputManager;
import application.Entities.User;
import application.Models.UserModel;

public class UserController {
	
	public  User 		user;
	private int         attemps;
	private UserModel   userModel;
	
	public boolean authenticateUser(){	
		
		UserInputManager inpManager = new UserInputManager();
				
		boolean auth = false;
				
		while(!auth){
			
			if(this.attemps > 3){
				System.out.println("You have fail to many times.Please restart aplication.");
				return false;
			}
			
			try{
				
				inpManager.askLoginDetails();
				boolean valid = this.getUserByUsernameAndPassword(inpManager.username, inpManager.password);
				System.out.println(valid);
				if(valid){
					auth = true;
					break;
				}else{
					this.attemps++;				
				}
			}
			catch(NullPointerException e){
				System.out.println(e.getMessage());
				continue;
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
				continue;
			}
		}
		
		return true;	

	}
	
	
	public boolean getUserByUsernameAndPassword(String username, String password){
		
		this.userModel = new UserModel();
		this.user      = new User();
		
		
		this.user = this.userModel.getUserByLogin(username);
		
		if(this.user.getId() != 0){
			if(this.user.getPassword().equals(password)){
				return true;
			}
		}
		
		return false;
	
	}
}
