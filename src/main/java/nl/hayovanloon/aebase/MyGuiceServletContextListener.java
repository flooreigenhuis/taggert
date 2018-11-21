package nl.hayovanloon.aebase;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import nl.hayovanloon.aebase.services.DatastoreClient;
import nl.hayovanloon.aebase.services.DatastoreClientImpl;
import nl.hayovanloon.aebase.services.UserInfoServiceClient;
import nl.hayovanloon.aebase.services.UserInfoServiceClientImpl;


public final class MyGuiceServletContextListener
    extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {
        bind(UserInfoServiceClient.class).to(UserInfoServiceClientImpl.class);
        bind(DatastoreClient.class).to(DatastoreClientImpl.class);

        filter("/").through(UserInfoFilter.class);

        serve("/").with(HomeServlet.class);
      }
    });
  }
}
