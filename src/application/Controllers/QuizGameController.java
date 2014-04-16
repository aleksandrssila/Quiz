package application.Controllers;

import java.util.ArrayList;
import java.util.List;
import core.UserInputManager;
import application.Models.QuizGameModel;
import application.Models.QuizModel;
import application.Entities.Answer;
import application.Entities.Question;
import application.Entities.Quiz;
import application.Entities.QuizGame;

public class QuizGameController {
	
	public  Quiz quiz;
	public  List<QuizGame> quizGame;
	private List<Quiz> quizList;
	private int score;
	/**
	 * 
	 */
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
	public boolean loadQuiz(){
		
		boolean correct = false;
		
		while(!correct){
			
			UserInputManager userMamager = new UserInputManager();
			
			int num = userMamager.askForInt();
			
			num--;			
			
				try{
					// selected quiz
					this.quiz = this.quizList.get(num);	
					// load quiz game 
					QuizGameModel getQuizGame = new QuizGameModel();
					this.quizGame = getQuizGame.getQuizGame(this.quiz);
					// break loop
					correct = true;
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
			UserInputManager inpMan = new UserInputManager();
			
			boolean nextQuestion 	= false;	
			
			while(!nextQuestion){
					
				int num = inpMan.askForInt();
								
				if(num >0){
					num--;
					try{
						Answer chosenAnsw = qgame.getAnswers().get(num);
						if(chosenAnsw.getId() == question.getAnswerId()){
							this.score++;
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
		
		System.out.println("Your score is: "+this.score);
		/**
		 * @TODO save the score
		 */	
	}

}
