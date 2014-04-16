package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.Global;
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

}