package application.Entities;

import java.util.List;

public class QuizGame {
	
	public Question question;
	public List<Answer> answers;
	/**
	 * 
	 * @param answers
	 */
	public void setAnswers(List<Answer> answers){
		this.answers = answers;
	}
	/**
	 * 
	 * @param question
	 */
	public void setQuestion(Question question){
		this.question = question;
	}
	/**
	 * 
	 * @return
	 */
	public Question getQuestion(){
		return this.question;
	}
	/**
	 * 
	 * @return
	 */
	public List<Answer> getAnswers(){
		return this.answers;
	}

}
