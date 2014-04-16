package core;

import java.sql.*;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
/**
 * 
 * Class dbClass (singleton pattern)
 *
 */
public class dbClass {
	
	
	public dbClass instance = null;
	private boolean isUsed 		= false;
	private boolean isConnected = false;
	private Connection conn;
	private ResultSet result;
	private PreparedStatement st;
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
	/**
	 * 
	 * @return
	 */
	public dbClass getInstance(){
		
	      if(this.instance == null) {
	         this.instance = new dbClass();
	      }
	        
	      return this.instance;
	 }
	/**
	 * 
	 * @param query
	 */
	public void dataEnquery(String query){
		
		 if(!this.isConnected){
				
				try {	
					Class.forName(this.driver).newInstance();
					this.conn 		= DriverManager.getConnection(this.url+this.dbName,this.userName,this.password);

				} 
				catch (Exception e) {
					e.printStackTrace();		
				}
				
				this.isConnected = true;

			}
				
		try {
			
			this.st  	= this.conn.prepareStatement(query);
			this.result = this.st.executeQuery();
							
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 	

	}
	/**
	 * 
	 * @param query
	 */
	public void insertEnquery(String query){
		
		 if(!this.isConnected){
				
				try {	
					Class.forName(this.driver).newInstance();
					this.conn 		= DriverManager.getConnection(this.url+this.dbName,this.userName,this.password);

				} 
				catch (Exception e) {
					e.printStackTrace();		
				}
				
				this.isConnected = true;

			}
		
		
		try {
			this.st = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  
			// get the result
			this.st.executeUpdate();
							
		}
		catch (Exception e) {
			System.out.println("Please chose different login");
		} 	
	} 
	/**
	 * 
	 */
	public void closeConnection(){
		if(!this.isUsed){
			
			this.isConnected = false;
			
			try {
				this.conn.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Set database is used
	 */
	public void useDb(){
		this.isUsed = true;
	}
	/**
	 * Set database not in use
	 */
	public void notUseDb(){
		this.isUsed = false;
	}
	/**
	 * 
	 * @return
	 */
	public ResultSet getResult(){
		return this.result;
	}
	/**
	 * 
	 * @return
	 */
	public PreparedStatement getStatement(){
		return this.st;
	}

}