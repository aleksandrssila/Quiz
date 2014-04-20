package application.Controllers;

import java.util.ArrayList;
import java.util.List;
import core.UserInputManager;
import application.Models.QuizGameModel;
import application.Models.QuizModel;
import application.Models.UserModel;
import application.Entities.Answer;
import application.Entities.Question;
import application.Entities.Quiz;
import application.Entities.QuizGame;
import application.Entities.Result;
import application.Entities.User;

public class QuizGameController {
	
	public  Quiz quiz;
	public  List<QuizGame> quizGame;
	private Result result;
	private List<Quiz> quizList;
	
	public QuizGameController(){
		this.quiz   = new Quiz();
		this.result = new Result();
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	public boolean loadQuiz(User user){
		
		this.quizList = new ArrayList<Quiz>();
		UserInputManager userMamager = new UserInputManager("INTIGER_PATTERT");

		QuizModel quizM = new QuizModel();
		quizList = quizM.getActiveQuizList();
		
		boolean correct = false;

		try{
			// check if there are any active quizez
			quizList.get(0).getId();
			
			System.out.println("*****************************");
			System.out.println("Please chose you quizz      :");
			System.out.println("*****************************");
			// quiz number
			int qnum = 1;
			// print all quizzes 	
			for(Quiz q:quizList){
				userMamager.setOption(Integer.toString(qnum));
				System.out.println("Nr: "+qnum+" | Name: "+q.getName());
				System.out.println("------------------------------------------");
				qnum++;
			}
						
			while(!correct){
							
				int num = Integer.parseInt(userMamager.askAction());
				
				num--;			
				
					try{
						// selected quiz
						this.quiz = this.quizList.get(num);	
						
						// check if user have played this quiz
						UserModel userM = new UserModel();
						this.result = userM.getUserResultByQuizId(this.quiz.getId(), user.getId());
						// if result exist in database
						if(this.result.getId() > 0){
							// show message
							System.out.println("");
							System.out.println("You have played this quiz");
							System.out.println("Score : "+this.result.getScore());
							System.out.println("Your answers  : ");
							System.out.println(this.result.getData());
						}
						else{
							// load quiz game 
							QuizGameModel getQuizGame = new QuizGameModel();
							this.quizGame = getQuizGame.getQuizGame(this.quiz);
							// break loop
							correct = true;
						}
					}
					catch(IndexOutOfBoundsException e){
						System.out.println("This number is wrong");
					}	
			}	
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("There are no active quiz games");
		}
		
		return correct;
	}
	/**
	 * 
	 * @param userid
	 */
	public void playGame(int userid){
		
		this.result.setQuizId(this.quiz.getId());
		this.result.setUserId(userid);
		this.result.setData(null);
		
		String newline = System.getProperty("line.separator");
		
		// question number
		int $i = 1;
		int finalScore = 0;
		// show quiz question and answers
		for(QuizGame qgame:this.quizGame){
			/** VAR question Question **/
			Question question = new Question();
			question = qgame.getQuestion();
			// print question
			System.out.println("");
			System.out.println("::::::::::::::::::::::::::");
			System.out.println("::::: QUESTION "+$i+ ":::::::::");
			System.out.println("::::::::::::::::::::::::::");
			System.out.println("");
			System.out.println(question.getQuestionText());
			System.out.println("");
			/** VAR inpMan UserInputManager**/
			UserInputManager inpMan = new UserInputManager("INTIGER_PATTERT");
			inpMan.setMessage("");
			// next question number
			$i++;	
			// first answer number
			int a = 1;
			// print all available answers
			for(Answer answer:qgame.getAnswers()){
				inpMan.setOption(Integer.toString(a));
				System.out.print("Nr: "+a);
				System.out.println(" | "+answer.getText());		
				a++;
			}
			
			boolean nextQuestion 	= false;	
			
			while(!nextQuestion){
					
				int num = Integer.parseInt(inpMan.askAction())-1;
								
				try{
					Answer chosenAnsw = qgame.getAnswers().get(num);
					if(chosenAnsw.getId() == question.getAnswerId()){
						finalScore++;
						if(this.result.getData() == null){
							this.result.setData("option"+num+":"+chosenAnsw.getText()+" :true "+newline);
						}
						else{
							this.result.setData(result.getData()+"option"+num+":"+chosenAnsw.getText()+" :true "+newline);
						}
						nextQuestion = true;
					}else{
						if(this.result.getData()== null){
							this.result.setData("option"+num+":"+chosenAnsw.getText()+" :false "+newline);
						}
						else{
							this.result.setData(result.getData()+"option"+num+":"+chosenAnsw.getText()+" :false "+newline);
						}
						nextQuestion = true;
					}
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("This number is wrong");
				}
								
			}
			
		}
		this.result.setScore(finalScore);
		
		// inser result to db
		UserModel userM = new UserModel();
		this.result.setId(userM.insertUserResult(this.result).getId());
		
		if(this.result.getId()>0){
			System.out.println("Your score is: "+this.result.getScore());
		}else{
			System.out.println("There was an error saving your score!");
		}
	}

}
