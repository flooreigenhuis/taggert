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

import com.google.appengine.api.users.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.incentro.taggert.services.UserInfoClient;

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

  private final UserInfoClient userInfoClient;

  @Inject
  public UserInfoFilter(UserInfoClient userInfoClient) {
    this.userInfoClient = userInfoClient;
  }

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) request;

    final User user = userInfoClient.getCurrentUser();
    if (user == null) {
      req.setAttribute("loginUrl", userInfoClient.createLoginURL("/"));
    } else {
      req.setAttribute("user", user);
      req.setAttribute("logoutUrl", userInfoClient.createLogoutURL("/"));
    }

    chain.doFilter(req, response);
  }

  @Override
  public void destroy() {
  }
}
