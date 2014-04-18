package application.Entities;
/**
 * Result Entity is a class for storing `user_result` data
 */
public class Result {
	
	private int id;
	private int userId;
	private int quizId;
	private String username;
	private String data;
	private int score;
	
	
	/**
	 * Set username
	 * @param username
	 */
	public void setUsername(String username){
		this.username = username;
	}
	/**
	 * Set result id
	 * @param id int
	 */
	public void setId(int id){
		this.id = id;
	}
	/**
	 * Set user id
	 * @param id int
	 */
	public void setUserId(int userid){
		this.userId = userid;
	}
	/**
	 * Set quiz id
	 * @param quizid int
	 */
	public void setQuizId(int quizid){
		this.quizId = quizid;
	}
	/**
	 * Set data about result
	 * @param data
	 */
	public void setData(String data){
		this.data = data;
	}
	/**
	 * Set user score 
	 * @param score int
	 */
	public void setScore(int score){
		this.score = score;
	}
	/**
	 * Get result id
	 * @return int
	 */
	public int getId(){
		return this.id;
	}
	/**
	 * Get user id
	 * @return int
	 */
	public int getUserId(){
		return this.userId;
	}
	/**
	 * Get quiz id
	 * @return int
	 */
	public int getQuizId(){
		return this.quizId;
	}
	/**
	 * Get data 
	 * @return String
	 */
	public String getData(){
		return this.data;
	}
	/**
	 * Get user score
	 * @return int
	 */
	public int getScore(){
		return this.score;
	}
	/**
	 * Get username
	 * @return
	 */
	public String getUsername(){
		return this.username;
	}

}
