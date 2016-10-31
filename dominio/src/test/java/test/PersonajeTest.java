package test;

import org.junit.*;

import batalla.Batalla;
import castas.*;
import personaje.Alianza;
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
	public void queFuncionaLaAlianza(){
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
		
		Batalla batalla = new Batalla(FPV,Cambiemos);
	
		batalla.batalla();
		
		
	}

}
