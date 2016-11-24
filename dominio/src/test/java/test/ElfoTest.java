package test;

import org.junit.Assert;
import org.junit.Test;

import castas.Brujo;
import castas.Paladin;
import personaje.Personaje;
import personajeEquipado.ConAnillo;
import personajeEquipado.ConArmadura;
import personajeEquipado.ConBastonAghanim;
import personajeEquipado.ConCascoDeLaMuerte;
import personajeEquipado.ConEspada;
import razas.Elfo;
import razas.Humano;
import razas.Orco;

public class ElfoTest {

	@Test
	public void quePuedoCrearUnPaladin() {
		Personaje paladin = new Elfo(new Brujo(),null,null);

		Assert.assertEquals(85, paladin.getSalud());
		Assert.assertEquals(120, paladin.getEnergia());
		Assert.assertEquals(1, paladin.getNivel());
		Assert.assertEquals(0, paladin.getExp());
		Assert.assertEquals(10, paladin.getAtaque());
		Assert.assertEquals(2, paladin.getDefensa());
		Assert.assertEquals(12, paladin.getInteligencia());
		Assert.assertEquals(3, paladin.getMana());
		Assert.assertEquals("Brujo", paladin.getCasta().nombreCastaElegida());
	}

	@Test
	public void quePuedoEquiparlo() {
		Personaje paladin = new Elfo(new Brujo(),null,null);

		paladin = new ConAnillo(paladin);
		// aumenta el ataque 15 pts
		Assert.assertEquals(10 + 15, paladin.getAtaque());

		paladin = new ConArmadura(paladin);
		// aumenta la defensa 13 pts
		Assert.assertEquals(2 + 13, paladin.getDefensa());

		paladin = new ConBastonAghanim(paladin); // aumenta el poder de hechizo
													// 5 pts
		Assert.assertEquals(10, paladin.obtenerPuntosDeHechizos());

		paladin = new ConEspada(paladin);
		// aumenta la defensa 13 pts
		Assert.assertEquals(10 + 15 + 5, paladin.getAtaque());

		paladin = new ConCascoDeLaMuerte(paladin);
		// aumenta la defensa 13 pts
		Assert.assertEquals(2 + 13 + 4, paladin.getDefensa());
	}

	@Test
	public void quePuedeAtacar() {
		Personaje paladin = new Elfo(new Brujo(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);
		paladin.atacar(orco);
		Assert.assertEquals(111, orco.getSalud());
		Assert.assertEquals(110, paladin.getEnergia());
	}

	@Test
	public void quePuedeSerAtacado() {
		Personaje paladin = new Elfo(new Brujo(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);
		orco.atacar(paladin);
		Assert.assertEquals(73, paladin.getSalud());
		Assert.assertEquals(88, orco.getEnergia());
	}

	@Test
	public void quePuedeHechizar() {
		Personaje paladin = new Elfo(new Brujo(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);

		Assert.assertFalse(paladin.aplicarHechizo("Bola de la oscuridad", orco));
		paladin.setMana(37);
		Assert.assertTrue(paladin.aplicarHechizo("Bola de la oscuridad", orco));
		Assert.assertEquals(81, orco.getSalud());
		Assert.assertEquals(37 - 23, paladin.getMana());

		Assert.assertFalse(paladin.aplicarHechizo("Disminuir ataque", orco));
		paladin.setMana(37);
		Assert.assertTrue(paladin.aplicarHechizo("Disminuir ataque", orco));
		Assert.assertEquals(6, orco.getAtaque());
		Assert.assertEquals(37 - 15, paladin.getMana());

		Assert.assertFalse(paladin.aplicarHechizo("Latigaso mortal", orco));
		paladin.setMana(37);
		Assert.assertTrue(paladin.aplicarHechizo("Latigaso mortal", orco));
		Assert.assertEquals(37, orco.getSalud());
		Assert.assertEquals(37 - 30, paladin.getMana());
	}

}
