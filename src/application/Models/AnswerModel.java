package application.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import core.dbClass;
import application.Entities.Answer;

public class AnswerModel {
	
	
	public List<Answer> getAnswersByQuestionId(int questionId){
		
		List<Answer> answers = new ArrayList<Answer>();
		
		
		String query = 	"SELECT * FROM quiz_answers " +
				"WHERE question_id = '"+questionId+"'"+
				"ORDER BY answer_id";

		dbClass db = new dbClass();
		ResultSet result = db.dataEnquery(query);
		
		if(result != null){
			try {
		
				while (result.next()) {
					
					Answer answer = new Answer();
					answer.setId(result.getInt("answer_id"));
					answer.setQuestionId(result.getInt("question_id"));
					answer.setText(result.getString("text"));
					
					answers.add(answer);
				}
				
				db.closeConnection();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return answers;
		
	}

}
