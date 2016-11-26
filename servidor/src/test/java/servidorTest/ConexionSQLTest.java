package servidorTest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import castas.Guerrero;
import conexionSQL.ConexionSQL;
import conexionSQL.OperacionesBD;
import personaje.Personaje;
import personaje.PersonajeDibujable;
import razas.Humano;

public class ConexionSQLTest {

	@Ignore
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
		String contraseña = "YLosPersas";
		Assert.assertEquals(false, conexion.existeUsuario(usuario));
		conexion.insertarUsuario(usuario, contraseña);
		conexion.existeUsuario(usuario);
		Assert.assertEquals(true, conexion.existeUsuario(usuario));
		conexion.desconectar();
	}

	@Ignore
	public void queValidaLaContraseña() {
		OperacionesBD conexion = new OperacionesBD();
		conexion.conectar();
		String usuario = "CIRO";
		String contraseña = "YLosPersas";
		Assert.assertEquals(false, conexion.existeUsuario(usuario));
		conexion.insertarUsuario(usuario, contraseña);
		Assert.assertEquals(true, conexion.existeUsuario(usuario));
		Assert.assertEquals(true, conexion.validarCredenciales(usuario, contraseña));
		Assert.assertEquals(false, conexion.validarCredenciales(usuario, "123456"));
		conexion.desconectar();
	}

//	@Test
//	public void queInsertaUnPersonaje() {
//		String nombrePersonaje = "NICO";
//		Personaje personaje = new Humano(new Guerrero(), nombrePersonaje, "humanoG");
//		OperacionesBD conexion = new OperacionesBD();
//		conexion.conectar();
//		Assert.assertEquals(true, conexion.insertarPersonaje(personaje, null));
//		conexion.desconectar();
//	}
	
//	@Test
//	public void queInsertaUnPersonajeDibujable() {
//		String nombrePersonaje = "NICO";
//		String img = "orcoP";
//		OperacionesBD conexion = new OperacionesBD();
//		conexion.conectar();
//		Assert.assertEquals(true, conexion.insertarPersonajeDibujable(nombrePersonaje, img));
//		conexion.desconectar();
//	}
	
	@Test
	public void queConsultaElPersonaje(){
		String nombrePersonaje = "NICO";
		OperacionesBD conexion = new OperacionesBD();
		conexion.conectar();
		Assert.assertEquals(true, conexion.consultarPersonaje(nombrePersonaje));
		conexion.desconectar();
	}
	@Test
	public void queObtieneElPersonaje(){
		String nombrePersonaje = "NICO";
		OperacionesBD conexion = new OperacionesBD();
		conexion.conectar();
	//	Personaje personaje = conexion.obtenerPersonaje(nombrePersonaje);
		//Assert.assertEquals(personaje, personaje);
		conexion.desconectar();
	}
	
}
