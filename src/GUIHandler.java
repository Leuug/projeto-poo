import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIHandler extends JFrame implements ActionListener {
	ImageGUI imageFrame;
	JButton movie1Button;
	JButton movie2Button;
	JButton movie3Button;
	JButton movie4Button;

	public String movie1name = "ALIEN";
	public String movie2name = "AMERICAN PSYCHO";
	public String movie3name = "RAIDERS OF THE LOST ARK";
	public String movie4name = "THE LORD OF THE RINGS: THE FELLOWSHIP OF THE RING";

	// o caminho para as imagens deve ser passado considerando que o programa
	// vai ser executado a pertir da raiz do projeto
	public String movie1file = "./Images/alien.jpg";
	public String movie2file = "./Images/apsycho.jpg";
	public String movie3file = "./Images/rotla.jpg";
	public String movie4file = "./Images/tlotrtfotr.jpg";

	public GUIHandler() throws IOException {
		super("MovieGuesser"); // nome temporário

		// JPanel guiPanel = (JPanel)this.getContentPane();
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(800, 640));

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 1, 1));

		movie1Button = new JButton(movie1name);
		movie1Button.addActionListener(this);
		movie1Button.setActionCommand("movie1");
		buttonsPanel.add(movie1Button);

		movie2Button = new JButton(movie2name);
		movie2Button.addActionListener(this);
		movie2Button.setActionCommand("movie2");
		buttonsPanel.add(movie2Button);

		movie3Button = new JButton(movie3name);
		movie3Button.addActionListener(this);
		movie3Button.setActionCommand("movie3");
		buttonsPanel.add(movie3Button);

		movie4Button = new JButton(movie4name);
		movie4Button.addActionListener(this);
		movie4Button.setActionCommand("movie4");
		buttonsPanel.add(movie4Button);

		add(buttonsPanel, BorderLayout.SOUTH);

		JPanel imagePanel = new JPanel(new BorderLayout());
		imageFrame = new ImageGUI();
		imagePanel.add(ImageGUI.picture, BorderLayout.CENTER);

		add(imagePanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("movie1".equals(e.getActionCommand())) {
			imageFrame.SetImage(movie1file);
		} else if ("movie2".equals(e.getActionCommand())) {
			imageFrame.SetImage(movie2file);
		} else if ("movie3".equals(e.getActionCommand())) {
			imageFrame.SetImage(movie3file);
		} else if ("movie4".equals(e.getActionCommand())) {
			imageFrame.SetImage(movie4file);
		}
	}

	public static void main(String[] args) throws Exception {
		GUIHandler frame = new GUIHandler();
		// frame.setSize(750, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
