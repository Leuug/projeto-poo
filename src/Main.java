import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // adicionando um tema na GUI
        UIManager.setLookAndFeel(
            "com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme");

        GUIHandler frame = new GUIHandler();
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
