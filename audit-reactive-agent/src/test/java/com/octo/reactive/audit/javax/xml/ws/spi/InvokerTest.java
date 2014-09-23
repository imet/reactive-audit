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

package com.octo.reactive.audit.javax.xml.ws.spi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.spi.Invoker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokerTest
{
	private final Invoker x = new Invoker()
	{

		@Override
		public void inject(WebServiceContext webServiceContext)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{

		}

		@Override
		public Object invoke(Method m, Object... args)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{
			return null;
		}
	};

	@Test(expected = NetworkAuditReactiveException.class)
	public void invoke() throws InvocationTargetException, IllegalAccessException
	{
		AuditReactive.strict.commit();
		x.invoke((Method)null, (Object)null);
	}

}