import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class StartMenu extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton startButton;
    private JButton quitButton;
    private JLabel nomeLabel;

    private String START = "start";
    private String QUIT = "quit";

    public StartMenu() throws Exception {
        mainPanel = (JPanel)this.getContentPane();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 2, 40, 2);

        nomeLabel = new JLabel(
            "<html><font size=+2><font color=purple><b>Movie Quizz</b></font></html>");
        constraints.gridy = 0;
        mainPanel.add(nomeLabel, constraints);

        constraints.insets = new Insets(2, 2, 2, 2);

        startButton = new JButton("Start!");
        startButton.addActionListener(this);
        startButton.setActionCommand(START);
        constraints.gridy = 1;
        mainPanel.add(startButton, constraints);

        quitButton = new JButton("Quit!");
        quitButton.addActionListener(this);
        quitButton.setActionCommand(QUIT);
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(quitButton, constraints);

        setSize(500, 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (START.equals(e.getActionCommand())) {
            this.setVisible(false);

            // CineSession session = new CineSession(0);
            try {
                GUIHandler frame = new GUIHandler(100);
                frame.setSize(300, 150);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (Exception err) {
                System.out.print("Não foi possivel iniciar GUIHandler");
                System.out.println(err);
                err.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws Exception {
        StartMenu frame = new StartMenu();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
