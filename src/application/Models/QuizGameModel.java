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
		
		List<QuizGame> quizGame = new ArrayList<QuizGame>();
		
		String query = "SELECT "+ 
			"quiz_questions.question_id as qs_id,"+
			"quiz_questions.answer_id as qs_answer_id,"+
			"quiz_questions.question_text as qs_text,"+
			"quiz_answers.answer_id as as_id,"+
			"quiz_answers.question_id as as_question_id,"+
			"quiz_answers.text as as_text "+
			"FROM quiz_questions "+
			"RIGHT JOIN quiz_answers "+
			"ON (quiz_questions.question_id = quiz_answers.question_id) "+
			"WHERE quiz_id = "+quiz.getId();
						
			dbClass db = new dbClass();
			ResultSet result = db.dataEnquery(query);
		
			if(result != null){
				try {
					
					QuizGame qgame 	  = new QuizGame();
					Question quest 	  = new Question();
					List <Answer> answers = new ArrayList<Answer>();
			
					while (result.next()) {
						
						Question question = new Question();
						Answer answer 	  = new Answer();
						
						if(qgame.getQuestion() == null){		
							qgame.setQuestion(quest);
							qgame.setAnswers(answers);
						}
						
						question.setId(result.getInt("qs_id"));
						question.setAnswerId(result.getInt("qs_answer_id"));
						question.setQuestionText(result.getString("qs_text"));
						question.setQuizId(quiz.getId());
						
						System.out.println(question.getId()+" : "+qgame.question.getId());
						
						
						if((qgame.question.getId() == 0)||(question.getId() == qgame.question.getId())){
									
							answer.setId(result.getInt("as_id"));
							answer.setQuestionId(result.getInt("as_question_id"));
							answer.setText(result.getString("as_text"));
							
							qgame.setQuestion(question);
							qgame.answers.add(answer);
													
						}
						else{
							
							qgame.setQuestion(quest);
							quizGame.add(qgame);
							
						}	
							
					}
					
					quizGame.add(qgame);
					db.closeConnection();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		return quizGame;
		
	}

}
