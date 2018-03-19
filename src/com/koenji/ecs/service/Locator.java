package com.koenji.ecs.service;

import java.util.HashMap;
import java.util.Map;

public class Locator {

  private static Map<Class<?>, Object> services = new HashMap<>();

  public static <T> void register(Class<T> service, Class<? extends T> instance) {
    Locator.register(service, instance, true);
  }

  public static <T> void register(Class<T> service, T instance) {
    Locator.register(service, instance, true);
  }

  public static <T> void register(Class<T> service, Class<? extends T> instance, boolean override) {
    if (!override && services.containsKey(service)) return;
    // OK set the service
    try {
      Object i = instance.getConstructor().newInstance();
      services.put(service, i);
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to create service instance!");
    }
  }

  public static <T> void register(Class<T> service, T instance, boolean override) {
    if (!override && services.containsKey(service)) return;
    // OK set the service
    services.put(service, instance);
  }

  public static <T> T get(Class<T> service) {
    return service.cast(services.getOrDefault(service, null));
  }
}
