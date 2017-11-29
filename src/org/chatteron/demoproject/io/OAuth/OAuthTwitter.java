/**
 * 
 */
package org.chatteron.demoproject.io.OAuth;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.chatteron.demoproject.factory.Factory;
import org.chatteron.demoproject.factory.exceptions.FactoryException;
import org.chatteron.demoproject.io.IOAuth;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

/**
 * @author Dr.Xperience
 *
 */
public class OAuthTwitter implements IOAuth
{

	/**
	 * 
	 */

	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
	private String accessToken;
	private String accessTokenSecret;
	private OAuth10aService service;

	public OAuthTwitter() throws SQLException, FactoryException
	{

		service = new ServiceBuilder(Factory.getFactory().getSource().getDatabase().getApiKey())
				.apiSecret(Factory.getFactory().getSource().getDatabase().getApiSecretKey())
				.callback("http://localhost").build(TwitterApi.instance());
		

	}

	public void test() throws IOException, InterruptedException, ExecutionException
	{
		final OAuth10aService service = new ServiceBuilder("COjmLq93AEFoDiKkLdjUnU7s0")
				.apiSecret("4lq3uMVKOQy46DIdOYntHcxVbF3aQenp5F3Cbb7Oai5lza4813").callback("http://localhost")
				.build(TwitterApi.instance());
		final Scanner in = new Scanner(System.in);

		System.out.println("=== Twitter's OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		final OAuth1RequestToken requestToken = service.getRequestToken();

		System.out.println("Got the Request Token!");
		System.out.println();

		System.out.println("Now go and authorize ScribeJava here:");
		System.out.println(service.getAuthorizationUrl(requestToken));
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		final String oauthVerifier = in.nextLine();
		System.out.println();

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, oauthVerifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious the raw answer looks like this: " + accessToken.getRawResponse() + "')");

		System.out.println("acesstoken " + accessToken.getToken());
		System.out.println("acesstoken secret " + accessToken.getTokenSecret());
		System.out.println("acesstoken toString " + accessToken.toString());

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		final Response response = service.execute(request);
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getBody());

		System.out.println();
		System.out.println("That's it man! Go and build something awesome with ScribeJava! :)");
	}

	@Override
	public String getAccessToken()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccessTokenSecret()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isAccessGranted()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
