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
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.incentro.taggert.services.FakeDatastoreClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class HomeServletTest {

  private final LocalServiceTestHelper helper = new LocalServiceTestHelper();
  private FakeDatastoreClient datastore;

  @Mock
  private HttpServletRequest req;

  @Mock
  private HttpServletResponse resp;

  @Mock
  private RequestDispatcher requestDispatcher;

  @Before
  public void setUp() {
    helper.setUp();
    datastore = new FakeDatastoreClient();
  }

  @Test
  public void GetWithName() throws IOException, ServletException {
    final Entity entity = new Entity("user", "foo@bar.com");
    entity.setProperty("name", "fubar");
    datastore.fill(entity);

    final User user = new User("foo@bar.com", "somehost.net");

    MockitoAnnotations.initMocks(this);

    when(req.getRequestURI()).thenReturn("somehost.net/");
    when(req.getAttribute("user")).thenReturn(user);
    when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

    final StringWriter responseWriter = new StringWriter();
    when(resp.getWriter()).thenReturn(new PrintWriter(responseWriter));

    final HomeServlet servlet = new HomeServlet(datastore);
    servlet.doGet(req, resp);

    verify(req).setAttribute("name", "fubar");
    verify(req).getRequestDispatcher("/index.jsp");
  }

  @After
  public void tearDown() {
  }
}
