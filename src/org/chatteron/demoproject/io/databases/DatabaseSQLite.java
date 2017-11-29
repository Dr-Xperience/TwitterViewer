/**
 * 
 */
package org.chatteron.demoproject.io.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import org.chatteron.demoproject.io.IDatabase;

/**
 * @author Dr.Xperience
 *
 */
public class DatabaseSQLite implements IDatabase
{

	Connection connection;
	Statement statement;
	
	/* (non-Javadoc)
	 * @see org.chatteron.demoproject.io.IDatabase#createConnection(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createConnection() throws ClassNotFoundException, SQLException			
	{
		Class.forName("org.sqlite.JDBC");
		String p = DatabaseSQLite.class.getResource("/cache.db").getFile();
		connection = DriverManager.getConnection("jdbc:sqlite:"+p);
		statement = connection.createStatement();
		
	}

	/* (non-Javadoc)
	 * @see org.chatteron.demoproject.io.IDatabase#printDatabase()
	 */
	public List<String> printDatabase() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.chatteron.demoproject.io.IDatabase#insert(java.lang.String[])
	 */
	@Override
	public int insert(String... values) throws SQLException
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getApiKey() throws SQLException
	{
		ResultSet result = statement.executeQuery("Select apikey from access where id = 1;");
		if(result.next())
		{
			return result.getString("apikey");
		}
		else
			throw new SQLException("Table access is empty kindly create cache.db database");
	}

	@Override
	public String getApiSecretKey() throws SQLException
	{
		ResultSet result = statement.executeQuery("Select apikeysecret from access where id = 1;");
		if(result.next())
		{
			return result.getString("apikeysecret");
		}
		else
			throw new SQLException("Table access is empty kindly create cache.db database");
	}

	@Override
	public String getAppAccessToken() throws SQLException
	{
		ResultSet result = statement.executeQuery("Select accesstoken from access where id = 1;");
		if(result.next())
		{
			return result.getString("accesstoken");
		}
		else
			throw new SQLException("Table access is empty kindly create cache.db database");
	}

	@Override
	public String getAppAccessTokenSecret() throws SQLException
	{
		ResultSet result = statement.executeQuery("Select accesstokensecret from access where id = 1;");
		if(result.next())
		{
			return result.getString("accesstokensecret");
		}
		else
			throw new SQLException("Table access is empty kindly create cache.db database");
	}

	@Override
	public String getUserAccessToken() throws SQLException
	{
		ResultSet result = statement.executeQuery("Select accesstoken from access where id = 2;");
		if(result.next())
		{
			return result.getString("accesstoken");
		}
		else
			throw new SQLException("Table access is empty kindly create cache.db database");
	}

	@Override
	public String getUserAccessTokenSecret() throws SQLException
	{
		ResultSet result = statement.executeQuery("Select accesstokensecret from access where id = 2;");
		if(result.next())
		{
			return result.getString("accesstokensecret");
		}
		else
			throw new SQLException("Table access is empty kindly create cache.db database");
		
	}

}
