package servidorTest;

import javax.swing.JOptionPane;

import servidor.ServidorJuego;

public class ServerTest {

	public static void main(String[] args) {
		try {
			ServidorJuego server = new ServidorJuego();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al Inicial el Servidor", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
