package conexionSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import personaje.PersonajeDibujable;

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

	public boolean insertarPersonaje(ArrayList<String> personaje) {
		String subQuery = "(SELECT ID_USUARIO FROM USUARIO WHERE USUARIO = '" + personaje.get(0) + "')";
		int raza = Integer.parseInt(personaje.get(1));
		int casta = Integer.parseInt(personaje.get(2));
		int mapa = 1;
		int nivel = Integer.parseInt(personaje.get(3));
		int experiencia = Integer.parseInt(personaje.get(4));
		int vida = Integer.parseInt(personaje.get(5));
		int energia = Integer.parseInt(personaje.get(6));
		int ataque = Integer.parseInt(personaje.get(7));
		int defensa = Integer.parseInt(personaje.get(8));
		int mana = Integer.parseInt(personaje.get(9));
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
				return lista;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}