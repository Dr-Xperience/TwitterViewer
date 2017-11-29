/**
 * 
 */
package org.chatteron.demoproject.factory;

import java.sql.SQLException;

import org.chatteron.demoproject.factory.exceptions.FactoryException;
import org.chatteron.demoproject.io.IDatabase;
import org.chatteron.demoproject.io.IOAuth;
import org.chatteron.demoproject.io.ITwitterStream;
import org.chatteron.demoproject.io.OAuth.OAuthTwitter;
import org.chatteron.demoproject.io.databases.DatabaseSQLite;
import org.chatteron.demoproject.io.nodesocket.TwitterStream;

/**
 * @author Dr.Xperience
 *
 */
public class Source implements ISource
{

	private IDatabase db;
	private IOAuth auth;
	private ITwitterStream twitStream;
	/* (non-Javadoc)
	 * @see org.chatteron.demoproject.factory.ISource#getDatabase()
	 */
	@Override	
	public IDatabase getDatabase()throws FactoryException
	{
		// TODO Auto-generated method stub
		if(db == null)
			{
				db = new DatabaseSQLite();
				try
				{
					db.createConnection();
				}
				catch (ClassNotFoundException e)
				{
					new FactoryException("sqlite driver not configured");
				}
				catch (SQLException e)
				{
					new FactoryException(e.getMessage());
				}
			}

		  return db;
	}
	@Override
	public IOAuth getOAuth() throws FactoryException
	{
		if(auth == null)
		{
			try
			{
				auth = new OAuthTwitter();
			}
			catch (SQLException e)
			{
				throw new FactoryException(e.getMessage());
			}					
		}
		
		  return auth;
	}
	@Override
	public ITwitterStream getTwitterStream() throws FactoryException
	{
		if(twitStream == null)
		{
			twitStream = new TwitterStream();
		}
		
		return twitStream;
	}
	

}
