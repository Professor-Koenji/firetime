package com.koenji.ecs.service;

import java.util.HashMap;
import java.util.Map;

public enum Locator {
  INSTANCE;

  private Map<Class<?>, Object> services = new HashMap<>();

  public <T> void registerService(Class<T> service, Class<? extends T> instance) {
    if(!services.containsKey(service)){
      try {
        Object in = instance.getConstructor().newInstance();
        services.put(service, in);
      } catch (Exception e) {
        throw new IllegalArgumentException("Unable to create service instance");
      }
    }
  }

  public <T> T getService(Class<T> service) {

    if(services.containsKey(service)) {
      return service.cast(services.get(service));
    } else {
      return null;
    }
  }
}
