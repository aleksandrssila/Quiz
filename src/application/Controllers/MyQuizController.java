package application.Controllers;

import java.util.ArrayList;
import java.util.List;

import application.Entities.Answer;
import application.Entities.Quiz;
import application.Entities.QuizGame;
import application.Entities.Result;
import application.Entities.User;
import application.Models.QuizGameModel;
import application.Models.QuizModel;
import application.Models.UserModel;
import core.Global;
import core.UserInputManager;

public class MyQuizController {
	
	private QuizGameController qgameC;
	private UserController     userC;
	
	public static void main(String [] args){
	/*
		MyQuizController test = new MyQuizController();
		test.userC.user.setId(1);
		test.userC.user.setName("test");
		test.deactivateUserQuizez(test.userC.user.getId());
	*/
	}
	
	public MyQuizController(){
		this.qgameC = new QuizGameController();
		this.userC  = new UserController();
	}
	
	public void seeQuizScore(User user){
		
		List<Quiz> quizes = new ArrayList<Quiz>(); 
		// initialise quiz model
		QuizModel quizM = new QuizModel();
		// list of all user quizes
		quizes = quizM.getUserList(user.getId());
		
		int i = 1;
		
		try{
			if(quizes.get(0).getId()>0){
				UserInputManager inpM = new UserInputManager("INTIGER_PATTERT");
				
				for(Quiz quiz:quizes){
					inpM.setOption(Integer.toString(i));
					System.out.println(i+" "+quiz.getName()+" status: "+((quiz.getStatus()>0)? "active": "disabled"));
					i++;
				}
				
				List<Result> results = new ArrayList<Result>();
				UserModel userM 	 = new UserModel();
				// get all results for that quiz
				inpM.setMessage("");
				results = userM.getUserResultsByQuizId(quizes.get(Integer.parseInt(inpM.askAction())-1).getId());
				// show all players score
				try{
					if(results.get(0).getId()>0){
						for(Result res:results){
							System.out.println("score: "+res.getScore()+" Username: "+res.getUsername());
						}
					}
				}// if no players were found
				catch(IndexOutOfBoundsException e){
					System.out.println("There are no players to show!");
				}
			}
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("There are no quizez to show!");
		}	
	}	
	/**
	 * Creates quiz  
	 * @param user
	 */
	public void createQuiz(User user){		
		// set user to user controller
		this.userC.setUser(user);
		// initialise quiz model
		QuizModel quizM = new QuizModel();
		// initialise quiz game model
		QuizGameModel qgameM = new QuizGameModel();
		boolean duplacateName = true;
		// while quiz name is not unique
		while(duplacateName){
			// Initialise user input manager
			UserInputManager inpM = new UserInputManager("USER_PATTERN");
			// set quiz name 
			inpM.setMessage("Please type the name of your quiz: ");
			this.qgameC.quiz.setName(inpM.askString());
			this.qgameC.quiz.setOwner(user.getId());
			this.qgameC.quiz.setStatus(0);
			// check name in database
			duplacateName = quizM.quizExistOnSystem(this.qgameC.quiz.getName());
			if(duplacateName){
				System.out.println("There is an duplicate, please try different");
			}
		}
		
		UserInputManager inpM = new UserInputManager("QUESTION_PATTERN");
		inpM.setMessage("- Would you like to add question ?(type no to stop adding questions) - ");
		inpM.setOption("yes");
		inpM.setOption("no");
		
		boolean addquiz = true;
		
		while(inpM.askAction().equals("yes")){
			
			QuizGame qGame = new QuizGame();
			UserInputManager inpQ = new UserInputManager("QUESTION_PATTERN");
			
			inpQ.setMessage("Please type the question: ");
			qGame.question.setQuestionText(inpQ.askString());
			
			int ans = 1;
			// there should be minimum 4 answers 
			while(ans < 5){
				// show message
				inpQ.setMessage("Please type the answer ("+ans+" out of 4): ");
				// set answer
				Answer answer = new Answer();
				answer.setText(inpQ.askString());
				// add answers to list
				qGame.answers.add(answer);
			
				ans++;
			}
			
			boolean check = false;
			int correct = 0;
			
			while(!check){
				inpQ.setMessage("Please type correct answer: ");
				correct = inpQ.askForInt();
				// if number is 1-4
				if((correct>0)&&(correct<5)){
					check = true;
				}else{
					System.out.println("Answer can be 1-4");
					check = false;
				}
			}
			// tell to class that it will be used
			Global.dataBase.getInstance().useDb();
			// create new quiz, and get id
			this.qgameC.quiz.setId(quizM.createQuiz(this.qgameC.quiz).getId());
			// set question quiz id
			qGame.question.setQuizId(this.qgameC.quiz.getId());
			// quiz was inserted
			boolean status = qgameM.insertQuizGame(qGame, correct);
			// not use db
			Global.dataBase.getInstance().notUseDb();
			// close connection
			Global.dataBase.getInstance().closeConnection();
			if(status){
				System.out.println("Question was added!");
				addquiz = false;
			}else{
				System.out.println("There was an error adding your qustion");
				addquiz = false;
			}
			
		}
	}
	
	
	public void manageQuizUserQuizez(int userid){
		
		List<Quiz> quizes = new ArrayList<Quiz>(); 
		// initialise quiz model
		QuizModel quizM = new QuizModel();
		// list of all user quizes
		quizes = quizM.getUserQuizList(userid);
		
		try{
			// check if the are any quiz games
			quizes.get(0).getId();
			int i = 1;
			
			UserInputManager inpM = new UserInputManager("INTIGER_PATTERT");
			
			for(Quiz quiz:quizes){
				inpM.setOption(Integer.toString(i));
				System.out.println(i+" "+quiz.getName()+" status: "+((quiz.getStatus()>0)? "active": "disabled"));
				i++;
			}
			
			inpM.setMessage("");
			int input = Integer.parseInt(inpM.askAction())-1;
			
			inpM.setMessage("What you would like todo?");
			inpM.cleanOptions();
			inpM.setOption("activate");
			inpM.setOption("disable");
			
			String answer = inpM.askAction();
			// activate quiz
			if(answer.equals("activate")){
				if(quizes.get(input).getStatus()>0){
					System.out.println("Quiz is already active");
				}
				else{
					boolean updated = quizM.updateQuizStatus(quizes.get(input).getId(),1, userid);
					if(updated){
						System.out.println("Quiz status was updated!");
					}else{
						System.out.println("There was an error!");
					}
				}
			}
			// disable quiz
			if(answer.equals("disable")){
				if(quizes.get(input).getStatus()==0){
					System.out.println("Quiz is already disabled");
				}
				else{
					boolean updated = quizM.updateQuizStatus(quizes.get(input).getId(),0, userid);
					if(updated){
						System.out.println("Quiz status was updated!");
						/**
						 * Show list of all users played
						 */
						List<Result> results = new ArrayList<Result>();
						UserModel userM = new UserModel();
						results = userM.getUserResultsByQuizId(quizes.get(input).getId());
						// show all players score
						try{
							if(results.get(0).getId()>0){
								for(Result res:results){
									System.out.println("score: "+res.getScore()+" Username: "+res.getUsername());
								}
							}
						}// if no players were found
						catch(IndexOutOfBoundsException e){
							System.out.println("There are no players to show!");
						}	
					}else{
						System.out.println("There was an error!");
					}
				}
			}
			
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("You have no quizez");
		}
	}
}
