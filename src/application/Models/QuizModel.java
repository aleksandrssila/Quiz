package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.Entities.Quiz;
import core.Global;

public class QuizModel {
	
public Quiz getQuiz(int id) throws NullPointerException{
		
		Quiz quiz = new Quiz();
		// query 		
		String query = 	"SELECT * FROM quiz" +
						"WHERE quiz_id = '"+id+"'";	
		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();

		try {

			while (result.next()) {
				quiz.setId(result.getInt("quiz_id"));
				quiz.setName(result.getString("quiz_name"));
				quiz.setOwner(result.getInt("quiz_owner"));
				quiz.setOwner(result.getInt("quiz_status"));
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		if(quiz.getId() == 0){
			Global.dataBase.getInstance().closeConnection();
			throw new NullPointerException("Not found in datbase");
		}
		
		Global.dataBase.getInstance().closeConnection();
		
		return quiz;
		
	}

	public boolean quizExistOnSystem(String name){
		// query 		
		String query = 	"SELECT * FROM quiz " +
						"WHERE quiz_name = '"+name+"'";	
		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		boolean check = false;
	
		try {
	
			check = result.next();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Global.dataBase.getInstance().closeConnection();
		
		return check;
		
	}


	public List<Quiz> getQuizList(){
		
		List<Quiz> quizList = new ArrayList<Quiz>();
		
		
		String query = 	"SELECT * FROM quiz ORDER BY quiz_id";
		
				
		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		if(result != null){
			try {

				while (result.next()) {
					Quiz quiz = new Quiz();
					quiz.setId(result.getInt("quiz_id"));
					quiz.setName(result.getString("quiz_name"));
					quiz.setOwner(result.getInt("quiz_owner"));
					quiz.setStatus(result.getInt("quiz_status"));
					
					quizList.add(quiz);
				}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Global.dataBase.getInstance().closeConnection();
		
		return quizList;
	}
	
	public List<Quiz> getUserList(int userid){
		
		List<Quiz> quizList = new ArrayList<Quiz>();
		
		
		String query = 	"SELECT * FROM quiz " +
						"WHERE quiz_owner ="+userid+" "+
						"ORDER BY quiz_id";
		
				
		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		if(result != null){
			try {

				while (result.next()) {
					Quiz quiz = new Quiz();
					quiz.setId(result.getInt("quiz_id"));
					quiz.setName(result.getString("quiz_name"));
					quiz.setOwner(result.getInt("quiz_owner"));
					quiz.setStatus(result.getInt("quiz_status"));
					
					quizList.add(quiz);
				}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Global.dataBase.getInstance().closeConnection();
		
		return quizList;
	}
	
	public List<Quiz> getActiveQuizList(){
		
		List<Quiz> quizList = new ArrayList<Quiz>();
		
		
		String query = 	"SELECT * FROM quiz " +
						"WHERE quiz_status IS NOT NULL "+
						"ORDER BY quiz_id";
		
				
		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		if(result != null){
			try {

				while (result.next()) {
					Quiz quiz = new Quiz();
					quiz.setId(result.getInt("quiz_id"));
					quiz.setName(result.getString("quiz_name"));
					quiz.setOwner(result.getInt("quiz_owner"));
					quiz.setStatus(result.getInt("quiz_status"));
				
					quizList.add(quiz);
				}
				
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Global.dataBase.getInstance().closeConnection();
		
		return quizList;
	}
	
	public Quiz createQuiz(Quiz newquiz){
		
		String query = 	"INSERT INTO quiz VALUES (NULL,'"+newquiz.getName()+"','"+newquiz.getOwner()+"',NULL)";
				
		// get results from db		
		Global.dataBase.getInstance().insertEnquery(query);

		try {
			ResultSet keys = Global.dataBase.getInstance().getStatement().getGeneratedKeys();
			if(keys.next()){
				newquiz.setId(keys.getInt(1));
			}  
		} catch (SQLException e) {
			System.out.println("Name exist, please chose different");
		} 
		
		Global.dataBase.getInstance().closeConnection();
		
		return newquiz;
		
	}

}
