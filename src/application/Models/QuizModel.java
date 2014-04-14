package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Entities.Question;
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


	public List<Quiz> getQuizList(){
		
		List<Quiz> quizList = new ArrayList<Quiz>();
		
		
		String query = 	"SELECT * FROM quiz ORDER BY quiz_id";
		
				
		dbClass db = new dbClass();
		ResultSet result = db.dataEnquery(query);
		
		if(result != null){
			try {

				while (result.next()) {
					Quiz quiz = new Quiz();
					quiz.setId(result.getInt("quiz_id"));
					quiz.setName(result.getString("quiz_name"));
					quiz.setOwner(result.getInt("quiz_owner"));
					
					quizList.add(quiz);
				}
				
				db.closeConnection();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return quizList;
	}

}
