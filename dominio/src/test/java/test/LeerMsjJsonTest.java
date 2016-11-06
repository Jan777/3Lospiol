package test;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import personaje.CuentaUsuario;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LeerMsjJsonTest {
	
	@Test
	public void puedoLeerMsjJson() throws JsonParseException, JsonMappingException, IOException{
		
		File jsonFile= new File("CrearCuenta.json");
		CuentaUsuario cuenta= null;
		ObjectMapper mapper= new ObjectMapper();
		
		cuenta= mapper.readValue(jsonFile, CuentaUsuario.class);
		//probando mensaje de CrearCuenta
		//obtengo nombre de usuario
		Assert.assertEquals("Carlos", cuenta.getUsuario());
		//obtengo clave de usuario
		Assert.assertEquals("clave", cuenta.getPassword());
		
	}
}
