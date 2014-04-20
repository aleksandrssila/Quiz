package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.Entities.Quiz;
import core.Global;

public class QuizModel {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Quiz getQuiz(int id){
		
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
	/**
	 * 
	 * @param name
	 * @return
	 */
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
	/**
	 * 
	 * @return
	 */
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
	/**
	 * 
	 * @param userid
	 * @return
	 */
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
	/**
	 * 
	 * @return
	 */
	public List<Quiz> getActiveQuizList(){
		
		List<Quiz> quizList = new ArrayList<Quiz>();
		
		
		String query = 	"SELECT * FROM quiz " +
						"WHERE quiz_status > 0 "+
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
	/**
	 * 
	 * @param newquiz
	 * @return
	 */
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
	/**
	 * 
	 * @param userid
	 * @return
	 */
	public List<Quiz> getUserQuizList(int userid){
		
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
	/**
	 * 
	 * @param quizid
	 * @param status
	 * @param userid
	 * @return
	 */
	public boolean updateQuizStatus(int quizid, int status, int userid){
				
		// update query
		String query = 	"UPDATE quiz " +
						"SET quiz_status ="+status+" "+
						"WHERE quiz_id="+quizid+" "+
						"AND quiz_owner ="+userid;
		// get results from db		
		boolean result = Global.dataBase.getInstance().updateEnquery(query);
		// close db connection
		Global.dataBase.getInstance().closeConnection();
		
		return result;
	}

}
