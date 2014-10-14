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

package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractReaderAudit;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 4
@Aspect
public class UnmarshallerAudit extends AbstractFileAudit
{
	@Before("call(* javax.xml.bind.Unmarshaller.unmarshal(java.io.File))")
	public void unmarshal(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.bind.Unmarshaller.unmarshal(java.io.InputStream)) && args(in)")
	public void unmarshal(JoinPoint thisJoinPoint, InputStream in)
	{
		final ReactiveAuditException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, new  ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return ex;
			}
		});
	}

	@Before("call(* javax.xml.bind.Unmarshaller.unmarshal(java.io.Reader)) && args(in)")
	public void unmarshal(JoinPoint thisJoinPoint, Reader in)
	{
		final ReactiveAuditException ex = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, new  ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return ex;
			}
		});
	}

	@Before("call(* javax.xml.bind.Unmarshaller.unmarshal(java.net.URL)) && args(url)")
	public void unmarshal(JoinPoint thisJoinPoint, URL url)
	{
		super.logLatency(HIGH, thisJoinPoint, URLTools.latencyURL(config, thisJoinPoint, url));
	}
}
