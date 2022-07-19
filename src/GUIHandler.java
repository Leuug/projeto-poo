import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import cinewise.*;
import java.util.*;

public class GUIHandler extends JFrame implements ActionListener {
	AnswerGUI answerFrame;
	JPanel mainPanel;
	JLabel score;
	JLabel answerStatus;
	JButton nextPosterButton;
	CineSession session;

	private boolean questionAnswered = false;
	private String NEXTPOSTER = "next_poster";

	private String answer = "";

	public GUIHandler(int difficulty) throws Exception {
		super("Menu");

		session = new CineSession(difficulty);

		this.setLayout(new BorderLayout());
		mainPanel = (JPanel) this.getContentPane();

		String scoreLabel = String.format("<html><font size=+2><b>SCORE: %d</b></font></html>", session.getScore());
		score = new JLabel(scoreLabel, SwingConstants.CENTER);
		mainPanel.add(score, BorderLayout.PAGE_START);

		nextPosterButton = new JButton("<html><b>NEXT POSTER</b></html>");
		mainPanel.add(nextPosterButton, BorderLayout.CENTER);
		nextPosterButton.addActionListener(this);
		nextPosterButton.setActionCommand(NEXTPOSTER);

		answerStatus = new JLabel("<html><font size =+1>Your answer is ...</font></html>",
		SwingConstants.CENTER);
		mainPanel.add(answerStatus, BorderLayout.PAGE_END);

		answerFrame = new AnswerGUI(this);
		answerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String [] answers = this.session.genMutipleChoises(4);
		String correctAnswer = this.session.getCorrectAnswer();

		System.out.println("A resposta esperada: " + correctAnswer + "\n");
		System.out.println("As opções: " + Arrays.toString(answers) + "\n");
		System.out.println("Ibagens: " + this.session.getMoviePath() + "\n");
		System.out.println("Nome: " + this.session.getMovieName() + "\n");

		answerFrame.SetMovies(answers[0], answers[1], answers[2], answers[3]);
		answerFrame.SetImage(session.getMoviePath());

		answerFrame.setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (NEXTPOSTER.equals(e.getActionCommand())) {
			if (questionAnswered) {
				try {
					if (!session.isItTheEnd()) {
						session.nextQuestion();
						answerStatus.setText("<html><font size =+1>Your answer is ...</font></html>");
						answer = "";
						questionAnswered = false;

						String[] movies = session.genMutipleChoises(4);
						answerFrame.SetMovies(movies[0], movies[1], movies[2], movies[3]);

						answerFrame.SetImage(session.getMoviePath());
					} else {
						// End program
					}
				} catch (Exception exception) {
				}
			}
		}
	}

	public void SetAnswer(String _answer) {
		System.out.println(answer);
		answer = _answer;
		questionAnswered = true;
		try {
			if (session.evaluate(answer)) {
				answerStatus.setText("<html><font size =+1>Your answer is <font color=green>CORRECT</font></html>");

				session.addToScore(100);
				String scoreText = String.format("<html><font size=+2><b>SCORE: %d</b></font></html>", session.getScore());
				score.setText(scoreText);
			} else {
				answerStatus.setText("<html><font size =+1>Your answer is <font color=red>INCORRECT</font></html>");

				session.addToScore(-50);
				if (session.getScore() < 0)
					session.addToScore(-1 * session.getScore());

				String scoreText = String.format("<html><font size=+2><b>SCORE: %d</b></font></html>", session.getScore());
				score.setText(scoreText);
			}
		} catch (Exception exception) {
			System.out.println(exception);

			try
			{
				System.out.println(
					Arrays.toString(this.session.genMutipleChoises(4))
					);
			}
			catch (Exception err)
			{
				System.out.println("Deu mais merda.");
				System.out.println(err);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		GUIHandler frame = new GUIHandler(0);
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
