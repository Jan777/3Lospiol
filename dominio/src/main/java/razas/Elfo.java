package razas;

import castas.Casta;
import personaje.Personaje;

public class Elfo extends Personaje {
	
	int ataquesRecibidos;
	
	public Elfo(Casta castaElegida){
		this.MaxSalud = 85;
		this.MaxEnergia = 120;
		this.salud=85;
		this.ataque=10;
		this.defensa=8;
		this.inteligencia=12;
		this.casta=castaElegida;
		/*
		String casta=castaElegida.obtenerCastaElegida();
		if(casta.compareTo("Brujo")==0)
			this.poderDeHechizo=4;
		else
			this.poderDeHechizo=0;
		*/
		this.energia=120;
		this.exp=0;
		this.nivel=1;
		this.ataquesRecibidos=0;
		this.mana=5;
	}
	/*
	public boolean aplicarHechizo(String hechizo,Personaje afectado){
		
		if(hechizo.compareTo("Golpe heroico")==0)
			if(this.mana>=37){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(37);
				return true;
			}
		if(hechizo.compareTo("Sanar")==0)
			if(this.mana>=10){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(10);
				return true;
			}
		if(hechizo.compareTo("Tormenta divina")==0)
			if(this.mana>=37){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(37);
				return true;
			}
		if(hechizo.compareTo("Bola de la oscuridad")==0)
			if(this.mana>=23){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(37);
				return true;
			}
		if(hechizo.compareTo("Disminuir ataque")==0)
			if(this.mana>=14){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(14);
				return true;
			}
		if(hechizo.compareTo("Latigazo mortal")==0)
			if(this.mana>=30){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(30);
				return true;
			}
		return false;
	}
	*/
	
	@Override
	protected void despuesDeAtacar() {
		mana+=10;
	}
	/*
	@Override
	public int calcularPuntosDeHechizos() {
		return poderDeHechizo;
	}*/
	
	@Override
	public int calcularPuntosDeAtaque() {
		return ataque + ataquesRecibidos + this.ataqueAfectado;
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public void serAtacado(int daño) {
		super.serAtacado(daño);
		this.ataquesRecibidos++;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return 0;
	}

	

}
