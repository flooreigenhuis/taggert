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
