package exam;

import javax.swing.*;

public class Main {

    public static void main(String[] args){

        SwingUtilities.invokeLater( () -> {     //metoda invokeLater wersja za pomocą wyrażenia lambda
            CirclesWindow circlesWindow = new CirclesWindow();
            circlesWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            circlesWindow.setVisible(true);

        });
    }
}