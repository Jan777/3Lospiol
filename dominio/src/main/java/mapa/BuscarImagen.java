package mapa;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.net.URL;

public class BuscarImagen {
	private BufferedImage img;

	public BuscarImagen() {

	}

	public Image subImg(String dibujo, int ancho, int alto, int sentido, int paso) {
		try {
			img = ImageIO.read(new File("src/main/java/img/"+ dibujo));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al buscar la imagen", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		return img.getSubimage(ancho * sentido, alto * paso, ancho, alto);

	}

	public Image subImgMapa(String dibujo, int x, int y, int ancho, int alto) {
		try {
			img = ImageIO.read(new File("src/main/java/img/"+ dibujo));
		} catch (IOException e) {
		}

		return img.getSubimage(x, y, ancho, alto);

	}

	public int ancho(String dibujo) {
		try {
			img = ImageIO.read(new File("src/main/java/img/"+ dibujo));
		} catch (IOException e) {
		}

		return (img.getWidth() / 3);
	}

	public int alto(String dibujo) {
		try {
			img = ImageIO.read(new File("src/main/java/img/"+ dibujo));
		} catch (IOException e) {
		}

		return (img.getHeight() / 4);
	}

	public int anchoMapa(String dibujo) {
		try {
			img = ImageIO.read(new File("src/main/java/img/"+ dibujo));
		} catch (IOException e) {
		}

		return img.getWidth();
	}

	public int altoMapa(String dibujo) {
		try {
			 img = ImageIO.read(new File("src/main/java/img/"+ dibujo));
			// img = ImageIO.read(new File("img\\"+dibujo));
		} catch (IOException e) {
		}

		return img.getHeight();
	}

}
