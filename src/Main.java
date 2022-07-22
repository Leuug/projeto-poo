/**
* Alunos:
* Bernardo Maia Coelho - 12542481 
* Miguel Reis de Araújo - 12752457
* Raphael Zoega Cali Gomes - 11800729
* Samuel Victorio Bernacci - 12703455
*/

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
