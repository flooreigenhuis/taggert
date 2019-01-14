/*
 * Copyright 2019 Incentro
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

package com.incentro.taggert;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.incentro.taggert.api.v1b1.ItemsServlet;
import com.incentro.taggert.services.DatastoreClient;
import com.incentro.taggert.services.DatastoreClientImpl;
import com.incentro.taggert.services.UserInfoClient;
import com.incentro.taggert.services.UserInfoClientImpl;


public final class MyGuiceServletContextListener
    extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {
        bind(UserInfoClient.class).to(UserInfoClientImpl.class);
        bind(DatastoreClient.class).to(DatastoreClientImpl.class);

        filter("/").through(UserInfoFilter.class);

        serve("/").with(HomeServlet.class);

        serve("/api/v1b1/datasets/default/items/bla").with(ItemsServlet.class);
      }
    });
  }
}
