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
	void createConnection(String url, String username, String password)throws ClassNotFoundException,SQLException;
	List<String> printDatabase() throws SQLException;
	int insert(String... values)throws SQLException;	
	
}