import java.util.ArrayList;
import java.util.Arrays;
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

		// WEIGHTS IN ORDER: art,writing,theater,building,math,science,computers,soc

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
