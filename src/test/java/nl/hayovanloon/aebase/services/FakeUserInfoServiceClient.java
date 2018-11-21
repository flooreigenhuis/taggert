package nl.hayovanloon.aebase.services;

import com.google.appengine.api.users.User;

public class FakeUserInfoServiceClient implements UserInfoServiceClient {

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
