package castas;

import hechizosYHabPaladin.GolpeHeroico;
import hechizosYHabPaladin.Sanar;
import hechizosYHabPaladin.TormentaDivina;

public class Paladin extends Casta {

	public Paladin() {
		this.casta = "Paladin";
		this.idCasta = 3;
		this.agregarHechizo("Sanar", new Sanar());
		this.agregarHechizo("Golpe heroico", new GolpeHeroico());
		this.agregarHechizo("Tormenta divina", new TormentaDivina());
	}

}
