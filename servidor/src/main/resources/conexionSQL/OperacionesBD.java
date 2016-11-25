package conexionSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public boolean insertarPersonaje2() {
		String subQuery = "(SELECT ID_USUARIO FROM USUARIO WHERE USUARIO = '" + "MILAGROS" + "')";
		int raza = 0;
		int casta = 0;
		int mapa = 1;
		int nivel = 0;
		int experiencia = 0;
		int vida = 0;
		int energia = 0;
		int ataque = 0;
		int defensa = 0;
		int mana = 0;
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

	public boolean insertarPersonajeDibujable(PersonajeDibujable personajeDibujable) {

		String id = personajeDibujable.getID();
		String img = personajeDibujable.getImg();
		String subQuery = "(SELECT ID_USUARIO FROM USUARIO WHERE USUARIO = '" + id + "')";
		String query = "INSERT INTO PERSONAJEDIBUJABLE VALUES(" + subQuery + ", '" + id + "', '" + img + "')";
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

	public String obtenerPersonajeDibujable(String nombreUsuario) {
		String subQuery = "(SELECT ID_USUARIO FROM USUARIO WHERE USUARIO = '" + nombreUsuario + "')";
		String query = "SELECT * FROM PERSONAJEDIBUJABLE WHERE ID_USUARIO = " + subQuery;
		String resultado;
		try {
			ResultSet resultSet = this.getConsulta().executeQuery(query);
			if (resultSet.next()) {
				resultado = resultSet.getString("USUARIO") + ":" + resultSet.getString("IMAGEN");
				return resultado;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	// Da error de casteo
	public ArrayList<String> obtenerPersonaje(String nombreUsuario) {
		String subQuery = "(SELECT ID_USUARIO FROM USUARIO WHERE USUARIO = '" + nombreUsuario + "')";
		String query = "SELECT * FROM PERSONAJE WHERE ID_USUARIO = " + subQuery;
		ArrayList<String> lista = new ArrayList<>();
		try {
			ResultSet resultSet = this.getConsulta().executeQuery(query);
			if (resultSet.next()) {

				int raza = resultSet.getInt("ID_RAZA");
				lista.add("" + raza);
				String casta = resultSet.getString("ID_CASTA");
				lista.add(casta);
				lista.add("" + 1); // ID_MAPA
				int nivel = resultSet.getInt("NIVEL");
				lista.add("" + nivel);
				int experiencia = resultSet.getInt("EXPERIENCIA");
				lista.add("" + experiencia);
				int vida = resultSet.getInt("VIDA");
				lista.add("" + vida);
				int energia = resultSet.getInt("ENERGIA");
				lista.add("" + energia);
				int ataque = resultSet.getInt("ATAQUE");
				lista.add("" + ataque);
				int defensa = resultSet.getInt("DEFENSA");
				lista.add("" + defensa);
				int mana = resultSet.getInt("MANA");
				lista.add("" + mana);
				int puntos = resultSet.getInt("PUNTOS");
				return lista;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}