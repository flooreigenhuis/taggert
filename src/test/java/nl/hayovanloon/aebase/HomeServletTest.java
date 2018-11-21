package nl.hayovanloon.aebase;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.users.User;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import nl.hayovanloon.aebase.services.FakeDatastoreClient;
import org.junit.After;
import org.junit.Assert;
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

  @Mock private HttpServletRequest req;

  @Mock private HttpServletResponse resp;

  @Mock private RequestDispatcher requestDispatcher;

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
