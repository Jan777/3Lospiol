package test;

import org.junit.*;

import castas.*;
import personaje.Personaje;
import personajeEquipado.ConAnillo;
import personajeEquipado.ConArmadura;
import personajeEquipado.ConCascoDeLaMuerte;
import personajeEquipado.ConEspada;
import razas.*;

public class PersonajeTest {

	/**
	 * Como jugador quiero que haya 3 razas para elegir, las cuales seran
	 * **orco,humano u elfo**, y 3 castas que seran **guerrerro, paladin y
	 * brujo**.
	 */
	@Test
	public void historiaDeUsuarioNº3() {

		Humano humano = new Humano(new Guerrero());
		Elfo elfo = new Elfo(new Brujo());
		Orco orco = new Orco(new Paladin());
		Assert.assertEquals(10, elfo.calcularPuntosDeAtaque());
		humano.atacar(elfo);
		Assert.assertEquals(79, elfo.getSalud());
		orco.atacar(elfo);
		Assert.assertEquals(71, elfo.getSalud());

	}

	/**
	 * Como jugador quiero que cada raza tenga una potencia propia univoca que
	 * la hace diferente a las otras dos razas.
	 */
	@Test
	public void historiaDeUsuarioNº4() {
		Humano humano = new Humano(new Guerrero());
		Orco orco = new Orco(new Guerrero());
		Elfo elfo = new Elfo(new Guerrero());

		// El humano tendrá mas defensa que las otras razas
		Assert.assertTrue(humano.obtenerPuntosDeDefensa() > orco.obtenerPuntosDeDefensa()
				&& humano.obtenerPuntosDeDefensa() > elfo.obtenerPuntosDeDefensa());
		// El Orco tendrá mas vida que las otras razas
		Assert.assertTrue(orco.getSalud() > humano.getSalud() && orco.getSalud() > elfo.getSalud());
		// El Orco tendrá mas poder de ataque que las otras razas
		Assert.assertTrue(orco.obtenerPuntosDeAtaque() > humano.obtenerPuntosDeAtaque()
				&& orco.obtenerPuntosDeAtaque() > elfo.obtenerPuntosDeAtaque());
		// El Elfo tendra mas inteligencia que las otras razas
		Assert.assertTrue(elfo.obtenerPuntosDeInteligencia() > humano.obtenerPuntosDeInteligencia()
				&& elfo.obtenerPuntosDeInteligencia() > orco.obtenerPuntosDeInteligencia());
		// El Elfo tendrá mas mana que las otras razas
		Assert.assertTrue(elfo.obtenerPuntosDeMana() > humano.obtenerPuntosDeMana()
				&& elfo.obtenerPuntosDeMana() > orco.obtenerPuntosDeMana());
	}

	@Test
	public void queAgregaItems() {
		Personaje personaje = new Humano(new Guerrero());
		Assert.assertEquals(10, personaje.obtenerPuntosDeDefensa());
		personaje = new ConArmadura(personaje);
		Assert.assertEquals(23, personaje.obtenerPuntosDeDefensa());
		personaje = new ConCascoDeLaMuerte(personaje);
		Assert.assertEquals(27, personaje.obtenerPuntosDeDefensa());
	}

	@Test
	public void quePuedoQuitarUnItem() {
		Personaje personaje = new Humano(new Guerrero());
		personaje = new ConEspada(personaje);
		personaje = new ConAnillo(personaje);
		personaje = new ConArmadura(personaje);

		Assert.assertTrue(personaje.tiene(ConArmadura.class));
		personaje = personaje.desequipar(ConArmadura.class);
		Assert.assertFalse(personaje.tiene(ConArmadura.class));

		Assert.assertTrue(personaje.tiene(ConEspada.class));
		personaje = personaje.desequipar(ConEspada.class);
		Assert.assertFalse(personaje.tiene(ConEspada.class));
	}

}
