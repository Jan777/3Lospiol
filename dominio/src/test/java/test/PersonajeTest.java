package test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import batalla.Batalla;
import castas.Brujo;
import castas.Guerrero;
import castas.Paladin;
import login.Login;
import personaje.Alianza;
import personaje.Jugador;
import personaje.Personaje;
import personajeEquipado.ConAnillo;
import personajeEquipado.ConArmadura;
import personajeEquipado.ConCascoDeLaMuerte;
import personajeEquipado.ConEspada;
import razas.Elfo;
import razas.Humano;
import razas.Orco;

public class PersonajeTest {

	/**
	 * Como jugador quiero poder tener una cuenta con la cual, mediante nombre
	 * de usuario y contrase�a para poder entrar al mundo.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void historiaDeUsuarioN�1() {

		Login login = new Login();
		login.main(null);
	}

	/**
	 * 2_** Como jugador quiero poder crear un personaje pudiendo elegir la
	 * casta que prefiera y ponerle un nombre a mi personaje.
	 */
	@Test
	public void historiaDeUsuarioN�2() {

		Jugador jugador = new Jugador("pepe", "Humano", "Brujo");

		Assert.assertEquals("pepe", jugador.getNombre());
		Assert.assertEquals(100, jugador.getPersonaje().getSalud());
	}

	/**
	 * Como jugador quiero que haya 3 razas para elegir, las cuales seran
	 * **orco,humano u elfo**, y 3 castas que seran **guerrerro, paladin y
	 * brujo**.
	 */
	@Test
	public void historiaDeUsuarioN�3() {

		Humano humano = new Humano(new Guerrero());
		Elfo elfo = new Elfo(new Brujo());
		Orco orco = new Orco(new Paladin());
		Assert.assertEquals(10, elfo.calcularPuntosDeAtaque());
		humano.atacar(elfo);
		Assert.assertEquals(75, elfo.getSalud());
		orco.atacar(elfo);
		Assert.assertEquals(63, elfo.getSalud());

	}

	/**
	 * Como jugador quiero que cada raza tenga una potencia propia univoca que
	 * la hace diferente a las otras dos razas.
	 */
	@Test
	public void historiaDeUsuarioN�4() {
		Humano humano = new Humano(new Guerrero());
		Orco orco = new Orco(new Guerrero());
		Elfo elfo = new Elfo(new Guerrero());

		// El humano tendr� mas defensa que las otras razas
		Assert.assertTrue(humano.obtenerPuntosDeDefensa() > orco.obtenerPuntosDeDefensa()
				&& humano.obtenerPuntosDeDefensa() > elfo.obtenerPuntosDeDefensa());
		// El Orco tendr� mas vida que las otras razas
		Assert.assertTrue(orco.getSalud() > humano.getSalud() && orco.getSalud() > elfo.getSalud());
		// El Orco tendr� mas poder de ataque que las otras razas
		Assert.assertTrue(orco.obtenerPuntosDeAtaque() > humano.obtenerPuntosDeAtaque()
				&& orco.obtenerPuntosDeAtaque() > elfo.obtenerPuntosDeAtaque());
		// El Elfo tendra mas inteligencia que las otras razas
		Assert.assertTrue(elfo.obtenerPuntosDeInteligencia() > humano.obtenerPuntosDeInteligencia()
				&& elfo.obtenerPuntosDeInteligencia() > orco.obtenerPuntosDeInteligencia());
		// El Elfo tendr� mas mana que las otras razas
		Assert.assertTrue(elfo.obtenerPuntosDeMana() > humano.obtenerPuntosDeMana()
				&& elfo.obtenerPuntosDeMana() > orco.obtenerPuntosDeMana());
	}

	/**
	 * Como jugador quiero que al matar a un monstruo o un jugador, este deje
	 * caer el item mas poderoso que tenga.
	 */
	@Test
	public void historiaDeUsuarioN�10() {
		Personaje humano = new Humano(new Guerrero());
		Personaje humanoAtacado = new Humano(new Brujo());
		humanoAtacado = new ConCascoDeLaMuerte(humanoAtacado);
		humanoAtacado = new ConAnillo(humanoAtacado);
		humanoAtacado = new ConEspada(humanoAtacado);
		while (humanoAtacado.getSalud() != 0) {
			humano.atacar(humanoAtacado);
		}
		// Determinar como hacer el item mas poderoso
		humanoAtacado = humanoAtacado.desequipar(ConEspada.class);
		Assert.assertFalse(humanoAtacado.tiene(ConEspada.class));
		Assert.assertTrue(humanoAtacado.tiene(ConAnillo.class));
		Assert.assertTrue(humanoAtacado.tiene(ConCascoDeLaMuerte.class));
	}

	/**
	 * 13_** Como jugador, quiero al ganar una batalla me sea dada una cantidad
	 * de puntos de experiencia para subir de nivel.
	 */
	@Test
	public void historiaDeUsuarioN�13() {
		Humano humano = new Humano(new Guerrero());
		Elfo elfo = new Elfo(new Brujo());
		Orco orco = new Orco(new Paladin());

		humano.atacar(elfo);
		humano.atacar(orco);
		humano.atacar(elfo);
		humano.atacar(orco);
		humano.atacar(elfo);

		Assert.assertEquals(2, humano.getNivel());

	}

	@Ignore
	public void queAgregaItems() {
		Personaje personaje = new Humano(new Guerrero());
		Assert.assertEquals(10, personaje.obtenerPuntosDeDefensa());
		personaje = new ConArmadura(personaje);
		Assert.assertEquals(23, personaje.obtenerPuntosDeDefensa());
		personaje = new ConCascoDeLaMuerte(personaje);
		Assert.assertEquals(27, personaje.obtenerPuntosDeDefensa());
	}

	// TODO: Por alguna raz�n, si se desequipa un item que se insert� primero
	// y despu�s se pregunta si el item insertado despu�s sigue, da error.
	// Prob� desequipando primero la espada y despu�s el resto, o
	// el anillo y despu�s la armadura.
	// Cuando es al rev�s, si funciona.
	@Test
	public void quePuedoQuitarUnItem() {
		Personaje personaje = new Humano(new Guerrero());
		personaje = new ConEspada(personaje);
		personaje = new ConAnillo(personaje);
		personaje = new ConArmadura(personaje);

		Assert.assertTrue(personaje.tiene(ConAnillo.class));
		personaje = personaje.desequipar(ConAnillo.class);
		Assert.assertFalse(personaje.tiene(ConAnillo.class));

		Assert.assertTrue(personaje.tiene(ConEspada.class));
		personaje = personaje.desequipar(ConEspada.class);
		Assert.assertFalse(personaje.tiene(ConEspada.class));
	}

	@Test
	public void queFuncionaLaAlianza() {
		Alianza FPV = new Alianza();
		Alianza Cambiemos = new Alianza();
		Personaje Macri = new Elfo(new Guerrero());
		Personaje Carrio = new Elfo(new Brujo());
		Personaje Cristina = new Humano(new Guerrero());
		Personaje Scioli = new Humano(new Paladin());

		FPV.agregarAliado(Cristina);
		FPV.agregarAliado(Scioli);
		Cambiemos.agregarAliado(Macri);
		Cambiemos.agregarAliado(Carrio);

		Batalla batalla = new Batalla(FPV, Cambiemos);

		batalla.batalla();

	}

}
