import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import cinewise.*;

public class GUIHandler extends JFrame implements ActionListener {
    AnswerGUI answerFrame;
    JPanel mainPanel;
    JLabel score;
    JLabel answerStatus;
    JButton nextPosterButton;
	CineSession session;

    private boolean questionAnswered = true;
    private String NEXTPOSTER = "next_poster";

    private String answer = "";

	
    public GUIHandler(int difficulty) throws Exception
	{
        super("Menu");

		session = new CineSession(difficulty);

        this.setLayout(new BorderLayout());
        mainPanel = (JPanel)this.getContentPane();

		String scoreLabel = String.format("<html><font size=+2><b>SCORE: %d</b></font></html>", session.getScore());
        score = new JLabel(scoreLabel, SwingConstants.CENTER);
        mainPanel.add(score, BorderLayout.PAGE_START);

        nextPosterButton = new JButton("<html><b>NEXT POSTER</b></html>");
        mainPanel.add(nextPosterButton, BorderLayout.CENTER);
        nextPosterButton.addActionListener(this);
        nextPosterButton.setActionCommand(NEXTPOSTER);

        answerStatus =
            new JLabel("<html><font size =+1>Your answer is ...</font></html>",
                       SwingConstants.CENTER);
        mainPanel.add(answerStatus, BorderLayout.PAGE_END);

        answerFrame = new AnswerGUI(this, session.getMoviePath());
        answerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        answerFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
	{
        if (NEXTPOSTER.equals(e.getActionCommand()))
		{
            if (questionAnswered)
			{
				session.nextQuestion();
				answerStatus.setText("<html><font size =+1>Your answer is ...</font></html>");
				answer = "";
				questionAnswered = false;
				
				answerFrame.SetMovies(, , , );
				
				/*
				Change poster image
				answerFrame.SetImage(session.getMoviePath())
				*/
            }
        }
    }

    public void SetAnswer(String _answer)
	{
		answer = _answer;
		questionAnswered = true;
		try
		{
			if (session.evaluate(answer))
			{
        		answerStatus.setText("<html><font size =+1>Your answer is <font color=green>CORRECT</font></html>");
                		
				session.addToScore(100);
					
				score.setText(String.format("<html><font size=+2><b>SCORE: $d</b></font></html>", session.getScore()));
			}
			else
			{
				answerStatus.setText("<html><font size =+1>Your answer is <font color=red>INCORRECT</font></html>");
              		
				session.addToScore(-50);
				if (session.getScore() < 0)
					session.addToScore(-1 * session.getScore());
				
				score.setText(String.format("<html><font size=+2><b>SCORE: $d</b></font></html>", session.getScore()));
			}
		}
		catch (Exception exception)
		{}
	}

    public static void main(String[] args) throws Exception {
        GUIHandler frame = new GUIHandler(0);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
