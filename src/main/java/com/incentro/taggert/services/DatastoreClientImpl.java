package com.incentro.taggert.services;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public final class DatastoreClientImpl implements DatastoreClient {

  private final DatastoreService datastore =
      DatastoreServiceFactory.getDatastoreService();

  @Override
  public Entity get(String kind, Long id) {
    return get(KeyFactory.createKey(kind, id));
  }

  @Override
  public Entity get(String kind, String name) {
    return get(KeyFactory.createKey(kind, name));
  }

  @Override
  public Entity get(Key key) {
    try {
      return datastore.get(key);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  @Override
  public void put(Entity entity) {
    datastore.put(entity);
  }

  @Override
  public void put(Iterable<Entity> entities) {
    datastore.put(entities);
  }
}
