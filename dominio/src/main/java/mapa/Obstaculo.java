package mapa;

public class Obstaculo {

	private Rectangulo obstaculo;

	public Obstaculo(Punto puntoInicial, int base, int altura) {
		obstaculo = new Rectangulo(puntoInicial, base, altura);
	}

}
