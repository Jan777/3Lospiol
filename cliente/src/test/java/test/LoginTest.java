package test;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.Login;

public class LoginTest {

	public static void main(String[] args) throws Exception {
		Login login = null;
		try {
			login = new Login();
			login.main(null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error servidor no conectado!", "Error al ejecutar el juego",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
