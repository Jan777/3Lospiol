package conexionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public boolean verificarCredencial(String nombre, String pas) {
		PreparedStatement pstmt = null;
		String contrasena = "";
		String query = "SELECT CONTRASENA FROM USUARIO WHERE NOMBRE=?";
		Connection conn;
		try {
			conn = this.getConexion();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nombre.toUpperCase());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				contrasena = rs.getString("CONTRASENA");
			}
			return pas.equals(contrasena);
		} catch (Exception e) {
		}
		return false;
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

	public boolean guardarPersonaje(String username, String raza, String casta) {
		PreparedStatement pstmt = null;
		String queryInsert = "insert into personaje (id_usuario,id_raza,id_casta,nivel,experiencia,vida,energia,ataque,defensa,magia,puntos,potencia,destreza,velocidad)"
				+ "values ((select id_usuario from usuario where nombre=?),(select id_raza from raza ra where ra.raza=?),(select id_casta from casta ca where ca.casta=?),0,0,0,0,0,0,0,0,0,0,0)";
		Connection conn;
		try {
			conn = this.getConexion();
			pstmt = conn.prepareStatement(queryInsert);
			pstmt.setString(1, username);
			pstmt.setString(2, raza);
			pstmt.setString(3, casta);

			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			// Loggin.getInstance().error("Error guardarPersonaje" +
			// e.getMessage());
		}
		return false;
	}

	public boolean actualizarPersonaje(int idUsuario, String raza, String casta) {
		PreparedStatement pstmt = null;
		Connection conn;
		String queryUpdate = "update personaje set id_raza=(select id_raza from raza ra where ra.raza=?),id_casta=(select id_casta from casta ca where ca.casta=?) where id_usuario=?";
		try {
			conn = this.getConexion();

			pstmt = conn.prepareStatement(queryUpdate);
			pstmt.setString(1, raza);
			pstmt.setString(2, casta);
			pstmt.setInt(3, idUsuario);

			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			// Loggin.getInstance().error("Error actualizarPersonaje" +
			// e.getMessage());
		}
		return false;
	}
}