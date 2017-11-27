import java.awt.EventQueue;
import java.sql.SQLException;

import org.chatteron.demoproject.factory.Factory;
import org.chatteron.demoproject.factory.Source;
import org.chatteron.demoproject.factory.exceptions.FactoryException;
import org.chatteron.demoproject.gui.MainWindow;

import com.google.gson.Gson;

/**
 * 
 */

/**
 * @author Dr.Xperience
 *
 */
public class DemoProjectStarter
{
	public static void main(String[] args)
	{
		
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Gson g = new Gson();
					Factory.init(new Source());
					new MainWindow();					
				}
				catch (Exception e)
				{
					System.err.print("Error on startup , Message :: "+e.getMessage());
				}
								
			}
		});
	}

}
