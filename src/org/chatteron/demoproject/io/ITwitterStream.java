/**
 * 
 */
package org.chatteron.demoproject.io;

import java.net.URISyntaxException;
import java.sql.SQLException;

import org.chatteron.demoproject.factory.exceptions.FactoryException;
import org.chatteron.demoproject.io.listeners.TweetStreamListener;

/**
 * @author Dr.Xperience
 *
 */
public interface ITwitterStream
{
	void streamData(String... keywords) throws URISyntaxException, SQLException, FactoryException;
	void addTweetStreamListerner(TweetStreamListener l);
	
}
