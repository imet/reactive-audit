/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit.javax.tools;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import javax.tools.FileObject;
import java.io.IOException;
import java.sql.SQLException;

public class FileObjectTest
{
	private final FileObject x = (FileObject) TestTools.createProxy(FileObject.class);

	@Test(expected = FileReactiveAuditException.class)
	public void delete()
	{
        TestTools.strict.commit();
		x.delete();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void openInputStream()
			throws IOException
	{
        TestTools.strict.commit();
		x.openInputStream();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void openOutputStream()
			throws InterruptedException, IOException, SQLException
	{
        TestTools.strict.commit();
		x.openOutputStream();
	}

	@Test(expected = FileReactiveAuditException.class)
	public void openReader()
			throws InterruptedException, IOException, SQLException
	{
        TestTools.strict.commit();
		x.openReader(true);
	}

	@Test(expected = FileReactiveAuditException.class)
	public void openWriter()
			throws InterruptedException, IOException, SQLException
	{
        TestTools.strict.commit();
		x.openWriter();
	}
}
