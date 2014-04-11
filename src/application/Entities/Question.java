package application.Entities;

public class Question {
	
	private  	int 	id;
	private 	int 	quizId;
	private 	int 	answerId;
	private 	String 	questionText;
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setQuizId(int quizId){
		this.quizId = quizId;
	}
	
	public void setAnswerId(int answerId){
		this.answerId = answerId;
	}
	
	public void setQuestionText(String questionText){
		this.questionText = questionText;
	}
	

}