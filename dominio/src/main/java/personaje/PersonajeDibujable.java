package personaje;

import java.awt.Image;

import mapa.BuscarImagen;
import mapa.Dibujable;

public class PersonajeDibujable implements Dibujable {

	private String iD;
	private String img;
	private int alto, ancho;
	private int x, y, sentido, paso = 0;
	private int xMouse, yMouse;

	public PersonajeDibujable(String ID) {
		img = "character1.png";
		BuscarImagen buscar = new BuscarImagen();
		alto = buscar.alto(img);
		ancho = buscar.ancho(img);
		xMouse = x = 72;
		yMouse = y = 200;
		this.iD = ID;
	}

	public void caminar() {
		if (x == xMouse && y == yMouse)
			return;

		if (x > xMouse) {
			x -= 4;
			paso = 1;
		}

		if (x < xMouse) {
			x += 4;
			paso = 2;
		}
		if (y > yMouse) {
			y -= 4;
			paso = 3;
		}

		if (y < yMouse) {
			y += 4;
			paso = 0;
		}
		sentido = (sentido + 1) % 3;

	}

	public void setXY(int xm, int ym) {
		this.xMouse = xm - (xm % 4);
		this.yMouse = ym - (ym % 4);
	}

	@Override
	public Image getImagen() {
		BuscarImagen buscar = new BuscarImagen();
		return buscar.subImg(img, ancho, alto, sentido, paso);
	}

	@Override
	public int getPosicionX() {
		return x;
	}

	@Override
	public int getPosicionY() {
		return y;
	}

	public int getPosicionXMouse() {
		return xMouse;
	}

	public int getPosicionYMouse() {
		return yMouse;
	}

	@Override
	public int getAncho() {
		return ancho;
	}

	@Override
	public int getAlto() {
		return alto;
	}

	@Override
	public boolean seSuperPonen(Dibujable d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getID() {
		return iD;
	}

	@Override
	public int compareTo(Dibujable d) {
		return this.iD.compareTo(d.getID());
	}

}
