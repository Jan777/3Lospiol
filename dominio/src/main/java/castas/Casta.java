package castas;

import habDeGuerrero.AumentarAtaque;
import habDeGuerrero.Desgarrar;
import habDeGuerrero.Ejecutar;
import hechizosDeBrujo.BolaDeLaOscuridad;
import hechizosDeBrujo.DisminuirAtaque;
import hechizosDeBrujo.LatigazoMortal;
import hechizosYHabPaladin.GolpeHeroico;
import hechizosYHabPaladin.Sanar;
import hechizosYHabPaladin.TormentaDivina;

import java.util.HashMap;
import java.util.Map;

import personaje.HechizoOHab;
import personaje.Personaje;
import personaje.Raza;
import razas.Elfo;
import razas.Humano;
import razas.Orco;

public class Casta{
	protected int CANT_MAX_HECHIZOS;
	protected Map<String, HechizoOHab> hechizos = new HashMap<String, HechizoOHab>();
	protected Personaje personajeCasta;
	
	public Casta (Raza raza,String casta){
		CANT_MAX_HECHIZOS=0;
		if(raza.razaElegida.compareTo("Orco")==0){
			this.personajeCasta=new Orco();
			this.personajeCasta.setVida(120);
			if(casta.compareTo("Paladin")==0){
				this.agregarHechizo("Sanar", new Sanar());
				this.agregarHechizo("Golpe heroico", new GolpeHeroico());
				this.agregarHechizo("Tormenta divina", new TormentaDivina());
			}
			if(casta.compareTo("Guerrero")==0){
				this.agregarHechizo("Aumentar ataque", new AumentarAtaque());
				this.agregarHechizo("Ejecutar", new Ejecutar());
				this.agregarHechizo("Desgarrar", new Desgarrar());
			}
		}
		if(raza.razaElegida.compareTo("Humano")==0){
			this.personajeCasta=new Humano();
			if(casta.compareTo("Paladin")==0){
				this.agregarHechizo("Sanar", new Sanar());
				this.agregarHechizo("Golpe heroico", new GolpeHeroico());
				this.agregarHechizo("Tormenta divina", new TormentaDivina());
			}
			if(casta.compareTo("Guerrero")==0){
				this.agregarHechizo("Aumentar ataque", new AumentarAtaque());
				this.agregarHechizo("Ejecutar", new Ejecutar());
				this.agregarHechizo("Desgarrar", new Desgarrar());
				
			}
			if(casta.compareTo("Brujo")==0){
				this.agregarHechizo("Disminuir ataque", new DisminuirAtaque());
				this.agregarHechizo("Bola de la oscuridad", new BolaDeLaOscuridad());
				this.agregarHechizo("Latigazo mortal", new LatigazoMortal());
				
			}
		}
		
		if(raza.razaElegida.compareTo("Elfo")==0){
			this.personajeCasta=new Elfo();
			this.personajeCasta.setEnergia(120);
			if(casta.compareTo("Paladin")==0){
				this.agregarHechizo("Sanar", new Sanar());
				this.agregarHechizo("Golpe heroico", new GolpeHeroico());
				this.agregarHechizo("Tormenta divina", new TormentaDivina());
			}
			if(casta.compareTo("Brujo")==0){
				this.agregarHechizo("Disminuir ataque", new DisminuirAtaque());
				this.agregarHechizo("Bola de la oscuridad", new BolaDeLaOscuridad());
				this.agregarHechizo("Latigazo mortal", new LatigazoMortal());
			}
		}
	}
	
	public void agregarHechizo(String conjuro, HechizoOHab hechizo) {
		if(CANT_MAX_HECHIZOS<3){
			this.hechizos.put(conjuro, hechizo);
			this.CANT_MAX_HECHIZOS++;
		}
	}
	
	public int getCantidadDeHechizos() {
		return this.hechizos.size();
	}

	public void hechizar(String conjuro, Personaje personaje) {
		this.hechizos.get(conjuro).afectar(personaje);
	}
}
