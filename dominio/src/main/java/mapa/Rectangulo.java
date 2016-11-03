package mapa;

public class Rectangulo {
	int base;
	int altura;
	private Punto puntoInicial;

	// Constructores
	public Rectangulo(Punto puntoInicial, int base, int altura) {
		this.puntoInicial = puntoInicial;
		this.base = base;
		this.altura = altura;
	}

	// Getters and Setters
	public double getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	// Metodos adicionales
	public double area() {
		return base * altura;
	}
}
