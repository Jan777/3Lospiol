package mapa;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map.Entry;

import personaje.Personaje;
import personaje.PersonajeDibujable;

public class Mapa {

	private String img;
	private int ancho, alto;
	private int anchoCuadro = 800, altoCuadro = 600;
	private HashMap<String, PersonajeDibujable> colisionables;

	public Mapa() {
		BuscarImagen buscar = new BuscarImagen();
		img = "stage2.png";
		alto = buscar.altoMapa(img);
		ancho = buscar.anchoMapa(img);
		this.colisionables = new HashMap<String, PersonajeDibujable>();
	}

	public Mapa(Mapa map) {

		this.ancho = map.ancho;
		this.alto = map.alto;
		this.colisionables = map.colisionables;
	}

	public int xRespectoPersonajeMapa(PersonajeDibujable pers) {
		int xRealitivo = pers.getPosicionX() - (this.anchoCuadro / 2);
		if (xRealitivo < 0) {
			return 0;
		}

		if ((xRealitivo + this.anchoCuadro) > this.ancho)
			return this.ancho - this.anchoCuadro;

		return xRealitivo;
	}

	public int yRespectoPersonajeMapa(PersonajeDibujable pers) {
		int yRealitivo = pers.getPosicionY() - (this.altoCuadro / 2);
		if (yRealitivo < 0) {
			return 0;
		}

		if ((yRealitivo + this.altoCuadro) > this.alto)
			return this.alto - this.altoCuadro;

		return yRealitivo;
	}

	public boolean sePuedeUbicar(PersonajeDibujable pers, int x, int y) {

		return true;
	}

	public PersonajeDibujable hayBatalla(PersonajeDibujable pers) {
		PersonajeDibujable enemigo;
		for (Entry<String, PersonajeDibujable> contrincante : colisionables.entrySet()) {

			if (!(pers.getID().equals(contrincante.getKey())) && contrincante.getValue().enBatalla() == false
					&& pers.seSuperPonen(contrincante.getValue())) {
				enemigo = contrincante.getValue();
				return enemigo;
			}
		}
		return null;
	}

	public void pintarMapa(Graphics g, PersonajeDibujable pers, ImageObserver observer) {
		Graphics2D g2 = (Graphics2D) g;
		int xRelativo = this.xRespectoPersonajeMapa(pers);
		int yRelativo = this.yRespectoPersonajeMapa(pers);
		BuscarImagen buscar = new BuscarImagen();
		g2.drawImage(buscar.subImgMapa(img, xRelativo, yRelativo, anchoCuadro, altoCuadro), 0, 0, observer);

		PersonajeDibujable d;

		for (Entry<String, PersonajeDibujable> dibujar : colisionables.entrySet()) {

			d = dibujar.getValue();
			int x = d.getPosicionX();
			int y = d.getPosicionY();
			if (d.enBatalla() == false && x > xRelativo && x < (xRelativo + this.anchoCuadro) && y > yRelativo
					&& y < (yRelativo + this.altoCuadro)) {
				g2.drawImage(d.getImagen(), d.getPosicionX() - xRelativo - (d.getAncho() / 2),
						d.getPosicionY() - yRelativo - d.getAlto(), observer);
				g2.drawString(d.getID(), d.getPosicionX() - xRelativo - (d.getAncho() / 2),
						d.getPosicionY() - yRelativo - d.getAlto());
			}
		}

	}

	public void agregarDibujable(Dibujable d) {
		colisionables.put(d.getID(), (PersonajeDibujable) d);
	}

	public void quitar(String id) {
		colisionables.remove(id);
	}

	public synchronized void actualizarMapa(Dibujable d) {
		// colisionables.replace(d.getID(), (Personaje)d);
		colisionables.remove(d.getID());
		colisionables.put(d.getID(), (PersonajeDibujable) d);
	}
}
