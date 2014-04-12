package application.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import core.dbClass;
import application.Entities.Question;

public class QuestionModel {
	
	public Question getQuestion(int id) throws NullPointerException{
		
		Question question = new Question();
				
		String query = 	"SELECT * FROM quiz_questions " +
						"WHERE question_id = '"+id+"'";
		
				
		dbClass db = new dbClass();
		ResultSet result = db.dataEnquery(query);
		
		if(result != null){
			try {

				while (result.next()) {
					question.setId(result.getInt("question_id"));
					question.setAnswerId(result.getInt("answer_id"));
					question.setQuizId(result.getInt("quiz_id"));
					question.setQuestionText(result.getString("question_text"));
				}
				
				db.closeConnection();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(question.getId() == 0){
			throw new NullPointerException("Not found in datbase");
		}
		
		return question;
		
	}
	
	public List<Question> getQuizQuestions(int quizId){
		
		List<Question> questionList = new ArrayList<Question>();
		
		String query = 	"SELECT * FROM quiz_questions " +
						"WHERE quiz_id = '"+quizId+"'";
		
		dbClass db = new dbClass();
		ResultSet result = db.dataEnquery(query);
		
		if(result != null){
			try {

				while (result.next()) {
					Question question = new Question();
					question.setId(result.getInt("question_id"));
					question.setAnswerId(result.getInt("answer_id"));
					question.setQuizId(result.getInt("quiz_id"));
					question.setQuestionText(result.getString("question_text"));
					
					questionList.add(question);
				}
				
				db.closeConnection();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return questionList;
				
	}

}
