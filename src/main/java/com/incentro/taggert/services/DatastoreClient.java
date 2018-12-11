package com.incentro.taggert.services;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;


public interface DatastoreClient {

  Entity get(String kind, Long id);

  Entity get(String kind, String name);

  Entity get(Key key);

  void put(Entity entity);

  void put(Iterable<Entity> entities);
}
