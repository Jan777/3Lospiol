package conexionSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import personaje.Personaje;
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
			e.printStackTrace();
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
		}
		return false;
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
		String values = + raza + ", " + casta + ", " + mapa + ", " + nivel + ", ";
		values += experiencia + ", " + vida + ", " + energia + ", " + ataque + ", ";
		values += defensa + ", " + mana + ", " + puntos;
		String query = "INSERT INTO PERSONAJE VALUES(" + null + ", " + subQuery + ", " + values + ")";
		
		try {
			int resultSet = this.getConsulta().executeUpdate(query);
			if (resultSet == 0)
				return false;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}