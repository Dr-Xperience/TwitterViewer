/**
 * 
 */
package org.chatteron.demoproject.factory;

import org.chatteron.demoproject.factory.exceptions.FactoryException;
import org.chatteron.demoproject.factory.exceptions.FactoryRuntimeException;

/**
 * @author Dr.Xperience
 *
 */
public class Factory
{

	private Factory()
	{

	}

	private ISource source;

	private static Factory object;

	public static void init(ISource source) throws FactoryException
	{
		if (source == null)
			throw new FactoryException("Source cannot be null");

		if (object == null)
		{
			object = new Factory();
			object.source = source;
		}
		else
		{
			object.source = source;
		}
	}

	public static Factory getFactory() throws FactoryException
	{
		if (object == null)
			throw new FactoryException("Factory not initialized yet, run Factory.init(source);");
		
		return object;
	}

	public ISource getSource()
	{
		if(source == null)
			throw new FactoryRuntimeException("Source is not initalized yet, run Factory.init(source);");
		return source;
	}

}
