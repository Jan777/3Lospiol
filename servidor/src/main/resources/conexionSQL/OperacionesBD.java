package conexionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OperacionesBD {

	private Connection conn;

	public OperacionesBD() {
		try {
			conn = ConexionSQL.getConnection();
		} catch (Exception e) {
			// Loggin.getInstance().error("Error estableciendo conexion a la
			// base " + e.getMessage());
		}
	}

	public int agregarUsuario(String nombre, String contrasena) {
		PreparedStatement pstmt = null;
		try {
			conn = ConexionSQL.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO USUARIO(NOMBRE,CONTRASENA) VALUES(?,?)");
			pstmt.setString(1, nombre.toUpperCase());
			pstmt.setString(2, contrasena);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// Loggin.getInstance().error("Log error al agregar usuario " +
			// e.getMessage());
			return 0;
		}
		return 1;
	}

	public void actualizarUsuario(int idUsuario, String nombre, String contrasena) {
		PreparedStatement pstmt = null;
		try {
			conn = ConexionSQL.getConnection();
			pstmt = conn.prepareStatement("UPDATE USUARIO SET NOMBRE=?,CONTRASENA=? WHERE ID_USUARIO=?");
			pstmt.setString(1, nombre.toUpperCase());
			pstmt.setString(2, contrasena);
			pstmt.setInt(3, idUsuario);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// Loggin.getInstance().error("Error actualizarUsuario: " +
			// e.getMessage());
		}
	}

	// public Usuario obtenerUsuario(int idUsuario) {
	// Usuario u = null;
	// PreparedStatement pstmt = null;
	// String nombre = "";
	// String contrasena = "";
	// String query = "SELECT NOMBRE,CONTRASENA FROM USUARIO WHERE
	// ID_USUARIO=?";
	// try {
	// conn = ConexionSQL.getConnection();
	// pstmt = conn.prepareStatement(query);
	// pstmt.setInt(1, idUsuario);
	//
	// ResultSet rs = pstmt.executeQuery();
	// while (rs.next()) {
	// nombre = rs.getString("NOMBRE");
	// contrasena = rs.getString("CONTRASENA");
	// }
	// u = new Usuario(nombre, contrasena);
	// } catch (Exception e) {
	// //Loggin.getInstance().error("Error obtenerUsuario " + e.getMessage());
	// }
	// return u;
	// }

	public boolean verificarCredencia(String nombre, String pas) {
		PreparedStatement pstmt = null;
		String contrasena = "";
		String query = "SELECT CONTRASENA FROM USUARIO WHERE NOMBRE=?";
		try {
			conn = ConexionSQL.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nombre.toUpperCase());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				contrasena = rs.getString("CONTRASENA");
			}
			return pas.equals(contrasena);
		} catch (Exception e) {
			// Loggin.getInstance().error("Error al agregar usuario" +
			// e.getMessage());
		}
		return false;
	}

	public boolean existeUsuario(String userName) {
		PreparedStatement pstmt = null;
		String nombre = "";
		String query = "SELECT NOMBRE FROM USUARIO WHERE NOMBRE=?";
		try {
			conn = ConexionSQL.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName.toUpperCase());

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				nombre = rs.getString("NOMBRE");
			}
			if (nombre.equals(userName.toUpperCase()))
				return true;
		} catch (Exception e) {
			// Loggin.getInstance().error("Error existeUsuario " +
			// e.getMessage());
		}
		return false;
	}

	// public Mensaje getPersonaje(String username) {
	// PreparedStatement pstmt = null;
	// String query = "SELECT NOMBRE,R.RAZA AS RAZA,C.CASTA AS CASTA,ID_USUARIO
	// AS ID FROM (SELECT U.NOMBRE,U.ID_USUARIO,P.ID_RAZA,P.ID_CASTA "
	// + "FROM USUARIO U INNER JOIN PERSONAJE P ON "
	// + "U.ID_USUARIO=P.ID_USUARIO WHERE NOMBRE=?) AS PERSON INNER JOIN RAZA R
	// ON PERSON.ID_RAZA=R.ID_RAZA INNER JOIN CASTA C ON
	// C.ID_CASTA=PERSON.ID_CASTA";
	// Mensaje personaje = new Mensaje();
	// try {
	// conn = ConexionSQL.getConnection();
	// pstmt = conn.prepareStatement(query);
	// pstmt.setString(1, username.toUpperCase());
	//
	// ResultSet rs = pstmt.executeQuery();
	// while (rs.next()) {
	// personaje.setUsername(rs.getString("NOMBRE"));
	// personaje.setRaza(rs.getString("RAZA"));
	// personaje.setCasta(rs.getString("CASTA"));
	// personaje.setIdUsuario(rs.getInt("ID"));
	// }
	// } catch (Exception e) {
	// //Loggin.getInstance().error("Error getPersonaje" + e.getMessage());
	// }
	// return personaje;
	// }

	public boolean guardarPersonaje(String username, String raza, String casta) {
		PreparedStatement pstmt = null;
		String queryInsert = "insert into personaje (id_usuario,id_raza,id_casta,nivel,experiencia,vida,energia,ataque,defensa,magia,puntos,potencia,destreza,velocidad)"
				+ "values ((select id_usuario from usuario where nombre=?),(select id_raza from raza ra where ra.raza=?),(select id_casta from casta ca where ca.casta=?),0,0,0,0,0,0,0,0,0,0,0)";

		try {
			conn = ConexionSQL.getConnection();
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
		String queryUpdate = "update personaje set id_raza=(select id_raza from raza ra where ra.raza=?),id_casta=(select id_casta from casta ca where ca.casta=?) where id_usuario=?";
		try {
			conn = ConexionSQL.getConnection();

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