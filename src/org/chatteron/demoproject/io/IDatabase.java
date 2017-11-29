/**
 * 
 */
package org.chatteron.demoproject.io;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Dr.Xperience
 *
 */

public interface IDatabase
{
	void createConnection() throws ClassNotFoundException, SQLException;
	
	int insert(String... values)throws SQLException;
	
	String getApiKey() throws SQLException;
	String getApiSecretKey() throws SQLException;
	String getAppAccessToken() throws SQLException;
	String getAppAccessTokenSecret() throws SQLException;
	String getUserAccessToken() throws SQLException;
	String getUserAccessTokenSecret() throws SQLException;
	
	
}