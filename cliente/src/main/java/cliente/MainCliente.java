package cliente;

import gui.CrearPersonaje;
import gui.Juego;
import gui.Login;

import java.awt.EventQueue;
import java.io.IOException;

import personaje.Personaje;
import personaje.PersonajeDibujable;

public class MainCliente {

	public static void main(String args[]) throws IOException {
		boolean noEntro=true;
		Personaje p = null;
		PersonajeDibujable d = null;
		
		Login login = new Login();
		
		
		do{
			if(true)///login.seCerro())
				if(tienePersonaje()==false){
					noEntro=false;
					CrearPersonaje crear= new CrearPersonaje(login.getUsuario(), login.obtenerCliente(),login);
					crear.setVisible(true);
					do{
						if(crear.seCerro()==false){
							
							p=crear.obtenerPersonaje();
							d=crear.obtenerPersDibujable();
						}
					}while(crear.seCerro()==false);
					
				}else{
					//SACAR DATOS DE LA BASE DE DATOS :D
				}
		}while(login.seCerro()==false && noEntro);
		
		new Juego(login.obtenerCliente(),login.getUsuario(),d,p);
	}
	// PREGUNTA A LA BASE DE DATOS SI TIENE PERSONAJE
	private static boolean tienePersonaje() {
		return false;
	}
}
