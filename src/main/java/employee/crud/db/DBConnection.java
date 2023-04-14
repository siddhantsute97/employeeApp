package employee.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static final String dbURL = "jdbc:mysql://localhost:3306/tommy";
	public static final String dbUser = "root";
	public static final String dbPassword = "sql123";
	

	public static Connection getConnection() {
		System.out.println("start connection");
		try {
			//load mysql jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//open connection
			connection = DriverManager.getConnection(dbURL,dbUser,dbPassword);
			if (connection !=null) {
				System.out.println("connected");
				return connection;
			}else {
				System.out.println("connection issue");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Exception occur"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	public static  Connection connection = getConnection();
	public static void main(String[] args) {
		System.out.println(DBConnection.connection);
		
		
	}
}
