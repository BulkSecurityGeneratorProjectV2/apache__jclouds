/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.cloudstack.features;

import com.google.inject.TypeLiteral;
import org.jclouds.cloudstack.options.GenerateUsageRecordsOptions;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.functions.ParseFirstJsonValueNamed;
import org.jclouds.rest.functions.MapHttp4xxCodesToExceptions;
import org.jclouds.rest.internal.RestAnnotationProcessor;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Tests behavior of {@code GlobalUsageAsyncClient}
 *
 * @author Richard Downer
 */
@Test(groups = "unit", testName = "GlobalUsageAsyncClientTest")
public class GlobalUsageAsyncClientTest extends BaseCloudStackAsyncClientTest<GlobalUsageAsyncClient> {

   public void testGenerateUsageRecords() throws Exception {
      Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
      c.set(Calendar.YEAR, 2012);
      c.set(Calendar.MONTH, Calendar.JANUARY);
      c.set(Calendar.DAY_OF_MONTH, 1);
      Date start = c.getTime();
      c.set(Calendar.DAY_OF_MONTH, 31);
      Date end = c.getTime();

      Method method = GlobalUsageAsyncClient.class.getMethod("generateUsageRecords",
         Date.class, Date.class, GenerateUsageRecordsOptions[].class);
      HttpRequest httpRequest = processor.createRequest(method, start, end);

      assertRequestLineEquals(httpRequest,
         "GET http://localhost:8080/client/api?response=json&command=generateUsageRecords&startdate=2012-01-01&enddate=2012-01-31 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseFirstJsonValueNamed.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, MapHttp4xxCodesToExceptions.class);

      checkFilters(httpRequest);
   }

   public void testGenerateUsageRecordsOptions() throws Exception {
      Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
      c.set(Calendar.YEAR, 2012);
      c.set(Calendar.MONTH, Calendar.JANUARY);
      c.set(Calendar.DAY_OF_MONTH, 1);
      Date start = c.getTime();
      c.set(Calendar.DAY_OF_MONTH, 31);
      Date end = c.getTime();

      Method method = GlobalUsageAsyncClient.class.getMethod("generateUsageRecords",
         Date.class, Date.class, GenerateUsageRecordsOptions[].class);
      HttpRequest httpRequest = processor.createRequest(method, start, end, GenerateUsageRecordsOptions.Builder.domainId(42));

      assertRequestLineEquals(httpRequest,
         "GET http://localhost:8080/client/api?response=json&command=generateUsageRecords&startdate=2012-01-01&enddate=2012-01-31&domainid=42 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseFirstJsonValueNamed.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, MapHttp4xxCodesToExceptions.class);

      checkFilters(httpRequest);
   }

   @Override
   protected TypeLiteral<RestAnnotationProcessor<GlobalUsageAsyncClient>> createTypeLiteral() {
      return new TypeLiteral<RestAnnotationProcessor<GlobalUsageAsyncClient>>() {
      };
   }
}
