import cinewise.CineSession;
import javax.swing.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// adicionando um tema na GUI
		UIManager.setLookAndFeel(
				"com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme");

		StartMenu startMenu = new StartMenu();
		startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startMenu.setVisible(true);
	}
}
