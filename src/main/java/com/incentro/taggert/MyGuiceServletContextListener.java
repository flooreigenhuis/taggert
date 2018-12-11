package com.incentro.taggert;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
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
      }
    });
  }
}
