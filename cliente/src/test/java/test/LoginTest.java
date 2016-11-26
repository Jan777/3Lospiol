package test;

import javax.swing.JOptionPane;

import org.junit.Test;

import gui.Login;

public class LoginTest {


	@Test
	public void queMuestraLaPantallaDelLogin(){
		Login login = null;
		try {
			login = new Login();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error servidor no conectado!", "Error al ejecutar el juego",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
