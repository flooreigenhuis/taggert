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

package com.incentro.taggert.services;

import com.google.appengine.api.users.User;

public class FakeUserInfoClient implements UserInfoClient {

  private User currentUser;
  private boolean userAdmin;

  @Override
  public User getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(User currentUser) {
    this.currentUser = currentUser;
  }

  @Override
  public String getEmail() {
    return currentUser.getEmail();
  }

  @Override
  public String createLoginURL(String path) {
    return "/fake-login-url";
  }

  @Override
  public String createLogoutURL(String path) {
    return "/fake-logout-url";
  }

  @Override
  public boolean isUserAdmin() {
    return false;
  }

  public void setUserAdmin(boolean userAdmin) {
    this.userAdmin = userAdmin;
  }
}
