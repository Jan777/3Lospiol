package razas;

import castas.Casta;
import personaje.Personaje;

public class Humano extends Personaje{
	
	public Humano(Casta castaElegida){
		this.MaxSalud = 100;
		this.MaxEnergia = 100;
		this.salud=100;
		this.ataque=10;
		this.defensa=10;
		this.inteligencia=10;
		this.casta=castaElegida;
		//String casta=castaElegida.obtenerCastaElegida();
		/*
		if(casta.compareTo("brujo")==0)
			this.poderDeHechizo=4;
		else
			this.poderDeHechizo=0;*/
		this.energia=100;
		this.exp=0;
		this.nivel=1;
		this.mana=1;
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
		if(hechizo.compareTo("Aumentar ataque")==0)
			if(this.mana>=15){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(15);
				return true;
			}
		if(hechizo.compareTo("Desgarrar")==0)
			if(this.mana>=30){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(30);
				return true;
			}
		if(hechizo.compareTo("Ejecutar")==0)
			if(this.mana>=25){
				this.casta.hechizar(hechizo, afectado);
				this.consumirMana(25);
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
	}*/
	
	@Override
	protected void despuesDeAtacar() {
		mana+=12;
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		return ataque + this.ataqueAfectado;
	}
	/*
	@Override
	public int calcularPuntosDeHechizos() {
		return poderDeHechizo;
	}*/

	@Override
	public boolean puedeAtacar() {
		return energia >= 10;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return 5;
	}
	
	
}
