package mapa;

import java.awt.Image;

import personaje.PersonajeDibujable;

public interface Dibujable  extends Comparable<Dibujable>{
	public String getID();
	public int getPosicionX();
	public int getPosicionY();
	public int getAncho();
	public int getAlto();
	public Image getImagen();
	
}
