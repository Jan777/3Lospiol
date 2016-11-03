package mapa;

public class Rectangulo {

	int base;
	int altura;
	Punto puntoInicial;

	public Rectangulo(Punto puntoInicial, int base, int altura) {
		this.puntoInicial = puntoInicial;
		this.base = base;
		this.altura = altura;
	}

	public double area() {
		return base * altura;
	}

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

}
