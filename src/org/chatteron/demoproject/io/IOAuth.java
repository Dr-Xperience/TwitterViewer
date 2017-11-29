/**
 * 
 */
package org.chatteron.demoproject.io;

/**
 * @author Dr.Xperience
 *
 */
public interface IOAuth
{

	String getAccessToken();

	String getAccessTokenSecret();
	
	Boolean isAccessGranted();

}
