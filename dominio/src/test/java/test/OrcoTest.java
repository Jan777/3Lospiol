package test;

import org.junit.Assert;
import org.junit.Test;

import castas.Paladin;
import personaje.Personaje;
import personajeEquipado.ConAnillo;
import personajeEquipado.ConArmadura;
import personajeEquipado.ConBastonAghanim;
import personajeEquipado.ConCascoDeLaMuerte;
import personajeEquipado.ConEspada;
import razas.Humano;
import razas.Orco;

public class OrcoTest {

	@Test
	public void quePuedoCrearUnPaladin() {
		Personaje paladin = new Orco(new Paladin(),null,null);

		Assert.assertEquals(120, paladin.getSalud());
		Assert.assertEquals(100, paladin.getEnergia());
		Assert.assertEquals(1, paladin.getNivel());
		Assert.assertEquals(0, paladin.getExp());
		Assert.assertEquals(12, paladin.getAtaque());
		Assert.assertEquals(3, paladin.getDefensa());
		Assert.assertEquals(5, paladin.getInteligencia());
		Assert.assertEquals(0, paladin.getMana());
		Assert.assertEquals("Paladin", paladin.getCasta().nombreCastaElegida());
	}

	@Test
	public void quePuedoEquiparlo() {
		Personaje paladin = new Orco(new Paladin(),null,null);

		paladin = new ConAnillo(paladin);
		// aumenta el ataque 15 pts
		Assert.assertEquals(12 + 15, paladin.getAtaque());

		paladin = new ConArmadura(paladin);
		// aumenta la defensa 13 pts
		Assert.assertEquals(3 + 13, paladin.getDefensa());

		paladin = new ConBastonAghanim(paladin); // aumenta el poder de hechizo
													// 5 pts
		Assert.assertEquals(2, paladin.obtenerPuntosDeHechizos());

		paladin = new ConEspada(paladin);
		// aumenta la defensa 13 pts
		Assert.assertEquals(12 + 15 + 5, paladin.getAtaque());

		paladin = new ConCascoDeLaMuerte(paladin);
		// aumenta la defensa 13 pts
		Assert.assertEquals(3 + 13 + 4, paladin.getDefensa());
	}

	@Test
	public void quePuedeAtacar() {
		Personaje paladin = new Orco(new Paladin(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);
		paladin.atacar(orco);
		Assert.assertEquals(109, orco.getSalud());
		Assert.assertEquals(88, paladin.getEnergia());
	}

	@Test
	public void quePuedeSerAtacado() {
		Personaje paladin = new Orco(new Paladin(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);
		orco.atacar(paladin);
		Assert.assertEquals(109, paladin.getSalud());
		Assert.assertEquals(88, orco.getEnergia());
	}

	@Test
	public void quePuedeHechizar() {
		Personaje paladin = new Orco(new Paladin(),null,null);
		Personaje orco = new Orco(new Paladin(),null,null);

		Assert.assertFalse(paladin.aplicarHechizo("Tormenta divina", orco));
		paladin.setMana(37);
		Assert.assertTrue(paladin.aplicarHechizo("Tormenta divina", orco));
		Assert.assertEquals(81, orco.getSalud());
		Assert.assertEquals(0, paladin.getMana());

		Assert.assertFalse(paladin.aplicarHechizo("Golpe heroico", orco));
		paladin.setMana(37);
		Assert.assertTrue(paladin.aplicarHechizo("Golpe heroico", orco));
		Assert.assertEquals(42, orco.getSalud());
		Assert.assertEquals(0, paladin.getMana());

		Assert.assertFalse(paladin.aplicarHechizo("Sanar", orco));
		paladin.setMana(37);
		Assert.assertTrue(paladin.aplicarHechizo("Sanar", orco));
		Assert.assertEquals(42+12, orco.getSalud());
		Assert.assertEquals(27, paladin.getMana());
	}

}
