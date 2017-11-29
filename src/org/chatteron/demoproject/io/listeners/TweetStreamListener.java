/**
 * 
 */
package org.chatteron.demoproject.io.listeners;

import java.util.ArrayDeque;

import org.chatteron.demoproject.json.TwitterStreamJSON;

/**
 * @author Dr.Xperience
 *
 */
public interface TweetStreamListener
{
	public void streamRecieved(ArrayDeque<TwitterStreamJSON> ad);
}
