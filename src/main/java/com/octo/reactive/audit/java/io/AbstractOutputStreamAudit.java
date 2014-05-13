package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;

import java.io.*;
import java.lang.reflect.Field;

public class AbstractOutputStreamAudit extends AbstractAudit
{
	static final Field fieldOutFilterOutputStream;
	static final Field fieldBoutObjectOutputStream;
	static final Field fieldOutObjectOutputStream;


	static
	{
		try
		{
			fieldOutFilterOutputStream = FilterOutputStream.class.getDeclaredField("out");
			fieldOutFilterOutputStream.setAccessible(true);
			fieldBoutObjectOutputStream = ObjectOutputStream.class.getDeclaredField("bout");
			fieldBoutObjectOutputStream.setAccessible(true);
			fieldOutObjectOutputStream = fieldBoutObjectOutputStream.getType().getDeclaredField("out");
			fieldOutObjectOutputStream.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

	protected boolean isLastOutputStreamWithLatency(OutputStream out)
	{
		while (out instanceof FilterOutputStream
				|| out instanceof ObjectOutputStream)
		{
			try
			{
				if (out instanceof FilterOutputStream)
				{
					FilterOutputStream filter = (FilterOutputStream) out;
					out = (OutputStream) fieldOutFilterOutputStream.get(filter);
				}
				else
				{
					ObjectOutputStream objIn=(ObjectOutputStream)out;
					// Ok for Java8
					out=(OutputStream)fieldOutObjectOutputStream.get(
							fieldBoutObjectOutputStream.get(objIn)
						);
				}
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		return (out instanceof FileOutputStream);
	}

}