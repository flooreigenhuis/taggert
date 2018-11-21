package nl.hayovanloon.aebase.services;

import com.google.appengine.api.users.User;


public interface UserInfoServiceClient {

  User getCurrentUser();

  String getEmail();

  String createLoginURL(String path);

  String createLogoutURL(String path);

  boolean isUserAdmin();
}
