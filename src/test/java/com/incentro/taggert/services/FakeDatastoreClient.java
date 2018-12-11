package com.incentro.taggert.services;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FakeDatastoreClient implements DatastoreClient {

  private Map<Key, Entity> store = new HashMap<>();
  private Map<Key, List<Entity>> putStore = new HashMap<>();

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
    final List<Entity> puts = putStore.getOrDefault(key, new ArrayList<>());
    if (puts.isEmpty()) {
      return store.get(key);
    } else {
      return puts.get(puts.size() - 1);
    }
  }

  @Override
  public void put(Entity entity) {
    final Key key = entity.getKey();
    final List<Entity> puts = putStore.getOrDefault(key, new ArrayList<>());
    puts.add(entity);
    putStore.put(key, puts);
  }

  @Override
  public void put(Iterable<Entity> entities) {
    for (Entity entity : entities) {
      put(entity);
    }
  }

  /**
   * Fills the fake Datastore with entities
   * @param entities  entities to fill store with
   */
  public void fill(Entity... entities) {
    for (Entity entity : entities) {
      store.put(entity.getKey(), entity);
    }
  }

  /**
   * Get a map of keys and lists of entities. Lists are ordered by insertion
   * time, oldest first.
   * @return  the result of all put actions
   */
  public Map<Key, List<Entity>> getPutsMap() {
    return putStore;
  }

  /**
   * Get a collection of all entries put into the store. Ordering per key streak
   * is insertion time.
   * @return  an iterable of enitities
   */
  public Iterable<Entity> getPuts() {
    final List<Entity> entities = new ArrayList<>(putStore.size());
    for (Collection<Entity> puts : putStore.values()) {
      entities.addAll(puts);
    }
    return entities;
  }
}
