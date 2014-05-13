package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;
import static com.octo.reactive.audit.TestTools.*;

/**
 * Created by pprados on 06/05/14.
 */
public class FileOutputStreamTest extends AuditOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		return new FileOutputStream(getFileOut());
	}

	private File getFileOut() throws IOException
    {
        push();
        File f=File.createTempFile("temp-file-name", ".tmp");
        f.delete();
        f.deleteOnExit();
	    pop();
        return f;
    }
	@Override
	@Test(expected=AuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void flush() throws IOException
	{
		super.flush();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void close() throws IOException
	{
		super.close();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void write_b() throws IOException
	{
		super.write_b();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void write_B() throws IOException
	{
		super.write_B();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void write_Bii() throws IOException
	{
		super.write_Bii();
	}
	@Test
	public void derived() throws IOException
	{
		class Derived extends FileOutputStream
		{
			Derived() throws IOException
			{
				super(getFileOut());
			}
		};
		new Derived();
	}
}
