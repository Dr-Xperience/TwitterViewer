import java.awt.EventQueue;

import org.chatteron.demoproject.factory.Factory;
import org.chatteron.demoproject.factory.Source;
import org.chatteron.demoproject.gui.MainWindow;

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

					 Factory.init(new Source());
					 new MainWindow();
				}
				catch (Exception e)
				{
					System.err.print("Error on startup , Message :: " + e.getMessage()+ " :: "+e);
				}

			}
		});
	}

}
