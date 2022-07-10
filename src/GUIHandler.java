import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUIHandler extends JFrame implements ActionListener
{
	AnswerGUI answerFrame;
	JPanel mainPanel;
	JLabel score;
	JLabel answerStatus;
	JButton nextPosterButton;


	private boolean questionAnswered = true;
	private String NEXTPOSTER = "next_poster";

	private String answer = "";


	public GUIHandler() throws IOException
	{
		super("Menu");

		this.setLayout(new BorderLayout());
		mainPanel = (JPanel) this.getContentPane();

		
		score = new JLabel("<html><font size=+2><b>SCORE: ---</b></font></html>", SwingConstants.CENTER);
		mainPanel.add(score, BorderLayout.PAGE_START);

		nextPosterButton = new JButton("<html><b>NEXT POSTER</b></html>");
		mainPanel.add(nextPosterButton, BorderLayout.CENTER);
		nextPosterButton.addActionListener(this);
		nextPosterButton.setActionCommand(NEXTPOSTER);

		answerStatus = new JLabel("<html><font size =+1>Your answer is ...</font></html>", SwingConstants.CENTER);
		mainPanel.add(answerStatus, BorderLayout.PAGE_END);


		answerFrame = new AnswerGUI(this);
		answerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		answerFrame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (NEXTPOSTER.equals(e.getActionCommand()))
		{
			if (questionAnswered)
			{
				answerStatus.setText("<html><font size =+1>Your answer is <font color=green>CORRECT</font></html>");
				score.setText("<html><font size=+2><b>SCORE: 3000</b></font></html>");
				// Get new poster
			}
		}
	}


	public void SetAnswer(String _answer)
	{
		answer = _answer;
	}


	public static void main (String[] args) throws Exception
	{
		GUIHandler frame = new GUIHandler();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
