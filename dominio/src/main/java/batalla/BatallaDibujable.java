package batalla;

import java.util.HashMap;

import mapa.BuscarImagen;
import personaje.PersonajeDibujable;

public class BatallaDibujable {
	
	private String img;
	private int ancho,alto;
	private int anchoCuadro = 800,altoCuadro = 600;
	private HashMap<String,PersonajeDibujable> retador = new HashMap<String,PersonajeDibujable>();
	private HashMap<String,PersonajeDibujable> contrincante = new HashMap<String,PersonajeDibujable>();
	
	
	public BatallaDibujable(){
		 BuscarImagen buscar = new BuscarImagen();
		 img = "batalla.png";
		 	alto = buscar.altoMapa(img);
		 	ancho = buscar.anchoMapa(img);
	       //this.colisionables = new HashMap<String, PersonajeDibujable>();
	 }
	

}
