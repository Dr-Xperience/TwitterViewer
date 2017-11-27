/**
 * 
 */
package org.chatteron.demoproject.factory;

import org.chatteron.demoproject.io.IDatabase;

/**
 * @author Dr.Xperience
 *
 */
public interface ISource
{
	IDatabase getDatabase() throws Exception;
	
}
