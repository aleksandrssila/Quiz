package core;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputManager {
	
	public  String  username;
	public  String  password;
	public  String  action;
	private Pattern pattern;
	private Matcher matcher;

	private static final String USERNAME_PATTERN =  "^[a-z0-9_-]{3,15}$";
	
	 public UserInputManager(){
		  pattern = Pattern.compile(USERNAME_PATTERN);
	  }
	
	public void askLoginDetails() throws NullPointerException,IllegalArgumentException{
		
		Scanner userInput = new Scanner( System.in );
		
		System.out.print("Enter your username: ");
		this.username   = userInput.nextLine( );
		
		System.out.print("Enter your password: ");
		this.password   = userInput.nextLine( );
		
		
		if(this.username.equals(null)){
			throw new NullPointerException("Error! Empty field");
		}
		
		if(this.password.equals(null)){
			throw new NullPointerException("Error! Empty field");
		}
		
		boolean validus = this.validate(this.username);
		boolean validpa = this.validate(this.password);
		
		if((!validus)||(!validpa)){
			throw new IllegalArgumentException("Lenght should be 3-15 with no special characters");
		}				
	}
	
	public int askForInt(){
		Scanner userInput = new Scanner( System.in );
		
		System.out.print("Option: ");
		try{
			int quizId   = userInput.nextInt();
			return quizId;
		}
		catch(InputMismatchException e){
			System.out.println("Use numbers only!");
			this.askForInt();
		}
		
		return 0;
		
		
	}
	
	public boolean askRegistration(){
		
		Scanner userInput = new Scanner( System.in );
		
		boolean answer   = false;
		boolean register = false;
		
		while(!answer){

			System.out.print("Would you like to register? (yes/no): ");
			String ans = userInput.nextLine( );
			
			if(ans.equals("yes")){
				register = true;
				answer = true;
			}
			
			if(ans.equals("no")){
				register = false;
				answer = true;
			}
		}
			
		return register;
		
	}
	
public void askAction() throws NullPointerException,IllegalArgumentException{
		
		Scanner userInput = new Scanner( System.in );
		
		System.out.print("(play/see score/remove quiz): ");
		this.action   = userInput.nextLine( );
		
		if(this.action.equals(null)){
			throw new NullPointerException("Error! Empty field");
		}
		
		if(!(this.action.equals("play"))
		 &&!(this.action.equals("see score"))	
		 &&!(this.action.equals("remove quiz"))){
			throw new IllegalArgumentException("Wrong input");
		}
				
	}
	 /**
	   * Validate username with regular expression
	   * @param username username for validation
	   * @return true valid username, false invalid username
	   */
	  public boolean validate(final String checkstring){
		  matcher = pattern.matcher(checkstring);
		  return matcher.matches();
	  }

}
