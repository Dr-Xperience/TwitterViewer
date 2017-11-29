/**
 * 
 */
package org.chatteron.demoproject.json;

/**
 * @author Dr.Xperience
 *
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TwitterStreamJSON implements Serializable
{

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("text")
	@Expose
	private String text;
	private final static long serialVersionUID = -4186720100214156158L;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public TwitterStreamJSON withName(String name)
	{
		this.name = name;
		return this;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public TwitterStreamJSON withText(String text)
	{
		this.text = text;
		return this;
	}

	@Override
	public String toString()
	{
		return "[text = "+text+", name = "+name+"]";
	}

}
