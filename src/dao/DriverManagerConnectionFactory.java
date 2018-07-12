package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerConnectionFactory extends ConnectionFactory {

	private String url;
    private String username;
    private String password;
    
	public DriverManagerConnectionFactory(String url, String username, String password) {
		this.url = url;
		this.password = password;
		this.username = username;
	}

	@Override
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
		
}