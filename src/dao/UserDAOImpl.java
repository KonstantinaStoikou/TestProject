package dao;

import java.util.List;

import dao.ConnectionFactory;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import dao.DAOUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

public class UserDAOImpl implements UserDAO {
	
	private static final String SQL_FIND_BY_EMAIL = "SELECT emai, password FROM Users WHERE emai = ?";
	private static final String SQL_LIST_ORDER_BY_EMAIL = "SELECT emai, password FROM Users ORDER BY emai";
	private static final String SQL_INSERT = "INSERT INTO Users (emai, password) VALUES (?, ?)";
	
	private ConnectionFactory factory;
    
    public UserDAOImpl(boolean pool)
    {
    	factory = ConnectionFactory.getInstance(pool);
    	System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiii");
    	System.out.println(factory);
    }

    @Override
	public User find(String email) {
		User user = null;
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
		try (
			Connection connection = factory.getConnection();
			PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_FIND_BY_EMAIL, false, email);
	        ResultSet resultSet = statement.executeQuery();)
		{
			System.out.println("yyyyyyyyyyyyyyyyyyy");
	        if (resultSet.next()) 
	            user = map(resultSet);
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
     
        return user;
	}

	@Override
	public List<User> list() {
		List<User> users = new ArrayList<>();

        try (
            Connection connection = factory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_EMAIL);
            ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                users.add(map(resultSet));
            }
        } 
        catch (SQLException e) {
        	System.err.println(e.getMessage());
        }

        return users;
	}

	@Override
	public int create(User user) 
	{
		int ret = -1;
		Object[] values = { user.getEmai(), user.getPassword()};

		try (Connection connection = factory.getConnection();
				PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);) 
		{
			int affectedRows = statement.executeUpdate();
			ret = affectedRows;
			if (ret == 0) {
				System.err.println("Creating user failed, no rows affected.");
				return ret;
			}
			else {
				return ret;
			}

		} 
		catch (SQLException e) {
			System.err.println("Creating user failed, no generated key obtained.");
			return ret;
		}
	}
	
	private static User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setEmai(resultSet.getString("email"));
        return user;
    }
	

}
