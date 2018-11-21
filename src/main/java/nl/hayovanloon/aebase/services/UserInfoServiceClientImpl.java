package nl.hayovanloon.aebase.services;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


public final class UserInfoServiceClientImpl implements UserInfoServiceClient {

  private final UserService userService = UserServiceFactory.getUserService();

  @Override
  public String createLoginURL(String path) {
    return userService.createLoginURL(path);
  }

  @Override
  public String createLogoutURL(String path) {
    return userService.createLogoutURL(path);
  }

  @Override
  public User getCurrentUser() {
    return userService.getCurrentUser();
  }

  @Override
  public String getEmail() {
    final User user = userService.getCurrentUser();
    return user == null ? null : user.getEmail();
  }

  @Override
  public boolean isUserAdmin() {
    return userService.isUserAdmin();
  }
}
