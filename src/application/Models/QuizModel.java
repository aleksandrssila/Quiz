package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.Entities.Quiz;
import core.dbClass;

public class QuizModel {
	
public Quiz getQuiz(int id) throws NullPointerException{
		
		Quiz quiz = new Quiz();
				
		String query = 	"SELECT * FROM quiz" +
						"WHERE quiz_id = '"+id+"'";
		
				
		dbClass db = new dbClass();
		ResultSet result = db.dataEnquery(query);
		
		if(result != null){
			try {

				while (result.next()) {
					quiz.setId(result.getInt("quiz_id"));
					quiz.setName(result.getString("quiz_name"));
					quiz.setOwner(result.getInt("quiz_owner"));
				}
				
				db.closeConnection();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(quiz.getId() == 0){
			throw new NullPointerException("Not found in datbase");
		}
		
		return quiz;
		
	}

}
