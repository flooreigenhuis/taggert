package com.incentro.taggert.services;

import com.google.appengine.api.users.User;


public interface UserInfoClient {

  User getCurrentUser();

  String getEmail();

  String createLoginURL(String path);

  String createLogoutURL(String path);

  boolean isUserAdmin();
}
