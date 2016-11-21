package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSQL {
	private static Connection conexion;
	private static String URL = "src/main/resources/WarLords.db";
	private Statement consulta;

	public ConexionSQL() {

	}

	public void conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
		}
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:" + URL);
			this.consulta = conexion.createStatement();
		} catch (SQLException e) {
		}
	}

	public void desconectar() {
		try {
			if (conexion != null) {
				conexion.close();
				this.consulta.close();
			}
		} catch (SQLException sqle) {
		}
	}

	public Statement getConsulta() {
		return this.consulta;
	}

	public Connection getConexion() {
		return conexion;
	}
}