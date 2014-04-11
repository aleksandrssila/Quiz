package core;

import java.sql.*;

public class dbClass {
	
	private Connection conn;
	private String url;
	private String dbName;
	private String driver;
	private String userName;
	private String password;
	
	public dbClass() {
		
		this.url 	  = "jdbc:mysql://107.170.44.145:3306/";
		this.dbName   = "quiz_game";
		this.driver   = "com.mysql.jdbc.Driver";
		this.userName = "quizgame"; 
		this.password = "birkbeck";
	    
	}
	
	public static void main(String[] args) {
		
	}
	
	
	
	public ResultSet dataEnquery(String query){
				
		try {
			
			try {
				Class.forName(this.driver).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			// connect to db
			
			this.conn = DriverManager.getConnection(this.url+this.dbName,this.userName,this.password);
			Statement st 		= conn.createStatement();
			// get the result
			ResultSet result 	= st.executeQuery(query);
			// close connection
			
			return result;	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 	

	} 
	
	public void closeConnection() throws SQLException{
		this.conn.close();
	}

}