package application.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import core.Global;
import application.Entities.Answer;

public class AnswerModel {
	/**
	 * 
	 * @param questionId
	 * @return
	 */
	public List<Answer> getAnswersByQuestionId(int questionId){
		
		List<Answer> answers = new ArrayList<Answer>();
		
		
		String query = 	"SELECT * FROM quiz_answers " +
				"WHERE question_id = '"+questionId+"'"+
				"ORDER BY answer_id";

		
		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		try {
	
			while (result.next()) {
				
				Answer answer = new Answer();
				answer.setId(result.getInt("answer_id"));
				answer.setQuestionId(result.getInt("question_id"));
				answer.setText(result.getString("text"));
				
				answers.add(answer);
			}			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	    Global.dataBase.getInstance().closeConnection();
		
		return answers;
		
	}
	/**
	 * 
	 * @param answer
	 * @return
	 */
	public Answer insertAnswer(Answer answer){
		
		String query = 	"INSERT INTO quiz_answers " +
						"VALUES (NULL,'"+answer.getQuestionId()+"','"+answer.getText()+"')";
				
		// get results from db		
		Global.dataBase.getInstance().insertEnquery(query);

		try {
			ResultSet keys = Global.dataBase.getInstance().getStatement().getGeneratedKeys();
			if(keys.next()){
				answer.setId(keys.getInt(1));
			}  
		} catch (SQLException e) {
			System.out.println("Name exist, please chose different");
		} 
		
		Global.dataBase.getInstance().closeConnection();
		
		return answer;
		
	}
	/**
	 * 
	 * @param answer
	 * @param questionid
	 * @return
	 */
	public Answer updateAnswerQuestionId(Answer answer, int questionid){
		
		String query = 	"UPDATE quiz_answers " +
						"SET question_id ="+questionid+" " +
						"WHERE answer_id="+answer.getId();
		
		// get results from db		
		Global.dataBase.getInstance().insertEnquery(query);

		try {
			ResultSet keys = Global.dataBase.getInstance().getStatement().getGeneratedKeys();
			if(keys.next()){
				answer.setId(keys.getInt(1));
			}  
		} catch (SQLException e) {
			System.out.println("Name exist, please chose different");
		} 
		
		Global.dataBase.getInstance().closeConnection();
		
		return answer;
		
	}

}
