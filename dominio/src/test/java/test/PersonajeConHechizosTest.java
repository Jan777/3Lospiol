package test;

import org.junit.Assert;
import org.junit.Test;

import castas.Guerrero;
import castas.Paladin;
import personaje.Personaje;
import personajeEquipado.ConEspada;
import razas.Humano;
import razas.Orco;

public class PersonajeConHechizosTest {
	
	@Test
	public void CrearPersonajeConCasta(){
		Personaje paladin= new Humano(new Paladin());
		Personaje guerreroAtacado=new Orco(new Guerrero()); 
		paladin.atacar(guerreroAtacado);
		Assert.assertEquals(115,guerreroAtacado.getSalud());
	}
	
	@Test
	public void probarHechizandoOponentes(){
		Personaje paladin= new Humano(new Paladin());
		Personaje guerreroAtacado=new Orco(new Guerrero()); 
		//probando con mana menor al requerido para lanzar el hechizo
		Assert.assertFalse("No tiene mana suficiente", paladin.aplicarHechizo("Tormenta divina", guerreroAtacado));
		paladin.setMana(50);
		//probando con mana suficiente para lanzar hechizo
		Assert.assertTrue(paladin.aplicarHechizo("Tormenta divina", guerreroAtacado));
		//ver mana actualizado
		Assert.assertEquals(50-37,paladin.obtenerPuntosDeMana());
	}
	// no funciona no se porque 
	@Test
	public void equiparItemsAlPersonaje(){
		Personaje orco=new Orco(new Guerrero());
		orco = new ConEspada(orco);
		Assert.assertEquals(12+5, orco.obtenerPuntosDeAtaque());
		
	}
}
