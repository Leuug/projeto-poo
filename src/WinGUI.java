import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import cinewise.*;
import java.io.*;
import java.util.*;

public class WinGUI extends JFrame implements ActionListener {

	private JPanel win;
	private JButton restartButton;
	private JButton quitButton;
	private JLabel nomeLabel;
	private JLabel Leaderboard;

	private String RESTART = "restart";
	private String QUIT = "quit";

	public WinGUI(CineSession session, GUIHandler score, AnswerGUI answer, ImageGUI image) throws Exception {
		score.setVisible(false);
		answer.setVisible(false);
		image.setVisible(false);

		int pontos = session.getScore();

		File arquivo = new File("Leaderboard.txt");
		boolean existe = arquivo.exists();

		if (existe == false) {
			arquivo.createNewFile();
		}

		Integer[] num = new Integer[4];
		int i;

		FileReader fr = new FileReader(arquivo);
		BufferedReader br = new BufferedReader(fr);

		for (i = 0; i < 4; i++) {
			num[i] = 0;
		}

		for (i = 0; br.ready(); i++) {
			String linha = br.readLine();
			num[i] = Integer.parseInt(linha);
		}

		br.close();
		fr.close();

		num[3] = pontos;
		for (i = 0; i < 4; i++) {
			System.out.println(num[i]);
		}

		Arrays.sort(num, Collections.reverseOrder());

		FileWriter fw = new FileWriter(arquivo);
		BufferedWriter bw = new BufferedWriter(fw);

		for (i = 0; i < 3; i++) {
			System.out.println(num[i]);
			bw.write(String.valueOf(num[i]));
			bw.newLine();
		}

		bw.close();
		fw.close();

		win = (JPanel) this.getContentPane();
		win.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(2, 2, 40, 2);

		String scoreLabel = String.format("<html><font size=+2><b>O jogo acabou!<br/>Seu score: %d</b></font></html>",
				pontos);
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

		String leaderboardLabel = String.format(
				"<html><font size=+2><b><br/>1st: %d<br/>2nd: %d<br/>3rd: %d</b></font></html>",
				num[0], num[1], num[2]);
		Leaderboard = new JLabel(leaderboardLabel);
		constraints.gridx = 0;
		constraints.gridy = 4;
		win.add(Leaderboard, constraints);

		setSize(500, 400);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (RESTART.equals(e.getActionCommand())) {
			this.setVisible(false);

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
}
