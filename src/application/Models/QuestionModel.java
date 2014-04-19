package application.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import core.Global;
import application.Entities.Question;

public class QuestionModel {
	
	public Question getQuestion(int id) throws NullPointerException{
		
		Question question = new Question();
				
		String query = 	"SELECT * FROM quiz_questions " +
						"WHERE question_id = '"+id+"'";
		
				
		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getResult();
		
		if(result != null){
			try {

				while (result.next()) {
					question.setId(result.getInt("question_id"));
					question.setAnswerId(result.getInt("answer_id"));
					question.setQuizId(result.getInt("quiz_id"));
					question.setQuestionText(result.getString("question_text"));
				}
				
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
		
		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		try {

			while (result.next()) {
				
				Question question = new Question();
				question.setId(result.getInt("question_id"));
				question.setAnswerId(result.getInt("answer_id"));
				question.setQuizId(result.getInt("quiz_id"));
				question.setQuestionText(result.getString("question_text"));
				
				questionList.add(question);
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		Global.dataBase.getInstance().closeConnection();
		
		return questionList;
				
	}
	
	public Question insertQuestion(Question question){
		
		String query = 	"INSERT INTO quiz_questions " +
						"VALUES (NULL,"+question.getQuizId()+",'"+question.getAnswerId()+"','"+question.getQuestionText()+"')";

		// get results from db		
		Global.dataBase.getInstance().insertEnquery(query);

		try {
			ResultSet keys = Global.dataBase.getInstance().getStatement().getGeneratedKeys();
			if(keys.next()){
				question.setId(keys.getInt(1));
			}  
		} catch (SQLException e) {
			System.out.println("Name exist, please chose different");
		} 
		
		Global.dataBase.getInstance().closeConnection();
		
		return question;
		
	}

}
