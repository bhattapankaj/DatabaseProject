/**
 * Author: Pankaj Bhatta
 * Description: Opens the MySQL connection for the Employee Search program and
 *    pulls the department and project names for the two lists.
 *    The MySQL login comes from db.properties (see db.properties.example)
 *    so each of us can use our own password without pushing it to GitHub.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseConnection {

	private static final String PROPERTIES_FILE = "db.properties";

	private Connection conn;
	private String dbName;

	/**
	 * Opens a connection to the given database. Any connection that was
	 * already open gets closed first, so Fill can be pressed again with a
	 * different database name.
	 */
	public void open(String name) throws SQLException {
		close();

		if (name == null || name.isBlank()) {
			throw new SQLException("No database name was entered.");
		}
		name = name.trim();
		// the name goes straight into the URL, so only allow normal db names
		if (!name.matches("[A-Za-z0-9_$]+")) {
			throw new SQLException("\"" + name + "\" is not a valid database name.");
		}

		Properties props = loadProperties();
		String host = props.getProperty("host", "localhost");
		String port = props.getProperty("port", "3306");
		String user = props.getProperty("user", "root");
		String password = props.getProperty("password", "");

		String url = "jdbc:mysql://" + host + ":" + port + "/" + name;
		conn = DriverManager.getConnection(url, user, password);
		dbName = name;
	}

	public boolean isOpen() {
		try {
			return conn != null && !conn.isClosed();
		} catch (SQLException e) {
			return false;
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public String getDatabaseName() {
		return dbName;
	}

	public List<String> getDepartments() throws SQLException {
		return getNames("SELECT Dname FROM DEPARTMENT ORDER BY Dname");
	}

	public List<String> getProjects() throws SQLException {
		return getNames("SELECT Pname FROM PROJECT ORDER BY Pname");
	}

	// runs a one column query and returns the values as a list
	private List<String> getNames(String sql) throws SQLException {
		if (!isOpen()) {
			throw new SQLException("Database is not open.");
		}
		List<String> names = new ArrayList<String>();
		try (PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				names.add(rs.getString(1));
			}
		}
		return names;
	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// nothing useful to do here, we are throwing it away anyway
			}
		}
		conn = null;
		dbName = null;
	}

	// reads db.properties from the project folder, falls back to root with no password
	private Properties loadProperties() {
		Properties props = new Properties();
		try (FileInputStream in = new FileInputStream(PROPERTIES_FILE)) {
			props.load(in);
		} catch (IOException e) {
			System.out.println("No " + PROPERTIES_FILE + " found, using root with no password.");
		}
		return props;
	}
}
