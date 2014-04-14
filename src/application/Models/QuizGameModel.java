package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.dbClass;
import application.Entities.Answer;
import application.Entities.Question;
import application.Entities.Quiz;
import application.Entities.QuizGame;

public class QuizGameModel{
	
	public List<QuizGame> getQuizGame(Quiz quiz){
		
		List<QuizGame> quizGame    = new ArrayList<QuizGame>();
		List<Question> questions   = new ArrayList<Question>();
		QuestionModel getQuestions = new QuestionModel();
		
		questions = getQuestions.getQuizQuestions(quiz.getId());
		
		 for(Question q:questions) {
			 List<Answer> answers    = new ArrayList<Answer>();
			 AnswerModel getAnswer   = new AnswerModel();
			QuizGame qgame           = new QuizGame();
			 
			 answers = getAnswer.getAnswersByQuestionId(q.getId());
			 
			 qgame.setQuestion(q);
			 qgame.setAnswers(answers);
			 
			 quizGame.add(qgame);	 		 
	     }
		
		
		return quizGame;
		
	}

}
