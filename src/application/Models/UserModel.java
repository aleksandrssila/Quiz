package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Global;
import application.Entities.Question;
import application.Entities.Result;
import application.Entities.User;

public class UserModel {
	
	private List<User> userList;
	/**
	 * 
	 * @return
	 */
	public List<User> getUserList(){
		return this.userList;
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @return User
	 */
	public User getUserByLogin(String username){
		
		User user = new User();
		
		String query = 	"SELECT * FROM user " +
						"WHERE user_name = '"+username+"'";
		

		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		try {
			
			while(result.next()) {
				user.setId(result.getInt("user_id"));
				user.setName(result.getString("user_name"));
				user.setPassword(result.getString("user_password"));
			}			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
					
		Global.dataBase.getInstance().closeConnection();
				
		return user;
		
	}
	
	public Result getUserResultByQuizId(int quizId, int userId){
		
		Result userR = new Result();
		// select result from `user_result`
		String query = 	"SELECT * FROM user_result " +
						"WHERE user_id = '"+userId+"' " +
						"AND quiz_id ='"+quizId+"'";
		

		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		try {
			// get data from returned result
			while(result.next()) {
				// set result class
				userR.setId(result.getInt("result_id"));
				userR.setUserId(result.getInt("user_id"));
				userR.setQuizId(result.getInt("quiz_id"));
				userR.setData(result.getString("data"));
				userR.setScore(result.getInt("score"));
			}			
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// close database connection			
		Global.dataBase.getInstance().closeConnection();
				
		return userR;
		
	}
	
	public User createUser(User newuser){
				
		String query = 	"INSERT INTO user VALUES (NULL,'"+newuser.getName()+"','"+newuser.getPassword()+"')";
				
		// get results from db		
		Global.dataBase.getInstance().insertEnquery(query);

		try {
			ResultSet keys = Global.dataBase.getInstance().getStatement().getGeneratedKeys();
			if(keys.next()){
				newuser.setId(keys.getInt(1));
			}  
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		Global.dataBase.getInstance().closeConnection();
		
		return newuser;
		
	}
	
	public List<Result> getUserResultsByQuizId(int quizId){
		
		List<Result> results = new ArrayList<Result>();
		
		// select result from `user_result`
		String query = 	"SELECT "+
							"user_result.result_id as id,"+
							"user_result.quiz_id as quiz_id,"+
							"user.user_id as user_id,"+
							"user.user_name as user_name,"+
							"user_result.data as data,"+
							"user_result.score as score "+
						"FROM user_result "+
						"INNER JOIN user "+
						"ON user.user_id = user_result.user_id "+
						"WHERE user_result.quiz_id = "+quizId+" "+
						"ORDER BY user_result.score DESC";

		// get results from db		
		Global.dataBase.getInstance().dataEnquery(query);
		ResultSet result = Global.dataBase.getInstance().getResult();
		
		try {
			// get data from returned result
			while(result.next()) {
				Result userR = new Result();
				// set result class
				userR.setId(result.getInt("id"));
				userR.setQuizId(result.getInt("quiz_id"));
				userR.setUserId(result.getInt("user_id"));
				userR.setUsername(result.getString("user_name"));
				userR.setData(result.getString("data"));
				userR.setScore(result.getInt("score"));
				// add result
				results.add(userR);
			}			
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// close database connection			
		Global.dataBase.getInstance().closeConnection();
				
		return results;
		
	}
	
	public Result insertUserResult(Result result){
		
		String query =  "INSERT INTO user_result "+
						"VALUES(NULL,"+result.getUserId()+","+result.getQuizId()+",'"+result.getData()+"',"+result.getScore()+")";

		// get results from db		
		Global.dataBase.getInstance().insertEnquery(query);

		try {
			ResultSet keys = Global.dataBase.getInstance().getStatement().getGeneratedKeys();
			if(keys.next()){
				result.setId(keys.getInt(1));
			}  
		} catch (SQLException e) {
			System.out.println("Name exist, please chose different");
		} 
		
		Global.dataBase.getInstance().closeConnection();
		
		return result;
		
	}
	
}