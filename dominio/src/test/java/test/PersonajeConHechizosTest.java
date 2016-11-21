package test;

import org.junit.Assert;
import org.junit.Test;

import castas.Brujo;
import castas.Guerrero;
import castas.Paladin;
import personaje.Personaje;
import personajeEquipado.ConEspada;
import razas.Humano;
import razas.Orco;

public class PersonajeConHechizosTest {
	
	@Test
	public void CrearPersonajeConCasta(){
		Personaje paladin= new Humano(new Paladin(), null, null);
		Personaje guerrero=new Orco(new Guerrero(), null, null); 
		guerrero.atacar(paladin);
		Assert.assertEquals(91,paladin.getSalud());
	}
	
	@Test
	public void probarHechizandoOponentes(){
		Personaje brujo= new Humano(new Brujo(), null, null);
		Personaje guerreroAtacado=new Orco(new Guerrero(), null, null); 
		//probando con mana menor al requerido para lanzar el hechizo
		Assert.assertFalse("No tiene mana suficiente", brujo.aplicarHechizo("Latigaso mortal", guerreroAtacado));
		brujo.setMana(50);
		//probando con mana suficiente para lanzar hechizo
		Assert.assertTrue(brujo.aplicarHechizo("Latigaso mortal", guerreroAtacado));
		//ver mana actualizado
		Assert.assertEquals(50-30,brujo.obtenerPuntosDeMana());
	}
	@Test
	public void equiparItemsAlPersonaje(){
		Personaje orco=new Orco(new Guerrero(), null, null);
		orco = new ConEspada(orco);
		Assert.assertEquals(12+5, orco.obtenerPuntosDeAtaque());
		
	}
}
