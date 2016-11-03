package personajeEquipado;

import personaje.Personaje;

public class PersonajeEquipado extends Personaje {
	Personaje personajeDecorado;
	protected String nombreDelItem;
	protected int prioridad;

	public PersonajeEquipado(Personaje personajeDecorado) {
		this.personajeDecorado = personajeDecorado;
	}

	@Override
	public boolean puedeAtacar() {
		return this.personajeDecorado.puedeAtacar();
	}

	@Override
	public int obtenerPuntosDeAtaque() {
		return this.personajeDecorado.obtenerPuntosDeAtaque();
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return this.personajeDecorado.calcularPuntosDeAtaque();
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return this.personajeDecorado.obtenerPuntosDeDefensa();
	}

	@Override
	public int obtenerPuntosDeHechizos() {
		return this.personajeDecorado.obtenerPuntosDeHechizos();
	}

	@Override
	public void serCurado() {
		this.personajeDecorado.serCurado();
	}

	@Override
	public boolean aplicarHechizo(String hechizo, Personaje afectado) {
		return this.personajeDecorado.aplicarHechizo(hechizo, afectado);
	}

	@Override
	public int calcularPuntosDeHechizos() {
		return this.personajeDecorado.calcularPuntosDeHechizos();
	}
	
	@Override
	public int obtenerPuntosDeSalud() {
		return this.personajeDecorado.obtenerPuntosDeSalud();
	}

	// @Override
	// public boolean tiene(Class decorado) {
	// return this.getClass() == decorado ||
	// this.personajeDecorado.tiene(decorado);
	// }
	//
	// @Override
	// public Personaje desequipar(Class decorado) {
	// return this.desequiparEste(decorado);
	// }
	//
	// private Personaje desequiparEste(Class decorado) {
	// if (this.getClass() == decorado)
	// return this.personajeDecorado;
	// return this.personajeDecorado.desequipar(decorado);
	// }

	@Override
	public int getPrioridad() {
		return this.prioridad;
	}

	@Override
	public Personaje getPersonajeDecorado() {
		return this.personajeDecorado;
	}

	@Override
	public int getCantidadDeItems() {
		return personajeDecorado.getCantidadDeItems() + 1;
	}

	public String getNombreItem() {
		return nombreDelItem;
	}

	public void setPersonajeDecorado(Personaje personajeDecorado) {
		this.personajeDecorado = personajeDecorado;
	}

	@Override
	public Personaje desequiparItem(PersonajeEquipado personajeEquipado, String[] nombreDelItem) {
		Personaje itemSiguiente = this;
		Personaje ItemAnterior = this;
		Personaje ItemUltimo = this;
		int cantidadDeItems = this.getCantidadDeItems();

		for (int indexItems = 0; indexItems < cantidadDeItems; indexItems++) {
			if (indexItems == 0 && itemSiguiente.getNombreItem() == personajeEquipado.getNombreItem()) {
				nombreDelItem[0] = itemSiguiente.getNombreItem();
				return (ItemUltimo = itemSiguiente.getPersonajeDecorado());
			}
			if (itemSiguiente.getNombreItem() != personajeEquipado.getNombreItem()) {
				ItemAnterior = itemSiguiente;
				itemSiguiente = itemSiguiente.getPersonajeDecorado();
			} else {
				ItemAnterior.setPersonajeDecorado(itemSiguiente.getPersonajeDecorado());
				nombreDelItem[0] = itemSiguiente.getNombreItem();
				return ItemUltimo;
			}
		}

		return null; // Retorno null porque es el caso donde no hay items para sacar
	}

	@Override
	public Personaje desequiparItemConMayorPrioridad() {

		Personaje itemConMasPrioridad = this;
		Personaje itemSiguiente = this.getPersonajeDecorado();
		if (this.getCantidadDeItems() > 1) {
			for (int intexItem = 0; intexItem < this.getCantidadDeItems(); intexItem++) {
				if (itemConMasPrioridad.getPrioridad() > itemSiguiente.getPrioridad()) {
					if (itemSiguiente.getPersonajeDecorado().getNombreItem() != null)
						itemSiguiente = itemSiguiente.getPersonajeDecorado();
				} else {
					itemConMasPrioridad = itemSiguiente;
					if (itemSiguiente.getPersonajeDecorado().getNombreItem() != null)
						itemSiguiente = (PersonajeEquipado) itemSiguiente.getPersonajeDecorado();
				}
			}
			return itemConMasPrioridad;
		}
		return this;
	}

	@Override
	public void despuesDeAtacar() {
		this.personajeDecorado.despuesDeAtacar();
	}

	
}
