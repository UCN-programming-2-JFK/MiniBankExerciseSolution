package solution.minibankexercise.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	final static String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=MiniBank01;user=minibankuser;password=password;encrypt=false;";

	private Database() {}

	public static Connection getConnection() throws Exception {

		Connection connection;
		try {
			connection = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			throw new Exception("Error connecting to database. Error was: '" + e.getMessage() + "'", e);
		}
		return connection;
	}
}
