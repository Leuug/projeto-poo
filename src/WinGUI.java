import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import cinewise.*;

public class WinGUI extends JFrame implements ActionListener{
    
    private JPanel win;
    private JButton restartButton;
    private JButton quitButton;
    private JLabel nomeLabel;

    private String RESTART = "restart";
    private String QUIT = "quit";

    public WinGUI(CineSession session, GUIHandler score, AnswerGUI answer, ImageGUI image) throws Exception {
        score.setVisible(false);    
        answer.setVisible(false);
        image.setVisible(false);

        win = (JPanel)this.getContentPane();
        win.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(2, 2, 40, 2);

        String scoreLabel =
            String.format("<html><font size=+2><b>O jogo acabou<br/>Seu score: %d</b></font></html>",
                          session.getScore());
        nomeLabel = new JLabel(scoreLabel);
        constraints.gridy = 0;
        win.add(nomeLabel, constraints);

        constraints.insets = new Insets(2, 2, 2, 2);

        restartButton = new JButton("Restart!");
        restartButton.addActionListener(this);
        restartButton.setActionCommand(RESTART);
        constraints.gridy = 1;
        win.add(restartButton, constraints);

        quitButton = new JButton("Quit!");
        quitButton.addActionListener(this);
        quitButton.setActionCommand(QUIT);
        constraints.gridx = 0;
        constraints.gridy = 2;
        win.add(quitButton, constraints);

        setSize(500, 400);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (RESTART.equals(e.getActionCommand())) {
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

    // public static void main(String[] args) throws Exception {
    //     WinGUI frame = new WinGUI();
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setVisible(true);
    // }
}
