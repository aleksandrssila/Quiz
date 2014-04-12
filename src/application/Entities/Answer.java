package application.Entities;

public class Answer {
	
	private int 	id;
	private int 	questionId;
	private String  text;
	/**
	 * 
	 * @param id
	 */
	public void setId(int id){
		this.id = id;
	}
	/**
	 * 
	 * @param questionId
	 */
	public void setQuestionId(int questionId){
		this.questionId = questionId;
	}
	/**
	 * 
	 * @param text
	 */
	public void setText(String text){
		this.text = text;
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
	 * @return
	 */
	public int getQuestionId(){
		return this.questionId;
	}
	/**
	 * 
	 * @return
	 */
	public String getText(){
		return this.text;
	}

}
