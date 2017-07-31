/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.anubis.suites;

import io.mifos.core.test.env.TestEnvironment;
import io.mifos.core.test.fixture.TenantDataStoreContextTestRule;
import io.mifos.core.test.fixture.cassandra.CassandraInitializer;
import org.junit.ClassRule;
import org.junit.rules.RuleChain;
import org.junit.rules.RunExternalResourceOnce;
import org.junit.rules.TestRule;

/**
 * @author Myrle Krantz
 */
public class SuiteTestEnvironment {
  public static final String APP_NAME = "anubis-v1";

  protected final static TestEnvironment testEnvironment = new TestEnvironment(APP_NAME);
  protected final static CassandraInitializer cassandraInitializer = new CassandraInitializer();
  private final static TenantDataStoreContextTestRule tenantDataStoreContext = TenantDataStoreContextTestRule.forRandomTenantName(cassandraInitializer);

  @ClassRule
  public static TestRule orderClassRules = RuleChain
      .outerRule(new RunExternalResourceOnce(testEnvironment))
      .around(new RunExternalResourceOnce(cassandraInitializer))
      .around(tenantDataStoreContext);
}