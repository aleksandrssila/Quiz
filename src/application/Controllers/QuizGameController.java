package application.Controllers;

import java.util.ArrayList;
import java.util.List;

import core.UserInputManager;
import application.Models.QuestionModel;
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
	
	public void getAllQuizez(){
		
		this.quizList = new ArrayList<Quiz>();
		
		QuizModel quizM = new QuizModel();
		quizList = quizM.getActiveQuizList();
		
		System.out.println("");
		System.out.println("Please chose from following :");
		System.out.println("");
			
		for(Quiz q:quizList){
			System.out.println("Quiz id: "+q.getId()+" Quiz name: "+q.getName());
		}
		
	}
	
	public void loadQuiz(){
		
		UserInputManager userMamager = new UserInputManager();
		int id = userMamager.askForInt();
		
		if(id > 0){
			
			try{
				
				for(Quiz q:quizList){
					if(q.getId() == id){
						
						this.quiz = new Quiz();
						this.quiz.setId(q.getId());
						this.quiz.setName(q.getName());
						this.quiz.setOwner(q.getOwner());
						
						QuizGameModel quizgM = new QuizGameModel();
						this.quizGame = quizgM.getQuizGame(this.quiz);
						this.score    = 0;
						break;
					}
				}
				
			}
			catch(NullPointerException e){
				System.out.println("Internal error, please restart quiz");
			}
			
		}
		
	}
	
	public void playGame(){
		
		int $i = 1;
		
		for(QuizGame qgame:this.quizGame){
			
			Question question = new Question();
			question = qgame.getQuestion();
			System.out.println("");
			System.out.println("::::::::::::::::::::::::::");
			System.out.println("::::: QUESTION "+$i+":::::::::");
			System.out.println("::::::::::::::::::::::::::");
			System.out.println("");
			System.out.println(question.getQuestionText());
			System.out.println("");
			System.out.println("Please chose Answer number: ");
			
			for(Answer answer:qgame.getAnswers()){
				System.out.print("Answer id: "+answer.getId());
				System.out.println(answer.getText());		
			}
			
			UserInputManager inpMan = new UserInputManager();
			
			boolean nextQuestion 	= false;	
			
			while(!nextQuestion){
				
				int num = inpMan.askForInt();
				
				if(num >0){
					for(Answer answer:qgame.getAnswers()){
						System.out.print(answer.getId()+" : "+num);
						if(num == answer.getId()){
							if(num == question.getAnswerId()){
								this.score++;
							}
							nextQuestion = true;
							break;
						}
						else{
							System.out.println("Chose number from available options");
						}		
					}	
				}
			}
			
			System.out.println("Your score is: "+this.score);
		}
		
	}

}
