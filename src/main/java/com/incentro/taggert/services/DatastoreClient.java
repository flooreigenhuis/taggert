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

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;


public interface DatastoreClient {

  Entity get(String kind, Long id);

  Entity get(String kind, String name);

  Entity get(Key key);

  void put(Entity entity);

  void put(Iterable<Entity> entities);
}
