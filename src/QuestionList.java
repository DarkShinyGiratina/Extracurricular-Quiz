/**
 * Represents a list of questions for the EC quiz.
 *
 * @author Ross Williams
 */

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionList {
	private static ArrayList<Question> questionList = new ArrayList<Question>();


	public QuestionList() {
		String q1Question = "What is your favorite subject in school, of these 4?";
		String[] q1Answers = {"Math", "Science", "English", "Social Studies"};
		int[][] q1Weights = {
						{0, 0, 0, 0, 25, 0, 15, 0},
						{0, 0, 0, 0, 0, 30, 10, 0},
						{10, 20, 10, 0, 0, 0, 0, 0},
						{0, 15, 0, 0, 0, 0, 0, 25}
		};
		questionList.add(generateQuestion(q1Question, q1Answers, q1Weights));

		String q2Question = "Which group of words do you think describes you the most?";
		String[] q2Answers = {"Smart and Introverted", "Creative and Eager", "Extroverted and Loud (in a good way!)", "Productive and Diligent"};
		int[][] q2Weights = {
						{0, 0, 0, 0, 15, 10, 15, 0},
						{10, 10, 10, 10, 0, 0, 0, 0},
						{0, 0, 20, 0, 0, 0, 0, 20},
						{0, 0, 0, 20, 0, 20, 0, 0}
		};
		questionList.add(generateQuestion(q2Question, q2Answers, q2Weights));

		String q3Question = "What do you like to do in your free time?";
		String[] q3Answers = {"Research interesting topics", "Watch TV/Youtube/Netflix", "Create things (drawing, writing stories, etc.)", "Read"};
		int[][] q3Weights = {
						{0, 0, 0, 0, 0, 20, 0, 20},
						{10, 15, 15, 0, 0, 0, 0, 0},
						{15, 15, 0, 10, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0, 20, 20}
		};
		questionList.add(generateQuestion(q3Question, q3Answers, q3Weights));



		String q4Question = "True or False: You prefer high-stakes competitions over low-stakes/relaxing activities.";
		String[] q4Answers = {"True", "False"};
		int[][] q4Weights = {
						{0, 0, 0, 10, 10, 10, 0, 10},
						{10, 10, 10, 0, 0, 0, 10, 0}
		};
		questionList.add(generateQuestion(q4Question, q4Answers, q4Weights));


		String q5Question = "Do you prefer working alone/with a small group or in a team?";
		String[] q5Answers = {"Alone/Small groups for me!", "With a team!"};
		int[][] q5Weights = {
						{10, 10, 0, 0, 10, 0, 10, 0},
						{0, 0, 10, 10, 0, 10, 0, 10}
		};
		questionList.add(generateQuestion(q5Question, q5Answers, q5Weights));

		String q6Question = "How do you learn best?";
		String[] q6Answers = {"Watching an example", "Doing something practical", "Reading instructions", "Thinking of my own way to understand a topic"};
		int[][] q6Weights = {
						{10, 10, 20, 0, 0, 0, 0, 0},
						{10, 10, 0, 20, 0, 0, 0, 0},
						{0, 0, 0, 0, 20, 20, 0, 0},
						{0, 0, 0, 0, 0, 0, 20, 20}
		};

		questionList.add(generateQuestion(q6Question, q6Answers, q6Weights));

		String q7Question = "How much time are you willing to commit per week to an activity?";
		String[] q7Answers = {"0-3 hours", "4-7 hours", "8-10 hours", "10+ hours"};
		int[][] q7Weights = {
						{20, 20, 0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 10, 0, 20, 10},
						{0, 0, 20, 20, 0, 0, 0, 0},
						{0, 0, 0, 20, 0, 10, 10, 0}
		};

		questionList.add(generateQuestion(q7Question,q7Answers,q7Weights));
	}

	/**
	 * Generates a question, given an array of answers and weights.
	 * @param q The question itself.
	 * @param answers An array of answer choices.
	 * @param weights A 2-D Array where the outer array corresponds to choices, and the inner arrays correspond to weights for each category.
	 * @return
	 */
	private Question generateQuestion(String q, String[] answers, int[][] weights) {
		HashMap<String, int[]> choices = new HashMap<>(Subjects.NUMBER_OF_SUBJECTS);
		for (int i = 0; i < answers.length; i++) {
			choices.put(answers[i], weights[i]);
		}
		return new Question(q, choices);
	}

	public static ArrayList<Question> getList() {
		return questionList;
	}
}
