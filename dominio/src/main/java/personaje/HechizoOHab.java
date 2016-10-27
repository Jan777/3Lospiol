package personaje;


public abstract class HechizoOHab {
	protected int costo;
	
	public abstract void afectar(Personaje personaje,int poderDeHabilidad);
	
	public int costoHabilidad(){
		return costo;
	}
}
