package mapa;

public class Punto {

	private double x, y;

	// Constructores
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Getters and Setters
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Punto2D [x=" + x + ", y=" + y + "]";
	}

	// Metodos adicionales
	public double modulo() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}

	public double distanciaAlOrigen() {
		return this.modulo();
	}

	public double distanciaAOtroPunto(Punto p) {
		return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
	}

	public Punto desplazar(Punto p) {
		this.x += p.getX();
		this.y += p.getY();
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto other = (Punto) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

}
