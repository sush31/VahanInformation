package databaseconnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPooling {

	private static DataSource ds;
	private static DataSource dsAlone;

	public static Connection getDBConnection() {
		Connection con = null;

		try {
			if (ds != null) {
				con = ds.getConnection();
			} else {
				String dsString = null;
				dsString = "java:/comp/env/jdbc/vow4";
				ds = (DataSource) new InitialContext().lookup(dsString);
				con = ds.getConnection();

			}
		} catch (NullPointerException | SQLException | NamingException ex) {
			//LOGGER.error(ex);
		} catch (Exception ex) {
			//LOGGER.error(ex);
		}
		return con;
	}

	public static Connection getDBConnectionStandAlone() {
		Connection con = null;
		try {
			if (dsAlone != null) {
				con = dsAlone.getConnection();
			} else {
				String dsString = "java:/comp/env/jdbc/vow4dash";
				dsAlone = (DataSource) new InitialContext().lookup(dsString);
				con = dsAlone.getConnection();
			}
		} catch (NullPointerException | SQLException | NamingException ex) {
			//LOGGER.error(ex);
		} catch (Exception ex) {
			//LOGGER.error(ex);
		}
		return con;
	}
}
