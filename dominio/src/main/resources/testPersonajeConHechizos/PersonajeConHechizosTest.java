package testPersonajeConHechizos;

import org.junit.Assert;
import org.junit.Test;

import personaje.Personaje;
import personajeEquipado.ConBastonAghanim;
import personajeEquipado.ConEspada;
import personajeEquipado.PersonajeEquipado;
import razas.Humano;
import razas.Orco;
import razas.Elfo;
import castas.Brujo;
import castas.Guerrero;
import castas.Paladin;

public class PersonajeConHechizosTest {
	
	/*@Test
	public void CrearPersonajeConCasta(){
		Personaje paladin= new Humano(new Paladin());
		Personaje guerreroAtacado=new Orco(new Guerrero()); 
		paladin.atacar(guerreroAtacado);
		Assert.assertEquals(110,guerreroAtacado.getSalud());
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
		Assert.assertEquals(50-37,paladin.getMana());
	}*/
	
	@Test
	public void equiparItemsAlPersonaje(){
		Personaje orco=new Orco(new Guerrero());
		PersonajeEquipado d2o= new ConEspada(orco);
		Assert.assertEquals(10+5, orco.obtenerPuntosDeAtaque());
		
	}
}
