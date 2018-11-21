package nl.hayovanloon.aebase;

import com.google.appengine.api.users.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import nl.hayovanloon.aebase.services.UserInfoServiceClient;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Singleton
public final class UserInfoFilter implements Filter {

  private final UserInfoServiceClient userInfoServiceClient;

  @Inject
  public UserInfoFilter(UserInfoServiceClient userInfoServiceClient) {
    this.userInfoServiceClient = userInfoServiceClient;
  }

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) request;

    final User user = userInfoServiceClient.getCurrentUser();
    if (user == null) {
      req.setAttribute("loginUrl", userInfoServiceClient.createLoginURL("/"));
    } else {
      req.setAttribute("user", user);
      req.setAttribute("logoutUrl", userInfoServiceClient.createLogoutURL("/"));
    }

    chain.doFilter(req, response);
  }

  @Override
  public void destroy() {
  }
}
