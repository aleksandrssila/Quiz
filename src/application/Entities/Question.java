package application.Entities;

public class Question {
	
	private  	int 	id;
	private 	int 	quizId;
	private 	int 	answerId;
	private 	String 	questionText;
	/**
	 * 
	 * @param id
	 */
	public void setId(int id){
		this.id = id;
	}
	/**
	 * 
	 * @param quizId
	 */
	public void setQuizId(int quizId){
		this.quizId = quizId;
	}
	/**
	 * 
	 * @param answerId
	 */
	public void setAnswerId(int answerId){
		this.answerId = answerId;
	}
	/**
	 * 
	 * @param questionText
	 */
	public void setQuestionText(String questionText){
		this.questionText = questionText;
	}
	/**
	 * 
	 * @return
	 */
	public int getId(){
		return this.id;
	}
	/**
	 * 
	 * @return int
	 */
	public int getQuizId(){
		return this.quizId;
	}
	/**
	 * 
	 * @return int
	 */
	public int getAnswerId(){
		return this.answerId;
	}
	/**
	 * 
	 * @return String
	 */
	public String getQuestionText(){
		return this.questionText;
	}
	

}