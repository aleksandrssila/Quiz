package application.Controllers;

import java.util.ArrayList;
import java.util.List;
import application.Models.QuestionModel;
import application.Models.QuizGameModel;
import application.Entities.Question;
import application.Entities.Quiz;
import application.Entities.QuizGame;

public class QuestionController {
	
	
public static void main(String[] args) {
	
	Quiz quiz = new Quiz();
	quiz.setId(1);
	quiz.setName("Test");
	quiz.setOwner(1);
	List<QuizGame> quizGameTest = new ArrayList<QuizGame>();
	QuizGameModel test = new QuizGameModel();
	quizGameTest =  test.getQuizGame(quiz);	
	System.out.println(quizGameTest);
	
	
	
		
	
	/*
	 *  for(Question q:test.questionList) {
         System.out.println(q.getId());
         System.out.println(q.getAnswerId());
     }
		QuestionModel test = new QuestionModel();
		
		try{
			Question question = test.getQuestion(1);
			
			System.out.println(question.getId());
			System.out.println(question.getQuizId());
			System.out.println(question.getAnswerId());
			System.out.println(question.getQuestionText());
			
		}
		catch(NullPointerException e){
			System.out.println("There is no value in database with this id");
		}
		
		
		*/
	}

}
