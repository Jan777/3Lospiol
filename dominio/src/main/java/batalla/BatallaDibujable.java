package batalla;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import mapa.BuscarImagen;
import mapa.Dibujable;
import personaje.Personaje;
import personaje.PersonajeDibujable;

public class BatallaDibujable {

	private String img;
	private String mensajeBatalla;
	private int ancho, alto;
	private int anchoCuadro = 800, altoCuadro = 600;
	private HashMap<String, Personaje> retador = new HashMap<String, Personaje>();
	private HashMap<String, PersonajeDibujable> contrincante = new HashMap<String, PersonajeDibujable>();
	private String turno;

	public BatallaDibujable(Personaje ataca, String bando, PersonajeDibujable enemigo) {
		BuscarImagen buscar = new BuscarImagen();
		img = "batalla.png";
		alto = buscar.altoMapa(img);
		ancho = buscar.anchoMapa(img);
		// this.colisionables = new HashMap<String, PersonajeDibujable>();
		retador.put(ataca.getID(), ataca);
		contrincante.put(enemigo.getID(), enemigo);
		turno = ataca.getID();
		bando = "R";
	}

	public void agregarContrincante(Personaje ataca, String bando) {
		contrincante.put(ataca.getID(), ataca);
		bando = "C";
	}

	public void pintarBatalla(Graphics g, ImageObserver observer) {
		Graphics2D g2 = (Graphics2D) g;
		BuscarImagen buscar = new BuscarImagen();
		g2.drawImage(buscar.subImgMapa(img, 0, 0, anchoCuadro, altoCuadro / 2), 0, 0, observer);

		PersonajeDibujable d;
		int x = 250;
		int y = 200;
		for (Entry<String, Personaje> dibujar : retador.entrySet()) {

			g2.drawImage(dibujar.getValue().getImagen(), x, y, observer);
			g2.drawString(dibujar.getKey(), x, y);
			y += 40;
		}

		x = 550;
		y = 200;

		for (Entry<String, PersonajeDibujable> dibujar : contrincante.entrySet()) {

			g2.drawImage(dibujar.getValue().getImagen(), x, y, observer);
			g2.drawString(dibujar.getKey(), x, y);
			y += 40;
		}
		

		Image menu = new ImageIcon("src/main/java/img/menu.png").getImage();
		Image boton = new ImageIcon("src/main/java/img/botonMenu.png").getImage();
		g2.drawImage(menu, 10, 400, observer);
		String opcion = null;
		g.drawImage(boton, 15, 400, observer);
		g.drawString("ATACAR", 15, 400);
		/*g.drawImage(boton, 15, 460, observer);
		g.drawString("defender", 18, 465);*/

	}

	public void pintarMenu(Graphics g, Personaje pers, String grupo, ImageObserver observer, MouseListener ml) {
		Graphics2D g2 = (Graphics2D) g;
		BuscarImagen buscar = new BuscarImagen();

		Image menu = new ImageIcon("src/main/java/img/menu.png").getImage();
		Image boton = new ImageIcon("src/main/java/img/botonMenu.png").getImage();
		g2.drawImage(menu, 10, 500, observer);
		String opcion = null;

		g2.drawImage(menu, 10, 500, observer);
		// String opcion = null;
		g2.drawImage(boton, 15, 490, observer);
		g2.drawString("atacar", 18, 495);
		g2.drawImage(boton, 15, 460, observer);
		g2.drawString("defender", 18, 465);

		int x;
		int y;
		System.out.println("menu");

		g.dispose();

	}

	public void actualizarBatalla(Dibujable d) {

	}

	public boolean esMiTurno(String id) {
		return turno.equals(id);
	}

}
