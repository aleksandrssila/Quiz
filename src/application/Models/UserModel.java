package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.dbClass;
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
	public User loginUser(String username, String password){
		
		User user = new User();
		
		String query = 	"SELECT * FROM user " +
						"WHERE user_name = '"+username+"' "+
						"AND user_password = '"+password+"'";
				
		dbClass db = new dbClass();
		
		ResultSet result = db.dataEnquery(query);
		
		if(result != null){
			try {
				
				while (result.next()) {
					user.setId(result.getInt("user_id"));
					user.setName(result.getString("user_name"));
					user.setPassword(result.getString("user_password"));
				}
				
				db.closeConnection();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return user;
		
	}

}