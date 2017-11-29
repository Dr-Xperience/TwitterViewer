/**
 * 
 */
package org.chatteron.demoproject.io.nodesocket;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayDeque;

import javax.swing.SwingUtilities;

import org.chatteron.demoproject.factory.Factory;
import org.chatteron.demoproject.factory.exceptions.FactoryException;
import org.chatteron.demoproject.io.ITwitterStream;
import org.chatteron.demoproject.io.listeners.TweetStreamListener;
import org.chatteron.demoproject.json.ClientJSON;
import org.chatteron.demoproject.json.TwitterStreamJSON;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * @author Dr.Xperience
 *
 */
public class TwitterStream implements ITwitterStream
{

	/**
	 * 
	 */
	private ArrayDeque<TwitterStreamJSON> tweetQueue;
	private TweetStreamListener l;
	
	public TwitterStream()
	{
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.chatteron.demoproject.io.ITwitterStream#streamData(java.lang.String[])
	 */
	@Override
	public void streamData(String... keywords) throws URISyntaxException, SQLException, FactoryException
	{
		IO.Options opt = new IO.Options();
		opt.path="/streaming";
		Socket socket = IO.socket("http://localhost:3000",opt);					
		socket.connect();
		//socket = socket.open();

		socket.emit("foo"," :: YO!!");
		
		ClientJSON  client = new ClientJSON();
		client.apiKey = Factory.getFactory().getSource().getDatabase().getApiKey();
		client.apiSecret = Factory.getFactory().getSource().getDatabase().getApiSecretKey();
		client.accessToken = Factory.getFactory().getSource().getDatabase().getAppAccessToken();
		client.accessTokenSecret = Factory.getFactory().getSource().getDatabase().getAppAccessTokenSecret();
		
		Gson g = new Gson();
		
		String clientString = g.toJson(client);
//		System.err.println(clientString);
		String words = g.toJson(keywords);
		System.err.println(words);
		socket.on("connect", new Emitter.Listener()
		{

			@Override
			public void call(Object... args)
			{
				System.err.println("Connect");

				socket.emit("client", clientString,words);

			}
		}).on("message", new Emitter.Listener()
		{

			@Override
			public void call(Object... args)
			{
				
				System.out.println(args[0].toString());
				socket.emit("foo",":: Bro");

			}
		}).on("streamData", new Emitter.Listener()
		{
			
			@Override
			public void call(Object... args)
			{
				if(args[0].toString().equals("[]") == false && args != null)
				{
					
					System.out.println(args[0].toString());
					tweetQueue = g.fromJson(args[0].toString(), new TypeToken<ArrayDeque<TwitterStreamJSON>>(){}.getType());
//					if(l != null)
//					{
//						SwingUtilities.invokeLater(new Runnable()
//						{
//							
//							@Override
//							public void run()
//							{
//								l.streamRecieved(tweetQueue);
//								
//							}
//						});
//						
//					}
				}
				
			}
		});
		
		socket.on("connection", new Emitter.Listener()
		{
			
			@Override
			public void call(Object... args)
			{
				// TODO Auto-generated method stub
				System.out.println(args[0].toString());
			}
		});
		socket.emit("foo"," :: Bye!!");
		
	}

	

	@Override
	public void addTweetStreamListerner(TweetStreamListener l)
	{
		if(l != null)
			this.l = l;
		else
			throw new NullPointerException();
		
	}

}
