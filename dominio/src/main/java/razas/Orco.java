package razas;

import castas.Casta;
import personaje.Personaje;

public class Orco extends Personaje{
	
	private int cantidadDeAtaques;
	
	public Orco(Casta castaElegida){
		this.salud=120;
		this.ataque=12;
		this.casta=castaElegida;
		this.energia=100;
		this.exp=0;
		this.nivel=1;
		this.cantidadDeAtaques=0;
		this.mana=0;
	}
	
	public boolean aplicarHechizo(String hechizo,Personaje afectado){
		if(hechizo.compareTo("Golpe heroico")==0)
			if(this.mana>37){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(37);
				return true;
			}
		if(hechizo.compareTo("Sanar")==0)
			if(this.mana>10){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(10);
				return true;
			}
		if(hechizo.compareTo("Tormenta divina")==0)
			if(this.mana>37){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(37);
				return true;
			}
		if(hechizo.compareTo("Aumentar ataque")==0)
			if(this.mana>15){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(15);
				return true;
			}
		if(hechizo.compareTo("Desgarrar")==0)
			if(this.mana>30){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(30);
				return true;
			}
		if(hechizo.compareTo("Ejecutar")==0)
			if(this.mana>25){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(25);
				return true;
			}
		return false;
	}
	
	@Override
	protected void despuesDeAtacar() {
		cantidadDeAtaques++;
		mana+=10;
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		return ataque + cantidadDeAtaques;
	}
	
	@Override
	public int calcularPuntosDeHechizos() {
		return 0;
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return 0;
	}

	@Override
	public void serCurado() {
		this.salud=120;
	}


}
