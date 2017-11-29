/**
 * 
 */
package org.chatteron.demoproject.factory;

import org.chatteron.demoproject.factory.exceptions.FactoryException;
import org.chatteron.demoproject.io.IDatabase;
import org.chatteron.demoproject.io.IOAuth;
import org.chatteron.demoproject.io.ITwitterStream;

/**
 * @author Dr.Xperience
 *
 */
public interface ISource
{
	IDatabase getDatabase() throws FactoryException;
	IOAuth getOAuth() throws FactoryException;
	ITwitterStream getTwitterStream() throws FactoryException;
	
	
}
