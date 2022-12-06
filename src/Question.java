/**
 * Describes a question in the overall quiz.
 *
 * @author Ross Williams
 */

import java.util.HashMap;

public class Question {
	private final String question;
	private final HashMap<String, int[]> answerChoices; // Maps the string (answer choice) to array of weights for each subject
	private String selectedAnswer;

	public Question(String q, HashMap<String, int[]> answerChoices) {
		question = q;
		this.answerChoices = answerChoices;
		selectedAnswer = null;
	}

	/**
	 * Adjusts the weighting of an overall map based on the chosen answer.
	 *
	 * @param overallWeights The array containing an overall sum of weights.
	 */
	public void adjustWeights(int[] overallWeights) {
		if (selectedAnswer == null) {
			System.out.println("There's no selected answer!");
			return; // If there's no selected answer in the question, don't keep going.
		}

		if (!(answerChoices.containsKey(selectedAnswer))) {
			System.out.println("Invalid answer choice!");
			return;
		}

		int[] chosen = answerChoices.get(selectedAnswer);
		for (Subjects curSubj : Subjects.values()) { // Iterate over each subject in the weights of the selected answer
			overallWeights[curSubj.INDEX] += chosen[curSubj.INDEX]; // Add each weight from the selected choice to the total weights.
		}
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public HashMap<String, int[]> getAnswerChoices() {
		return answerChoices;
	}

	public String toString() {
		return question;
	}
}

