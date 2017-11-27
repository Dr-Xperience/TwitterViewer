/**
 * 
 */
package org.chatteron.demoproject.io.databases;

import java.sql.Connection;
import java.sql.DriverManager;
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
	public void createConnection(String url, String username, String password)
			throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc::sqlite:cache.db");
		statement = connection.createStatement();

	}

	/* (non-Javadoc)
	 * @see org.chatteron.demoproject.io.IDatabase#printDatabase()
	 */
	@Override
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

}
