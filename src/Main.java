import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) {

		// Generate the list of questions, then shuffle it.
		new QuestionList(); // This generates the QuestionList, so I can then use the static getList method.
		ArrayList<Question> questions = QuestionList.getList();
		Collections.shuffle(questions);

		FlatDarculaLaf.setup(); // Get the dark FlatLAF setup
		JFrame frame = new JFrame("Extracurricular Quiz"); // Set up our frame
		JTabbedPane tabbedPane = new JTabbedPane();

		// Setting up the first tab, a menu.
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());

		// Intro label, with a bigger font.
		JLabel quizIntro = new JLabel("Welcome to Comet's Extracurricular Quiz!");
		quizIntro.setAlignmentX(Component.CENTER_ALIGNMENT);
		quizIntro.setFont(quizIntro.getFont().deriveFont(36f));
		menuPanel.add(quizIntro, BorderLayout.PAGE_START);

		// A description of the quiz
		JTextArea description = new JTextArea("In this quiz, you will be asked a series of questions to determine what extracurriculars you might want to try in school! Please answer as honestly as you can, so the results can be as accurate as possible!");
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);
		description.setFont(description.getFont().deriveFont(24f));
		menuPanel.add(description, BorderLayout.CENTER);

		// Add the buttons
		menuPanel.add(generateButtonPanel(tabbedPane, null), BorderLayout.PAGE_END);
		// Add the panel to the tabbed pane
		tabbedPane.add(menuPanel);

		// Generate question tabs for each question
		for (Question q : questions) {
			JPanel qPanel = generateQuestionPanel(q, tabbedPane);
			tabbedPane.add(qPanel);
		}

		// Generate the results tab
		JPanel resultsPanel = new JPanel(new BorderLayout());

		JLabel resultsText = new JLabel("Results");
		resultsText.setHorizontalAlignment(JLabel.CENTER);
		resultsText.setFont(resultsText.getFont().deriveFont(36f));
		resultsPanel.add(resultsText, BorderLayout.PAGE_START);

		// A description of the results, and the button to generate.
		JPanel instructionsAndButtonPanel = new JPanel();
		instructionsAndButtonPanel.setLayout(new BoxLayout(instructionsAndButtonPanel, BoxLayout.PAGE_AXIS));
		JTextArea resultsInstructions = new JTextArea("Click the button below to generate your results! You're free to revisit any question you like before doing this!");
		resultsInstructions.setLineWrap(true);
		resultsInstructions.setWrapStyleWord(true);
		resultsInstructions.setEditable(false);
		resultsInstructions.setFont(resultsInstructions.getFont().deriveFont(24f));
		instructionsAndButtonPanel.add(resultsInstructions);

		// Add a button which will generate the results.
		JButton resultsButton = new JButton("Generate Results");
		resultsButton.addActionListener(e -> {
			JPanel finalResults = generateResults(questions);
			frame.setVisible(false);
			JFrame newFrame = new JFrame("Extracurricular Quiz Results");
			newFrame.add(finalResults);
			newFrame.pack();
			newFrame.setPreferredSize(new Dimension(newFrame.getWidth(), 500));
			newFrame.pack();
			newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			newFrame.setLocationRelativeTo(null);
			newFrame.setVisible(true);

		});
		resultsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		instructionsAndButtonPanel.add(resultsButton);

		resultsPanel.add(instructionsAndButtonPanel, BorderLayout.CENTER);
		resultsPanel.add(generateButtonPanel(tabbedPane, null), BorderLayout.PAGE_END);

		tabbedPane.add(resultsPanel);

		// Prevent the user from clicking tabs to change them.
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			tabbedPane.setEnabledAt(i, false);
		}

		frame.add(tabbedPane);
		frame.pack();
		frame.pack(); // Apparently JTextAreas don't like you if you don't pack twice
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Generates the results for the quiz.
	 * @param questions The list of questions used.
	 * @return A new JPanel with the results.
	 */
	private static JPanel generateResults(ArrayList<Question> questions) {
		JPanel resultsPanel = new JPanel(new BorderLayout());
		JLabel resultsText = new JLabel("Results");
		resultsText.setFont(resultsText.getFont().deriveFont(36f));
		resultsText.setHorizontalAlignment(JLabel.CENTER);
		resultsPanel.add(resultsText, BorderLayout.PAGE_START);

		// Get our final weights.
		int[] totalWeights = new int[Subjects.NUMBER_OF_SUBJECTS];
		for (Question q : questions) {
			q.adjustWeights(totalWeights);
		}

		// Generate the top two results, which will have text associated.
		int maxWeightIndex = 0;
		for (int i = 0; i < totalWeights.length; i++) {
			maxWeightIndex = totalWeights[i] > totalWeights[maxWeightIndex] ? i : maxWeightIndex;
		}

		int secondMaxWeightIndex = 0;
		for (int i = 0; i < totalWeights.length; i++) {
			if (i == maxWeightIndex) continue; // Skip the max, so we can find the second highest.
			secondMaxWeightIndex = totalWeights[i] > totalWeights[secondMaxWeightIndex] ? i : secondMaxWeightIndex;
		}

		// Make a panel containing the results text.
		JPanel resultsTextPanel = new JPanel();
		resultsTextPanel.setLayout(new BoxLayout(resultsTextPanel, BoxLayout.PAGE_AXIS));

		JTextArea topResult = new JTextArea("");
		topResult.setEditable(false);
		topResult.setLineWrap(true);
		topResult.setWrapStyleWord(true);
		topResult.setFont(topResult.getFont().deriveFont(18f));
		resultsTextPanel.add(topResult);

		resultsTextPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		JTextArea secondResult = new JTextArea("");
		secondResult.setEditable(false);
		secondResult.setLineWrap(true);
		secondResult.setWrapStyleWord(true);
		secondResult.setFont(topResult.getFont().deriveFont(18f));
		resultsTextPanel.add(secondResult);

		resultsTextPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		JTextArea makeYourOwn = new JTextArea("If your school doesn't have any of these suggested activities, feel free to try making them (or anything else) yourself! Colleges love students with the initiative to make their own clubs and activities.");
		makeYourOwn.setEditable(false);
		makeYourOwn.setLineWrap(true);
		makeYourOwn.setWrapStyleWord(true);
		makeYourOwn.setFont(makeYourOwn.getFont().deriveFont(18f));
		resultsTextPanel.add(makeYourOwn);


		resultsPanel.add(resultsTextPanel, BorderLayout.CENTER);

		// Change the text for the top result.
		if (maxWeightIndex == Subjects.ART.INDEX) {
			topResult.setText("Your top result was art! Consider joining clubs related to your favorite artistic passions, be it origami, painting, or anything else! Stage crew is a great choice, if your school has a theater department! If your school doesn't have a club for your favorite art, try making one! Colleges like students with the initiative to form their own clubs.");
		} else if (maxWeightIndex == Subjects.WRITING.INDEX) {
			topResult.setText("Your top result was writing! Try joining the school newspaper, or a creative writing club!");
		} else if (maxWeightIndex == Subjects.THEATER.INDEX) {
			topResult.setText("Your top result was theater! If your school has a theater department, audition! You might land great parts, and theater is a great thing to have on your application! If your school doesn't have a theater department, see if you can create student performance clubs like an a-capella group or an improv troupe!");
		} else if (maxWeightIndex == Subjects.BUILDING.INDEX) {
			topResult.setText("Your top result was building! Stage crew, science olympiad (building), and robotics are all great options! If your school has anything else you might like, try it!");
		} else if (maxWeightIndex == Subjects.MATH.INDEX) {
			topResult.setText("Your top result was math! The math team is a great place, but there's also external math competitions to try like the AMC! You might even qualify for AIME and beyond, which colleges love to see.");
		} else if (maxWeightIndex == Subjects.SCIENCE.INDEX) {
			topResult.setText("Your top result was science! Science olympiad, science bowl, and science research (this may be a class at your school) might be things to try! If you're specifically interested in certain branches of science, check for national-level competitions to join like HOSA! Colleges like seeing high-ranking results.");
		} else if (maxWeightIndex == Subjects.COMPUTERS.INDEX) {
			topResult.setText("Your top result was computers! The programming club and robotics programming might be the spot for you! There are also external programming competitions like Google Kickstart, or USACO! Colleges like seeing high-ranking results in competitions!");
		} else if (maxWeightIndex == Subjects.SOC.INDEX) {
			topResult.setText("Your top result was social studies/business! Try clubs like Model Congress, DECA, or debate! If you can reach a national or even state level of competition, that will reflect very well on your college application!");
		}

		// Change the text for the second result.
		if (secondMaxWeightIndex == Subjects.ART.INDEX) {
			secondResult.setText("Your second result was art! Consider joining clubs related to your favorite artistic passions, be it origami, painting, or anything else! Stage crew is a great choice, if your school has a theater department!");
		} else if (secondMaxWeightIndex == Subjects.WRITING.INDEX) {
			secondResult.setText("Your second result was writing! Try joining the school newspaper, or a creative writing club!");
		} else if (secondMaxWeightIndex == Subjects.THEATER.INDEX) {
			secondResult.setText("Your second result was theater! If your school has a theater department, audition! You might land great parts, and theater is a great thing to have on your application! If your school doesn't have a theater department, see if you can join or create student performance clubs like an a-capella group or an improv troupe!");
		} else if (secondMaxWeightIndex == Subjects.BUILDING.INDEX) {
			secondResult.setText("Your second result was building! Stage crew, science olympiad (building), and robotics are all great options! If your school has anything else you might like, try it!");
		} else if (secondMaxWeightIndex == Subjects.MATH.INDEX) {
			secondResult.setText("Your second result was math! The math team is a great place, but there's also external math competitions to try like the AMC! You might even qualify for AIME and beyond, which colleges love to see.");
		} else if (secondMaxWeightIndex == Subjects.SCIENCE.INDEX) {
			secondResult.setText("Your second result was science! Science olympiad, science bowl, and science research (this may be a class at your school) might be things to try! If you're specifically interested in certain branches of science, check for national-level competitions to join like HOSA! Colleges like seeing high-ranking results.");
		} else if (secondMaxWeightIndex == Subjects.COMPUTERS.INDEX) {
			secondResult.setText("Your second result was computers! The programming club and robotics programming might be the spot for you! There are also external programming competitions like Google Kickstart, or USACO! Colleges like seeing high-ranking results in competitions!");
		} else if (secondMaxWeightIndex == Subjects.SOC.INDEX) {
			secondResult.setText("Your second result was social studies/business! Try clubs like Model Congress, DECA, or debate! If you can reach a national or even state level of competition, that will reflect very well on your college application!");
		}


		// Make a panel containing text thanking the user, and a button which ends the program.
		JPanel endStuff = new JPanel();
		endStuff.setLayout(new BoxLayout(endStuff, BoxLayout.PAGE_AXIS));

		JLabel thanks = new JLabel("Thank you for taking the extracurriculars quiz! Hopefully you found something to pursue :)");
		thanks.setFont(thanks.getFont().deriveFont(14f));
		thanks.setAlignmentX(Component.CENTER_ALIGNMENT);

		endStuff.add(thanks);

		// A Button that closes the window, thus ending the program.
		JButton close = new JButton("Close Quiz");
		close.addActionListener(e -> {
			JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(resultsPanel);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		});

		endStuff.add(close);

		resultsPanel.add(endStuff, BorderLayout.PAGE_END);
		return resultsPanel;
	}

	/**
	 * Generates a panel for a specific question.
	 * @param q The question object you want to generate for.
	 * @param pane The JTabbedPane that the question panel resides in. Used to make the next and previous buttons.
	 * @return The panel with the question, properly formatted.
	 */
	private static JPanel generateQuestionPanel(Question q, JTabbedPane pane) {
		JPanel qPanel = new JPanel(new BorderLayout()); // Make the Question Panel

		JTextArea questionText = new JTextArea(q.getQuestion()); // Add the text of the question, and make sure it's not editable
		questionText.setEditable(false);
		questionText.setLineWrap(true);
		questionText.setWrapStyleWord(true);
		questionText.setFont(questionText.getFont().deriveFont(30f));
		qPanel.add(questionText, BorderLayout.PAGE_START);

		// Make a panel for the answers, with text saying which answer is selected (and showing buttons to click)
		JPanel answersPanel = new JPanel();
		answersPanel.setLayout(new BoxLayout(answersPanel, BoxLayout.PAGE_AXIS));

		JLabel selectedChoice = new JLabel("You haven't selected an answer!");
		selectedChoice.setAlignmentX(Component.CENTER_ALIGNMENT);
		selectedChoice.setFont(selectedChoice.getFont().deriveFont(20f));
		answersPanel.add(selectedChoice);

		// 2 columns with however many rows are necessary
		JPanel answerButtonsPanel = new JPanel(new GridLayout(0, 2));

		// Loop through the answer choices, and add a button for each one.
		for (String answer: q.getAnswerChoices().keySet()) {
			JButton answerButton = new JButton(answer);
			answerButton.addActionListener(e -> {
				q.setSelectedAnswer(answer);
				selectedChoice.setText("You have selected: " + answer);
				selectedChoice.setAlignmentX(Component.CENTER_ALIGNMENT);
			});
			answerButtonsPanel.add(answerButton);
		}

		answersPanel.add(answerButtonsPanel);
		qPanel.add(answersPanel, BorderLayout.CENTER);
		qPanel.add(generateButtonPanel(pane, q),BorderLayout.PAGE_END);
		return qPanel;
	}

	/**
	 * Generate a "next" and "previous" button within a tabbed pane for a specific question's panel.
	 * @param tabbedPane The pane you're adding to (needed to check tab indices)
	 * @param q The question the buttons are sharing a panel with (needed to check if there's been an answer yet)
	 * @return
	 */
	private static JPanel generateButtonPanel(JTabbedPane tabbedPane, Question q) {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		JButton next = new JButton("Next Page");
		JButton prev = new JButton("Previous Page");
		// Add page button listeners to handle swapping tabs.
		next.addActionListener(e -> {
			if (q != null && q.getSelectedAnswer() == null) { // If they haven't selected an answer yet, don't do anything.
				return;
			}
			if (tabbedPane.getSelectedIndex() != tabbedPane.getTabCount() - 1) { // If advancing to the next page won't make us go out of bounds
				tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() + 1);
			}
		});

		prev.addActionListener(e -> {
			if (tabbedPane.getSelectedIndex() != 0) { // If going backwards won't make us go out of bounds
				tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() - 1);
			}
		});
		buttonPanel.add(prev);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(next);
		return buttonPanel;
	}
}