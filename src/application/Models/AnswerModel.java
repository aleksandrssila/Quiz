package application.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import core.Global;
import application.Entities.Answer;

public class AnswerModel {
	
	
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

}
