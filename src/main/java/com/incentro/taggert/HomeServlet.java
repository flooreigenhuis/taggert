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