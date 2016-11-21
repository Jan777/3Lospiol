package servidorTest;

import org.junit.Assert;
import org.junit.Test;

import conexionSQL.ConexionSQL;
import conexionSQL.OperacionesBD;

public class ConexionSQLTest {

	@Test
	public void queConectaConLaBaseDeDatos() {
		ConexionSQL conexion = new ConexionSQL();
		conexion.conectar();

	}

	@Test
	public void queConsultaCorrectamente() {
		OperacionesBD conexion = new OperacionesBD();
		conexion.conectar();
		String usuario = "NICO";
		Assert.assertEquals(true, conexion.existeUsuario(usuario));
		usuario = "Mascherano";
		Assert.assertEquals(false, conexion.existeUsuario(usuario));
		conexion.desconectar();
	}

	@Test
	public void queInsertaUnUsuario() {
		OperacionesBD conexion = new OperacionesBD();
		conexion.conectar();
		String usuario = "CIRO";
		String contraseña = "YLosPersas";
		Assert.assertEquals(false, conexion.existeUsuario(usuario));
		conexion.insertarUsuario(usuario, contraseña);
		conexion.existeUsuario(usuario);
		Assert.assertEquals(true, conexion.existeUsuario(usuario));
		conexion.desconectar();
	}
}
