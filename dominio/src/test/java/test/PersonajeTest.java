package test;

import org.junit.Assert;
import org.junit.Test;

import batalla.Batalla;
import castas.Brujo;
import castas.Guerrero;
import castas.Paladin;
import habDeGuerrero.AumentarAtaque;
import habDeGuerrero.Desgarrar;
import habDeGuerrero.Ejecutar;
import hechizosDeBrujo.BolaDeLaOscuridad;
import hechizosDeBrujo.DisminuirAtaque;
import hechizosDeBrujo.LatigazoMortal;
import hechizosYHabPaladin.GolpeHeroico;
import hechizosYHabPaladin.Sanar;
import hechizosYHabPaladin.TormentaDivina;
import mapa.BuscarImagen;
//import login.Login;
import personaje.Alianza;
import personaje.Personaje;
import personaje.PersonajeDibujable;
import personajeEquipado.ConAnillo;
import personajeEquipado.ConArmadura;
import personajeEquipado.ConCascoDeLaMuerte;
import personajeEquipado.ConEspada;
import personajeEquipado.PersonajeEquipado;
import razas.Elfo;
import razas.Humano;
import razas.Orco;

public class PersonajeTest {

	/**
	 * Como jugador quiero poder tener una cuenta con la cual, mediante nombre
	 * de usuario y contraseña para poder entrar al mundo.
	 * 
	 * @throws InterruptedException
	 */
	// @Test
	// public void historiaDeUsuarioNº1() {
	//
	// Login login = new Login();
	// login.main(null);
	// }

	/**
	 * 2_** Como jugador quiero poder crear un personaje pudiendo elegir la
	 * casta que prefiera y ponerle un nombre a mi personaje.
	 */
	// @Test
	// public void historiaDeUsuarioNº2() {
	//
	// Jugador jugador = new Jugador("pepe", "Humano", "Brujo");
	// Assert.assertEquals("pepe", jugador.getNombre());
	// Assert.assertEquals(100, jugador.getPersonaje().obtenerPuntosDeSalud());
	// }

