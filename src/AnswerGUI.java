import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class AnswerGUI extends JFrame implements ActionListener {
    GUIHandler guihander;
    ImageGUI imageFrame;
    JButton movie1Button;
    JButton movie2Button;
    JButton movie3Button;
    JButton movie4Button;

    boolean active;

    public String movie1name = "ALIEN";
    public String movie2name = "AMERICAN PSYCHO";
    public String movie3name = "RAIDERS OF THE LOST ARK";
    public String movie4name =
        "THE LORD OF THE RINGS: THE FELLOWSHIP OF THE RING";

    // public String movie1file = "./img/alien.jpg";
    // public String movie2file = "./img/apsycho.jpg";
    // public String movie3file = "./img/rotla.jpg";
    // public String movie4file = "./img/tlotrtfotr.jpg";

    public AnswerGUI(GUIHandler _guihander) throws Exception {
        super("Answer");
        guihander = _guihander;

        this.active = true;

        JPanel guiPanel = (JPanel)this.getContentPane();
        guiPanel.setLayout(new GridLayout(2, 2, 1, 1));

        movie1Button = new JButton(movie1name);
        movie1Button.addActionListener(this);
        movie1Button.setActionCommand("movie1");
        guiPanel.add(movie1Button);

        movie2Button = new JButton(movie2name);
        movie2Button.addActionListener(this);
        movie2Button.setActionCommand("movie2");
        guiPanel.add(movie2Button);

        movie3Button = new JButton(movie3name);
        movie3Button.addActionListener(this);
        movie3Button.setActionCommand("movie3");
        guiPanel.add(movie3Button);

        movie4Button = new JButton(movie4name);
        movie4Button.addActionListener(this);
        movie4Button.setActionCommand("movie4");
        guiPanel.add(movie4Button);

        this.setSize(750, 200);

        imageFrame = new ImageGUI();
        imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.active) this.active = false;
        else return;

        if ("movie1".equals(e.getActionCommand())) {
            AnswerPoster(movie1name);
        } else if ("movie2".equals(e.getActionCommand())) {
            AnswerPoster(movie2name);
        } else if ("movie3".equals(e.getActionCommand())) {
            AnswerPoster(movie3name);
        } else if ("movie4".equals(e.getActionCommand())) {
            AnswerPoster(movie4name);
        }
    }

    public void AnswerPoster(String movieName) {
        guihander.SetAnswer(movieName);
    }

	public void SetImage(String imageDir)
	{
		imageFrame.SetImage(imageDir);
	}

    public void SetMovies(String _movie1name,
                          String _movie2name,
                          String _movie3name,
                          String _movie4name) {
        this.movie1name = _movie1name;

        this.movie2name = _movie2name;

        this.movie3name = _movie3name;

        this.movie4name = _movie4name;

        this.refresh();
    }

    public void refresh ()
    {
        this.active = true;
        this.movie1Button.setText(this.movie1name);
        this.movie2Button.setText(this.movie2name);
        this.movie3Button.setText(this.movie3name);
        this.movie4Button.setText(this.movie4name);
    }
}
