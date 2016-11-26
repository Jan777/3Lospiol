package servidorTest;

import javax.swing.JOptionPane;
import org.junit.Test;
import servidor.ServidorJuego;

public class ServerTest {

	@Test
	public void queLevantaElServidor() {
		try {
			ServidorJuego server = new ServidorJuego();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al Inicial el Servidor", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
