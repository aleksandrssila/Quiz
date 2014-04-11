package application.Controllers;

import java.util.ArrayList;
import java.util.List;
import application.Models.QuestionModel;
import application.Entities.Question;

public class QuestionController {
	
	
public static void main(String[] args) {
	
	QuestionModel test = new QuestionModel();
	test.getQuizQuestions(1);
	
	 for(Question q:test.questionList) {
         System.out.println(q.getId());
         System.out.println(q.getAnswerId());
     }
		
	
	/*
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
