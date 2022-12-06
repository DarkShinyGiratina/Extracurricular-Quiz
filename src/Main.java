import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) {

		// Generate the list of questions, then shuffle it.
		final QuestionList LIST = new QuestionList();
		ArrayList<Question> questions = LIST.getList();
		Collections.shuffle(questions);

		FlatDarculaLaf.setup(); // Get the dark FlatLAF setup
		JTabbedPane tabbedPane = new JTabbedPane();

		// Setting up the first tab, a menu.
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(3,1));

		// Intro label, with a bigger font.
		JLabel quizIntro = new JLabel("Welcome to Comet's Extracurricular Quiz!");
		Font labelFont = quizIntro.getFont();
		quizIntro.setFont(new Font(labelFont.getName(), Font.PLAIN, 36));
		menuPanel.add(quizIntro);


		JTextArea description = new JTextArea("In this quiz, you will be asked a series of questions to determine what extracurriculars you might want to try in school! Please answer as honestly as you can, so the results can be as accurate as possible!");
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		menuPanel.add(description);

		menuPanel.add(Box.createVerticalGlue());
		tabbedPane.add(menuPanel);

		JFrame frame = new JFrame("Extracurricular Quiz");
		frame.setSize(800, 600);
		frame.add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}