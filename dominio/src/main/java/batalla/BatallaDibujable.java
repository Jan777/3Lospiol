package batalla;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map.Entry;

import mapa.BuscarImagen;
import mapa.Dibujable;
import personaje.Personaje;
import personaje.PersonajeDibujable;


public class BatallaDibujable {
	
	private String img;
	private int ancho,alto;
	private int anchoCuadro = 800,altoCuadro = 600;
	private HashMap<String,Personaje> retador = new HashMap<String,Personaje>();
	private HashMap<String,Personaje> contrincante = new HashMap<String,Personaje>();
	private String turno;
	
	public BatallaDibujable(Personaje ataca ,Personaje atacado){
		 BuscarImagen buscar = new BuscarImagen();
		 img = "batalla.png";
		 	alto = buscar.altoMapa(img);
		 	ancho = buscar.anchoMapa(img);
	       //this.colisionables = new HashMap<String, PersonajeDibujable>();
		 	turno = ataca.getID();
		 	retador.put(ataca.getID(), ataca);
		 	contrincante.put(atacado.getID(), atacado);		 	
	 }
	
	public void pintarMapa(Graphics g, ImageObserver observer){              
		Graphics2D g2 = (Graphics2D)g;     
		BuscarImagen buscar = new BuscarImagen();
		g2.drawImage(buscar.subImgMapa(img, 0, 0, anchoCuadro, altoCuadro),0, 0, observer);
                
		PersonajeDibujable d;
		int x = 250;
		int y = 200;
		for (Entry<String, Personaje> dibujar : retador.entrySet()){
			
	    	
        		g2.drawImage(dibujar.getValue().getImagen(),x, y , observer);
        		g2.drawString(dibujar.getKey(), x, y );
        	y+= 40;
		}
		
		x = 550;
		y = 200;
		
		for (Entry<String, Personaje> dibujar : contrincante.entrySet()){
			
	    	
    		g2.drawImage(dibujar.getValue().getImagen(),x, y , observer);
    		g2.drawString(dibujar.getKey(), x, y );
    	y+= 40;
	}
		          
    }
	
	public void actualizarBatalla(Dibujable d){
		
		}
	
	public boolean esMiTurno(String id)
	{
		return turno.equals(id);
	}

}
