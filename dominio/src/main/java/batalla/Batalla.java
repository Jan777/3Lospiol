package batalla;

import java.util.Iterator;

import personaje.Alianza;
import personaje.Personaje;

public class Batalla {

	private Personaje retador;
	private Personaje contrincante;
	private Alianza alianzaRetadora;
	private Alianza alianzaContrincante;

	public Batalla(Alianza alianzaRetadora, Alianza alianzaContrincante) {
		this.alianzaRetadora = alianzaRetadora;
		this.alianzaContrincante = alianzaContrincante;
	}

	public void agregarAlBatallonRetador(Personaje personaje) {
		this.alianzaRetadora.agregarAliado(personaje);
	}

	public void agregarAlBatallonContrincante(Personaje personaje) {
		this.alianzaContrincante.agregarAliado(personaje);
	}

	public void batalla() {
		Iterator<Personaje> iteratorRetador;
		Iterator<Personaje> iteratorContrincante;

		while (!this.alianzaRetadora.getAliados().isEmpty() && !this.alianzaContrincante.getAliados().isEmpty()) {
			iteratorRetador = this.alianzaRetadora.getAliados().iterator();
			iteratorContrincante = this.alianzaContrincante.getAliados().iterator();

			while (iteratorRetador.hasNext() && iteratorContrincante.hasNext()) {
				this.retador = iteratorRetador.next();
				this.contrincante = iteratorContrincante.next();
				this.retador.atacar(this.contrincante);

				if (this.contrincante.getSalud() == 0) {
					// Eliminar el item mas poderoso y darselo a quien lo mato
					iteratorContrincante.remove();

				} else {
					this.contrincante.atacar(this.retador);
					if (this.retador.getSalud() == 0) {
						// Eliminar el item mas poderoso y darselo a quien lo
						// mato
						iteratorContrincante.remove();

					}
				}
			}

		}

	}

}
