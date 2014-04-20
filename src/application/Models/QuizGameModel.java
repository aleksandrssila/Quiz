package application.Models;

import java.util.ArrayList;
import java.util.List;
import core.Global;
import application.Entities.Answer;
import application.Entities.Question;
import application.Entities.Quiz;
import application.Entities.QuizGame;

public class QuizGameModel{
	/**
	 * 
	 * @param quiz
	 * @return
	 */
	public List<QuizGame> getQuizGame(Quiz quiz){
		
		List<QuizGame> quizGame    = new ArrayList<QuizGame>();
		List<Question> questions   = new ArrayList<Question>();
		QuestionModel getQuestions = new QuestionModel();
				
		questions = getQuestions.getQuizQuestions(quiz.getId());
		
		 for(Question q:questions) {
			 List<Answer> answers    = new ArrayList<Answer>();
			 AnswerModel getAnswer   = new AnswerModel();
			QuizGame qgame           = new QuizGame();
			 
			 answers = getAnswer.getAnswersByQuestionId(q.getId());
			 
			 qgame.setQuestion(q);
			 qgame.setAnswers(answers);
			 
			 quizGame.add(qgame);	 		 
	     }
		
		Global.dataBase.getInstance().closeConnection();
		 
		return quizGame;
		
	}
	/**
	 * 
	 * @param quizG
	 * @param rightAnsw
	 * @return
	 */
	public boolean insertQuizGame(QuizGame quizG, int rightAnsw){
		
		rightAnsw--;
		// tell moduls that database is in use
		Global.dataBase.getInstance().useDb();
		// add answers to db and set id
		for(Answer answer:quizG.answers){
			AnswerModel answM = new AnswerModel();
			answer.setId(answM.insertAnswer(answer).getId());
		}
		// get id of the right answer
		quizG.question.setAnswerId(quizG.answers.get(rightAnsw).getId());
		// question model
		QuestionModel questionM = new QuestionModel();
		quizG.question.setId(questionM.insertQuestion(quizG.question).getId());
		// update answer
		for(Answer answer:quizG.answers){
			AnswerModel answM = new AnswerModel();
			answM.updateAnswerQuestionId(answer, quizG.question.getId());
		}
		// put database not in use and close connection
		Global.dataBase.getInstance().notUseDb();
		Global.dataBase.getInstance().closeConnection();
		
		return ((quizG.question.getId() > 0)&&(quizG.question.getAnswerId() >0)? true: false);	
	}

}
