package application.Controllers;

import java.util.ArrayList;
import java.util.List;
import core.UserInputManager;
import application.Models.QuizGameModel;
import application.Models.QuizModel;
import application.Models.UserModel;
import application.Entities.Answer;
import application.Entities.Question;
import application.Entities.Quiz;
import application.Entities.QuizGame;
import application.Entities.Result;
import application.Entities.User;

public class QuizGameController {
	
	public  Quiz quiz;
	public  List<QuizGame> quizGame;
	private Result result;
	private List<Quiz> quizList;
	/**
	 * 
	 */
	public QuizGameController(){
		this.quiz   = new Quiz();
		this.result = new Result();
	}
	public void getAllQuizez(){
		
		this.quizList = new ArrayList<Quiz>();
		
		QuizModel quizM = new QuizModel();
		quizList = quizM.getActiveQuizList();
		
		System.out.println("*****************************");
		System.out.println("Please chose you quizz      :");
		System.out.println("*****************************");
		// print all quizzes 	
		for(Quiz q:quizList){
			System.out.println("Nr: "+q.getId()+" | Name: "+q.getName());
			System.out.println("------------------------------------------");
		}
		
	}
	/**
	 * 
	 */
	public boolean loadQuiz(User user){
		
		boolean correct = false;
		
		while(!correct){
			
			UserInputManager userMamager = new UserInputManager("INTIGER_PATTERT");
			
			int num = userMamager.askForInt();
			
			num--;			
			
				try{
					// selected quiz
					this.quiz = this.quizList.get(num);	
					
					// check if user have played this quiz
					UserModel userM = new UserModel();
					this.result = userM.getUserResultByQuizId(this.quiz.getId(), user.getId());
					// if result exist in database
					if(this.result.getId() > 0){
						// show message
						System.out.println("");
						System.out.println("You have played this quiz");
						System.out.println("Score : "+this.result.getScore());
						System.out.println("Data  : "+this.result.getData());
						System.out.println("");
					}
					else{
						// load quiz game 
						QuizGameModel getQuizGame = new QuizGameModel();
						this.quizGame = getQuizGame.getQuizGame(this.quiz);
						// break loop
						correct = true;
					}
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("This number is wrong");
				}	
		}	
		
		return correct;
	}
	/**
	 * 
	 */
	public void playGame(){
		// question number
		int $i = 1;
		int finalScore = 0;
		// show quiz question and answers
		for(QuizGame qgame:this.quizGame){
			/** VAR question Question **/
			Question question = new Question();
			question = qgame.getQuestion();
			// print question
			System.out.println("");
			System.out.println("::::::::::::::::::::::::::");
			System.out.println("::::: QUESTION "+$i+ ":::::::::");
			System.out.println("::::::::::::::::::::::::::");
			System.out.println("");
			System.out.println(question.getQuestionText());
			System.out.println("");
			System.out.println("Please select Nr, from following answers: ");
			// next question number
			$i++;	
			// first answer number
			int a = 1;
			// print all available answers
			for(Answer answer:qgame.getAnswers()){
				System.out.print("Nr: "+a);
				System.out.println(" | "+answer.getText());		
				a++;
			}
			/** VAR inpMan UserInputManager**/
			UserInputManager inpMan = new UserInputManager("USERNAME_PATTERN");
			
			boolean nextQuestion 	= false;	
			
			while(!nextQuestion){
					
				int num = inpMan.askForInt();
								
				if(num >0){
					num--;
					try{
						Answer chosenAnsw = qgame.getAnswers().get(num);
						if(chosenAnsw.getId() == question.getAnswerId()){
							finalScore++;
							nextQuestion = true;
						}else{
							nextQuestion = true;
						}
					}
					catch(IndexOutOfBoundsException e){
						System.out.println("This number is wrong");
					}
								
				}
			}
			
		}
		this.result.setScore(finalScore);
		System.out.println("Your score is: "+this.result.getScore());
		/**
		 * @TODO save the score
		 */	
	}

}
