/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gemstone.gemfire.security;

import com.gemstone.gemfire.test.junit.categories.DistributedTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(DistributedTest.class)
public class IntegratedClientAuthDUnitTest extends AbstractIntegratedClientAuthDistributedTest {

  @Test
  public void testAuthentication(){
    int port = serverPort;
    client1.invoke("logging in super-user with correct password", () -> {
      SecurityTestUtils.createCacheClient("super-user", "1234567", port, SecurityTestUtils.NO_EXCEPTION);
    });

    client2.invoke("logging in super-user with wrong password", () -> {
      SecurityTestUtils.createCacheClient("super-user", "wrong", port, SecurityTestUtils.AUTHFAIL_EXCEPTION);
    });
  }
}


