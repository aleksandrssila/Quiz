package application.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.dbClass;
import application.Entities.User;

public class UserModel {
		
	public static void main(String[] args) {
		
		UserModel test = new UserModel();
		User user = test.loginUser("test", "test");
		
		System.out.println(user.getId());
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		
	}
	
	public User loginUser(String username, String password){
		
		User user = new User();
		
		String query = 	"SELECT * FROM user " +
						"WHERE user_name = '"+username+"' "+
						"AND user_password = '"+password+"'";
		
		System.out.println(query);
		
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