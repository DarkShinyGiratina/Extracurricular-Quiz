/**
 * Describes a question in the overall quiz.
 * @author Ross Williams
 */

import java.util.HashMap;

public class Question {
	private String question;
	private HashMap<String, int[]> answerChoices; // Maps the string (answer choice) to array of weights for each subject
	private String selectedAnswer;

	public Question(String q, HashMap<String, int[]> answerChoices) {
		question = q;
		this.answerChoices = answerChoices;
		selectedAnswer = null;
	}

	/**
	 * Adjusts the weighting of an overall map based on the chosen answer.
	 *
	 * @param overallWeights The map containing an overall sum of weights.
	 */
	public void adjustWeights(int[] overallWeights) {
		if (selectedAnswer == null) {
			System.out.println("There's no selected answer!");
			return; // If there's no selected answer in the question, don't keep going.
		}
		int[] chosen = answerChoices.get(selectedAnswer);

		if (chosen == null) {
			System.out.println("Invalid answer choice!");
			return;
		}

		for (Subjects curSubj : Subjects.values()) { // Iterate over each subject in the weights of the selected answer
			int currentSubjectWeight = overallWeights[curSubj.INDEX]; // Get the current weight in the overall map
			currentSubjectWeight += chosen[curSubj.INDEX]; // Add the weight from this choice into the overall weight
			overallWeights[curSubj.INDEX] = currentSubjectWeight; // Replace the weight in the overall question
		}
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
}

