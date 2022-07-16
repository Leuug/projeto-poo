import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class StartMenu extends JFrame {
	private JPanel mainPanel;
	private JPanel buttonsPanel;
	private JButton startButton;
	private JButton quitButton;

	private int xButtonSize = 300;
	private int yButtonSize = 200;
	
	public StartMenu() {
		mainPanel = (JPanel)this.getContentPane();
		mainPanel.setLayout(new GridBagLayout());

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
		//buttonsPanel.add(Box.createVerticalGlue());

		//buttonsPanel.add(Box.createHorizontalGlue());
		startButton = new JButton("Start!");
		startButton.setMaximumSize(new Dimension(xButtonSize, yButtonSize));
		startButton.setMinimumSize(new Dimension(xButtonSize, yButtonSize));
		buttonsPanel.add(startButton);

		quitButton = new JButton("Quit!");
		quitButton.setMaximumSize(new Dimension(xButtonSize, yButtonSize));
		quitButton.setMinimumSize(new Dimension(xButtonSize, yButtonSize));
		buttonsPanel.add(quitButton);

		//buttonsPanel.add(Box.createVerticalGlue());
		mainPanel.add(buttonsPanel, new GridBagConstraints());
		
	    setSize(500, 400);
	}

	public static void main(String[] args) {
		StartMenu frame = new StartMenu();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
