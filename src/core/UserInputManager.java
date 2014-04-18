package core;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInputManager {

	private String  	 message;
	private List<String> options;
	private int          intoption;
	private int 		 validateAs;
	
	
	private Pattern pattern;
	private Matcher matcher;

	private static final String USERNAME_PATTERN =  "^[a-z0-9_-]{2,15}$";
	private static final String QUESTION_PATTERN =  "^([A-Z]|[0-9]|[a-z]|[\\s]|[!]|[?]){5,100}";
	private static final String INTIGER_PATTERT  =  "^[0-9]{1,10}";

	 public UserInputManager(String pattern){
		  this.pattern = Pattern.compile(this.getPattern(pattern));
		  this.options = new ArrayList<String>();
	}
	 /**
	  * Gets pattern for input manager
	  * @param pattern
	  * @return
	  */
	 private String getPattern(String pattern){
		 if(pattern.equals("USERNAME_PATTERN")){
				return UserInputManager.USERNAME_PATTERN;
		 }else if(pattern.equals("QUESTION_PATTERN")){
			 return UserInputManager.QUESTION_PATTERN;
		 }else if(pattern.equals("INTIGER_PATTERT")){
			 return UserInputManager.INTIGER_PATTERT;
		 }else{
			 return UserInputManager.USERNAME_PATTERN;
		 }
	}
	/**
	 * Add option to option list
	 * @param option
	 */
	public void setOption(String option){
		this.options.add(option);
	}
	/**
	 * Set message 
	 * @param msg
	 */
	public void setMessage(String msg){
		this.message = msg;
	}
	/**
	 * Asks user to chose action
	 * @return String
	 */
	public String askAction(){
				
		boolean valid = false;
		String  string = null;
		// print message with available options
		System.out.println(this.message);
		System.out.print("Select from (");
		// show all options
		for(String option:this.options){
			System.out.print("|"+option+"|");
		}
		System.out.print("): ");
		// while user will not type valid option, ask again
		while(!valid){
			// user input
			Scanner userInput = new Scanner( System.in );
			string = userInput.nextLine();
			// check user input against options
			for(String option:this.options){
				// check against options
				if(option.equals(string)){
					valid = true;
				}
				// exit program
				if(string.equals("exit")){
					try{System.exit(1);
					}catch(Exception e){
						System.out.println("Thank you for visiting! Bye!");
					}
				}
			}
			// show msg if invalid
			if(!valid){
				System.out.print("Invalid option, try again: ");
			}
		}
		
		return string;
	}
	/**
	 * Ask user for string
	 * @return string
	 */
	public String askString(){
		// print message with available options
		System.out.print(this.message);
		String string = null;
		boolean valid = false;
		// while input is not valid, ask again
		while(!valid){
			// user input
			Scanner userInput = new Scanner( System.in );
			string = userInput.nextLine();
			// validate string
			valid = this.validateString(string);
			if(!valid){
				System.out.print("2 char minumum and Latin only! :");
			}
		}
		return string;	
	}
	/**
	 * 
	 * @return
	 */
	public int askForInt(){
		int quizId = 0;
		boolean valid = false;
		// show message
		System.out.println(this.message);
		// while user input is wrong, ask again
		while(!valid){
			// user input
			Scanner userInput = new Scanner( System.in );
			String input = userInput.nextLine();
			// exit
			if(input.equals("exit")){
				try{System.exit(1);
				}catch(Exception e){
					System.out.println("Thank you for visiting! Bye!");
				}
			}
			// parse to integer
			try{
				quizId   = Integer.parseInt(input);
				valid = true;
			}
			// catch error 
			catch(NumberFormatException e){
				System.out.print("Numbers only! try again: ");
				valid = false;
			}
		}
		
		return quizId;	
	}
	/**
	 * validate String 
	 * @param option
	 * @return
	 */
	private boolean validateString(String string){
		// exit 
		if(string.equals("exit")){
			try{System.exit(1);
			}catch(Exception e){
				System.out.println("Thank you for visiting! Bye!");
			}
		}
		// validate format
		boolean valid = this.validate(string);
		// if empty string
		if(string.equals(null)){
			valid = false;
		}
		return valid;
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
