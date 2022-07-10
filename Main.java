import javax.swing.*;

public class Main {
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(
				"com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme");
		GUIHandler frame = new GUIHandler();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
