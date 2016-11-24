package test;

import org.junit.Assert;
import org.junit.Test;

import castas.Guerrero;
import castas.Paladin;
import personaje.Personaje;
import personajeEquipado.ConAnillo;
import personajeEquipado.ConArmadura;
import personajeEquipado.ConBastonAghanim;
import personajeEquipado.ConCascoDeLaMuerte;
import personajeEquipado.ConEspada;
import razas.Humano;
import razas.Orco;

public class HumanoTest {

	@Test
	public void quePuedoCrearUnGuerrero() {
		Personaje guerrero = new Humano(new Guerrero(),null,null);

		Assert.assertEquals(100, guerrero.getSalud());
		Assert.assertEquals(100, guerrero.getEnergia());
		Assert.assertEquals(1, guerrero.getNivel());
		Assert.assertEquals(0, guerrero.getExp());
		Assert.assertEquals(10, guerrero.getAtaque());
		Assert.assertEquals(10, guerrero.getDefensa());
		Assert.assertEquals(10, guerrero.getInteligencia());
		Assert.assertEquals(0, guerrero.getMana());
		Assert.assertEquals("Guerrero", guerrero.getCasta().nombreCastaElegida());
	}

	@Test
	public void quePuedoEquiparlo() {
		Personaje guerrero = new Humano(new Guerrero(),null,null);

		guerrero = new ConAnillo(guerrero);
		// aumenta el ataque 15 pts
		Assert.assertEquals(10 + 15, guerrero.getAtaque());

		guerrero = new ConArmadura(guerrero);
		// aumenta la defensa 13 pts
		Assert.assertEquals(10 + 13, guerrero.getDefensa());

		guerrero = new ConBastonAghanim(guerrero); // aumenta el poder de
													// hechizo 5 pts
		Assert.assertEquals(5, guerrero.obtenerPuntosDeHechizos());

		guerrero = new ConEspada(guerrero);
		// aumenta la defensa 13 pts
		Assert.assertEquals(10 + 15 + 5, guerrero.getAtaque());

		guerrero = new ConCascoDeLaMuerte(guerrero);
		// aumenta la defensa 13 pts
		Assert.assertEquals(10 + 13 + 4, guerrero.getDefensa());
	}

	@Test
	public void quePuedeAtacar() {
		Personaje guerrero = new Humano(new Guerrero(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);
		guerrero.atacar(orco);
		Assert.assertEquals(111, orco.getSalud());
		Assert.assertEquals(90, guerrero.getEnergia());
	}

	@Test
	public void quePuedeSerAtacado() {
		Personaje guerrero = new Humano(new Guerrero(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);
		orco.atacar(guerrero);
		Assert.assertEquals(91, guerrero.getSalud());
		Assert.assertEquals(88, orco.getEnergia());
	}

	@Test
	public void quePuedeHechizar() {
		Personaje guerrero = new Humano(new Guerrero(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);

		guerrero.setMana(37);
		Assert.assertTrue(guerrero.aplicarHechizo("Aumentar ataque", orco));
		Assert.assertEquals(12 + 4, orco.getAtaque());
		Assert.assertEquals(37 - 15, guerrero.getMana());

		Assert.assertFalse(guerrero.aplicarHechizo("Desgarrar", orco));
		guerrero.setMana(37);
		Assert.assertTrue(guerrero.aplicarHechizo("Desgarrar", orco));
		Assert.assertEquals(85, orco.getSalud());
		Assert.assertEquals(37 - 30, guerrero.getMana());

		Assert.assertFalse(guerrero.aplicarHechizo("Ejecutar", orco));
		guerrero.setMana(37);
		Assert.assertTrue(guerrero.aplicarHechizo("Ejecutar", orco));
		Assert.assertEquals(72, orco.getSalud());
		Assert.assertEquals(37 - 25, guerrero.getMana());
	}

}
