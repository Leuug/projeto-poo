import javax.swing.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		GUIHandler frame = new GUIHandler();
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
