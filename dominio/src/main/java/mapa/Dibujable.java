package mapa;

import java.awt.Image;

public interface Dibujable  extends Comparable<Dibujable>{
	public String getID();
	public int getPosicionX();
	public int getPosicionY();
	public int getAncho();
	public int getAlto();
	public Image getImagen();
	public boolean seSuperPonen(Dibujable d);
	
}