	/**
	 * Como jugador quiero que haya 3 razas para elegir, las cuales seran
	 * **orco,humano u elfo**, y 3 castas que seran **guerrerro, paladin y
	 * brujo**.
	 */
	@Test
	public void historiaDeUsuarioNº3() {

		Humano humano = new Humano(new Guerrero(), null, null);
		Elfo elfo = new Elfo(new Brujo(), null, null);
		Orco orco = new Orco(new Paladin(), null, null);
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
	public void historiaDeUsuarioNº4() {
		Humano humano = new Humano(new Guerrero(), null, null);
		Orco orco = new Orco(new Guerrero(), null, null);
		Elfo elfo = new Elfo(new Guerrero(), null, null);

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

	/**
	 * Como jugador quiero que al matar a un monstruo o un jugador, este deje
	 * caer el item mas poderoso que tenga.
	 */
	@Test
	public void historiaDeUsuarioNº10() {
		Personaje humano = new Humano(new Guerrero(), null, null);
		Personaje humanoAtacado = new Humano(new Brujo(), null, null);
		humanoAtacado = new ConCascoDeLaMuerte(humanoAtacado);
		humanoAtacado = new ConEspada(humanoAtacado);
		humanoAtacado = new ConAnillo(humanoAtacado);
		while (humanoAtacado.getSalud() != 0) {
			humano.atacar(humanoAtacado);
		}
		String nombreDelItem[] = new String[1];
		humanoAtacado = humanoAtacado
				.desequiparItem((PersonajeEquipado) humanoAtacado.desequiparItemConMayorPrioridad(), nombreDelItem);
		Assert.assertEquals("ConEspada", nombreDelItem[0]);
		Assert.assertEquals(0, humano.getCantidadDeItems());
		Assert.assertEquals(10, humano.obtenerPuntosDeAtaque());
		Assert.assertEquals(2, humanoAtacado.getCantidadDeItems());
		Assert.assertEquals(14, humanoAtacado.obtenerPuntosDeDefensa());
		Assert.assertEquals(25, humanoAtacado.obtenerPuntosDeAtaque());
	}

	/**
	 * 13_** Como jugador, quiero al ganar una batalla me sea dada una cantidad
	 * de puntos de experiencia para subir de nivel.
	 */
	@Test
	public void historiaDeUsuarioNº13() {
		Humano humano = new Humano(new Guerrero(), null, null);
		Elfo elfo = new Elfo(new Brujo(), null, null);
		Orco orco = new Orco(new Paladin(), null, null);

		while (humano.estaVivo()) {
			orco.atacar(humano);
			orco.serEnergizado();
		}
		orco.atacar(humano);
		while (elfo.estaVivo()) {
			orco.atacar(elfo);
			orco.serEnergizado();
		}
		orco.atacar(elfo);
		// el orco mato a 2 jugadores y sube un nivel
		Assert.assertEquals(2, orco.getNivel());

	}

	@Test
	public void queFuncionaLaAlianza() {
		Alianza FPV = new Alianza();
		Alianza Cambiemos = new Alianza();
		Personaje Macri = new Elfo(new Guerrero(), null, null);
		Personaje Carrio = new Elfo(new Brujo(), null, null);
		Personaje Cristina = new Humano(new Guerrero(), null, null);
		Personaje Scioli = new Humano(new Paladin(), null, null);

		FPV.agregarAliado(Cristina);
		FPV.agregarAliado(Scioli);
		Cambiemos.agregarAliado(Macri);
		Cambiemos.agregarAliado(Carrio);

		Batalla batalla = new Batalla(FPV, Cambiemos);

		batalla.batalla();

	}

	@Test
	public void quePuedoAgregarYQuitarUnItem() {

		Personaje personaje = new Humano(new Guerrero(), null, null);
		personaje = new ConEspada(personaje);
		Assert.assertEquals(100, personaje.obtenerPuntosDeSalud());
		Assert.assertEquals(15, personaje.obtenerPuntosDeAtaque());
		Assert.assertEquals(10, personaje.obtenerPuntosDeDefensa());

		personaje = new ConCascoDeLaMuerte(personaje);
		personaje = new ConArmadura(personaje);

		Assert.assertEquals(15, personaje.obtenerPuntosDeAtaque());
		Assert.assertEquals(27, personaje.obtenerPuntosDeDefensa());
		String[] nombreDelItem = new String[1];
		personaje = personaje.desequiparItem((PersonajeEquipado) personaje.desequiparItemConMayorPrioridad(),
				nombreDelItem);
		Assert.assertEquals(10, personaje.obtenerPuntosDeAtaque());
		Assert.assertEquals(27, personaje.obtenerPuntosDeDefensa());
		Assert.assertEquals(2, personaje.getCantidadDeItems());
		Assert.assertEquals("ConEspada", nombreDelItem[0]);
		personaje = new ConEspada(personaje);
		Assert.assertEquals(15, personaje.obtenerPuntosDeAtaque());
		Assert.assertEquals(27, personaje.obtenerPuntosDeDefensa());
		Assert.assertEquals(3, personaje.getCantidadDeItems());
		//

	}

	@Test
	public void queBuscaLasImagenes() {
		BuscarImagen buscar = new BuscarImagen();
		String imagen = "orcoP.png";
		Assert.assertEquals(32, buscar.alto(imagen));
		Assert.assertEquals(32, buscar.ancho(imagen));
		String mapa = "stage2";
		Assert.assertEquals(128, buscar.altoMapa(mapa));
		Assert.assertEquals(96, buscar.anchoMapa(mapa));

	}

	@Test
	public void queCaminaElPersonaje() {
		PersonajeDibujable personaje = new PersonajeDibujable("Nombre", "orcoP");
		personaje.caminar();
	}

	@Test
	public void quePuedoCrearHabilidades() {
		AumentarAtaque a = new AumentarAtaque();
		Desgarrar d = new Desgarrar();
		Ejecutar e = new Ejecutar();

		BolaDeLaOscuridad b = new BolaDeLaOscuridad();
		DisminuirAtaque di = new DisminuirAtaque();
		LatigazoMortal l = new LatigazoMortal();

		GolpeHeroico g = new GolpeHeroico();
		Sanar s = new Sanar();
		TormentaDivina t = new TormentaDivina();
	}

}
