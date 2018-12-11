package com.incentro.taggert;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.users.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.incentro.taggert.services.DatastoreClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Singleton
public final class HomeServlet extends HttpServlet {

  private final DatastoreClient datastore;

  @Inject
  public HomeServlet(DatastoreClient datastore) {
    this.datastore = datastore;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    final Object o = req.getAttribute("user");
    if (o instanceof User) {
      final Entity user = datastore.get("user", ((User) o).getEmail());
      if (user != null) {
        req.setAttribute("name", user.getProperty("name").toString());
      }
    }

    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }
}
