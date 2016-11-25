package conexionSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import castas.Brujo;
import castas.Casta;
import castas.Guerrero;
import castas.Paladin;
import personaje.Personaje;
import personaje.PersonajeDibujable;
import razas.Elfo;
import razas.Humano;
import razas.Orco;

public class OperacionesBD extends ConexionSQL {

	public OperacionesBD() {
	}

	public boolean insertarUsuario(String usuario, String contraseña) {
		String query = "INSERT INTO USUARIO VALUES(" + null + ", '" + usuario + "', '" + contraseña + "')";
		try {
			int resultSet = this.getConsulta().executeUpdate(query);
			if (resultSet == 0)
				return false;
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean existeUsuario(String nombreUsuario) {
		String query = "SELECT * FROM USUARIO WHERE USUARIO = '" + nombreUsuario + "'";
		String nombre = "";
		try {
			ResultSet resultSet = this.getConsulta().executeQuery(query);
			while (resultSet.next()) {
				nombre = resultSet.getString("USUARIO");
			}
			if (nombre.equals(nombreUsuario.toUpperCase()))
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean validarCredenciales(String usuario, String password) {
		String contrasena = "";
		String query = "SELECT CONTRASENA FROM USUARIO WHERE USUARIO = '" + usuario + "'";
		try {

			ResultSet resultSet = this.getConsulta().executeQuery(query);
			while (resultSet.next()) {
				contrasena = resultSet.getString("CONTRASENA");
			}
			return password.equals(contrasena);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insertarPersonaje(Personaje personaje, PersonajeDibujable personajeDibujable) {
		String subQuery = "(SELECT ID_USUARIO FROM USUARIO WHERE USUARIO = '" + personaje.getID() + "')";
		int raza = personaje.getIdRaza();
		int casta = personaje.getCasta().getIdCasta();
		int mapa = 1;
		int nivel = personaje.getNivel();
		int experiencia = personaje.getExp();
		int vida = 0;
		int energia = personaje.getEnergia();
		int ataque = personaje.getAtaque();
		int defensa = personaje.getDefensa();
		int mana = personaje.getMana();
		int puntos = 0;
		String values = +raza + ", " + casta + ", " + mapa + ", " + nivel + ", ";
		values += experiencia + ", " + vida + ", " + energia + ", " + ataque + ", ";
		values += defensa + ", " + mana + ", " + puntos;
		String query = "INSERT INTO PERSONAJE VALUES(" + null + ", " + subQuery + ", " + values + ")";

		try {
			int resultSet = this.getConsulta().executeUpdate(query);
			if (resultSet == 0)
				return false;
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean consultarPersonaje(String nombreUsuario) {
		String subQuery = "(SELECT ID_USUARIO FROM USUARIO WHERE USUARIO = '" + nombreUsuario + "')";
		String query = "SELECT * FROM PERSONAJE WHERE ID_USUARIO = " + subQuery;
		try {
			ResultSet resultSet = this.getConsulta().executeQuery(query);
			if (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public Personaje obtenerPesonaje(String nombreUsuario) {
		String subQuery = "(SELECT ID_USUARIO FROM USUARIO WHERE USUARIO = '" + nombreUsuario + "')";
		String query = "SELECT * FROM PERSONAJE WHERE ID_USUARIO = " + subQuery;
		try {
			ResultSet resultSet = this.getConsulta().executeQuery(query);
			if (resultSet.next()) {
				int raza = resultSet.getInt("ID_RAZA");
				String casta = resultSet.getString("ID_CASTA");
				int mapa = 1;
				int nivel = resultSet.getInt("NIVEL");
				int experiencia = resultSet.getInt("EXPERIENCIA");
				int vida = resultSet.getInt("VIDA");
				int energia = resultSet.getInt("ENERGIA");
				int ataque = resultSet.getInt("ATAQUE");
				int defensa = resultSet.getInt("DEFENSA");
				int mana = resultSet.getInt("MANA");
				int puntos = resultSet.getInt("PUNTOS");
				return obtenerPersonaje(nombreUsuario, casta, raza,vida,ataque,defensa,energia,experiencia,nivel,mana,raza);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	private Personaje obtenerPersonaje(String nombrePersonaje, String casta, int raza, int salud, int ataque, int defensa, int energia, int experiencia, int nivel, int mana, int idRaza) {
		switch (raza) {
		case 1:
			switch (casta) {
			case "paladin":
				return new Humano(new Paladin(), nombrePersonaje, "humanoP",ataque,salud,defensa,energia,experiencia,nivel,mana,idRaza);
				
			// return new PersonajeDibujable(nombrePersonaje, "humanoP");

			case "guerrero":
				return new Humano(new Guerrero(), nombrePersonaje, "humanoG");
			// return new PersonajeDibujable(nombrePersonaje, "humanoG");

			case "brujo":
				return new Humano(new Brujo(), nombrePersonaje, "humanoB");
			// dibujoPersonaje = new PersonajeDibujable(nombrePersonaje,
			// "humanoB");

			default:
				break;
			}
			break;
		case 3:
			switch (casta) {
			case "paladin":
				return new Orco(new Paladin(), nombrePersonaje, "orcoP");
			// dibujoPersonaje = new PersonajeDibujable(nombrePersonaje,
			// "orcoP");

			case "guerrero":
				return new Orco(new Guerrero(), nombrePersonaje, "orcoG");
			// dibujoPersonaje = new PersonajeDibujable(nombrePersonaje,
			// "orcoG");

			default:
				break;
			}
			break;
		case 2:
			switch (casta) {
			case "paladin":
				return new Elfo(new Paladin(), nombrePersonaje, "elfoP");
			// dibujoPersonaje = new PersonajeDibujable(nombrePersonaje,
			// "elfoP");

			case "guerrero":
				return new Elfo(new Guerrero(), nombrePersonaje, "elfoG");
			// dibujoPersonaje = new PersonajeDibujable(nombrePersonaje,
			// "elfoG");

			case "brujo":
				return new Elfo(new Brujo(), nombrePersonaje, "elfoB");
			// dibujoPersonaje = new PersonajeDibujable(nombrePersonaje,
			// "elfoB");

			default:
				break;
			}
			break;
		default:
			break;
		}
		return new Humano(new Paladin(), nombrePersonaje, "humanoP");
	}
}