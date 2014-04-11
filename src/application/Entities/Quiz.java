package application.Entities;

public class Quiz {
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 *  SELECT *
	 *	FROM quiz_questions 
	 *	RIGHT JOIN quiz 
	 *	ON (quiz_questions.quiz_id = quiz.quiz_id)
	 *	INNER JOIN quiz_answers
		ON (quiz_answers.question_id = quiz_questions.question_id)
		WHERE quiz.quiz_id = 1
	 */

}
