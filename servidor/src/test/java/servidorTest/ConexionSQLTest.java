package servidorTest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import conexionSQL.ConexionSQL;
import conexionSQL.OperacionesBD;

public class ConexionSQLTest {

	@Test
	public void queConectaConLaBaseDeDatos() {
		ConexionSQL conexion = new ConexionSQL();
		conexion.conectar();

	}

	@Ignore
	public void queConsultaCorrectamente() {
		OperacionesBD conexion = new OperacionesBD();
		conexion.conectar();
		String usuario = "NICO";
		Assert.assertEquals(true, conexion.existeUsuario(usuario));
		usuario = "Mascherano";
		Assert.assertEquals(false, conexion.existeUsuario(usuario));
		conexion.desconectar();
	}

	@Ignore
	public void queInsertaUnUsuario() {
		OperacionesBD conexion = new OperacionesBD();
		conexion.conectar();
		String usuario = "CIRO";
		String contrase�a = "YLosPersas";
		Assert.assertEquals(false, conexion.existeUsuario(usuario));
		conexion.insertarUsuario(usuario, contrase�a);
		conexion.existeUsuario(usuario);
		Assert.assertEquals(true, conexion.existeUsuario(usuario));
		conexion.desconectar();
	}

	@Test
	public void queValidaLaContrase�a() {
		OperacionesBD conexion = new OperacionesBD();
		conexion.conectar();
		String usuario = "CIRO";
		String contrase�a = "YLosPersas";
		Assert.assertEquals(false, conexion.existeUsuario(usuario));
		conexion.insertarUsuario(usuario, contrase�a);
		Assert.assertEquals(true, conexion.existeUsuario(usuario));
		Assert.assertEquals(true, conexion.validarCredenciales(usuario, contrase�a));
		Assert.assertEquals(false, conexion.validarCredenciales(usuario, "123456"));
		conexion.desconectar();
	}
}
