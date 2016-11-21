package mapa;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.net.URL;

public class BuscarImagen {
	private BufferedImage img;

	public BuscarImagen() {

	}

	public Image subImg(String dibujo, int ancho, int alto, int sentido, int paso) {
		try {
			img = ImageIO.read(new File(
					"D:\\Mis Documentos\\Facultad\\Programación Avanzada\\Programación Avanzada\\Proyectos\\jrpg\\dominio\\src\\main\\java\\img\\"
							+ dibujo));
		} catch (IOException e) {
		}

		return img.getSubimage(ancho * sentido, alto * paso, ancho, alto);

	}

	public Image subImgMapa(String dibujo, int x, int y, int ancho, int alto) {
		try {
			img = ImageIO.read(new File(
					"D:\\Mis Documentos\\Facultad\\Programación Avanzada\\Programación Avanzada\\Proyectos\\jrpg\\dominio\\src\\main\\java\\img\\"
							+ dibujo));
		} catch (IOException e) {
		}

		return img.getSubimage(x, y, ancho, alto);

	}

	public int ancho(String dibujo) {
		try {
			img = ImageIO.read(new File(
					"D:\\Mis Documentos\\Facultad\\Programación Avanzada\\Programación Avanzada\\Proyectos\\jrpg\\dominio\\src\\main\\java\\img\\"
							+ dibujo));
		} catch (IOException e) {
		}

		return (img.getWidth() / 3);
	}

	public int alto(String dibujo) {
		try {
			// String path = "dominio/src/main/java/img" + dibujo;
			//String path2 = getClass().getResource("img/" + dibujo).getPath();
			//URL is = getClass().getResource("/.../img/" + dibujo);
			//String path3 = "src\\main\\java\\img\\" + dibujo;
			String path = "D:\\Mis Documentos\\Facultad\\Programación Avanzada\\Programación Avanzada\\Proyectos\\jrpg\\dominio\\src\\main\\java\\img\\"
					+ dibujo;
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}

		return (img.getHeight() / 4);
	}

	public int anchoMapa(String dibujo) {
		try {
			img = ImageIO.read(new File("C:\\Users\\Nico\\Desktop\\dominio\\bin\\img\\" + dibujo));
		} catch (IOException e) {
		}

		return img.getWidth();
	}

	public int altoMapa(String dibujo) {
		try {
			 img = ImageIO.read(new File("C:\\Users\\Nico\\Desktop\\dominio\\bin\\img\\"+dibujo));
			// img = ImageIO.read(new File("img\\"+dibujo));
		} catch (IOException e) {
		}

		return img.getHeight();
	}

}
