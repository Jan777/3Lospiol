package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionSQL {
	private static Connection conn;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String URL = "jdbc:sqlite:src/main/resources/WarLords.db";

	private ConexionSQL() {

	}

	public static Connection getConnection() {
		try {
			if (conn == null) {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL);
			}
		} catch (ClassNotFoundException cnfe) {
			
		} catch (SQLException sqle) {
			
		}
		return conn;
	}

	public static void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException sqle) {
		}
	}
}