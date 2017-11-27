/**
 * 
 */
package org.chatteron.demoproject.factory;

import org.chatteron.demoproject.io.IDatabase;
import org.chatteron.demoproject.io.databases.DatabaseSQLite;

/**
 * @author Dr.Xperience
 *
 */
public class Source implements ISource
{

	private IDatabase db;
	/* (non-Javadoc)
	 * @see org.chatteron.demoproject.factory.ISource#getDatabase()
	 */
	@Override	
	public IDatabase getDatabase()throws Exception
	{
		// TODO Auto-generated method stub
		if(db == null)
			{
				db = new DatabaseSQLite();
				return db;
			}
		else
		  throw new Exception("Database not found");
	}
	

}
