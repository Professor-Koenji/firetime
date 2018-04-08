package com.koenji.ecs.service;

import java.util.HashMap;
import java.util.Map;

/**
 * A service locator for use in registering some common
 * utlity and service classes.
 *
 * @author Chris Williams
 * @version 1.0
 */
public class Locator {

  private static Map<Class<?>, Object> services = new HashMap<>();

  /**
   * Register a new service for global availability via the Locator.
   * @param service The class of the service being provided.
   * @param instance A concrete instance of a superclass of the service class.
   * @param <T> Any type.
   */
  public static <T> void register(Class<T> service, Class<? extends T> instance) {
    Locator.register(service, instance, true);
  }

  /**
   * Register a new service for global availability via the Locator.
   * @param service The class of the service being provided.
   * @param instance A concrete instance of an instance of the service class.
   * @param <T> Any type.
   */
  public static <T> void register(Class<T> service, T instance) {
    Locator.register(service, instance, true);
  }

  /**
   * Register a new service for global availability via the Locator.
   * @param service The class of the service being provided.
   * @param instance A concrete instance of a superclass of the service class.
   * @param override Whether an override should occur if a service has already been declared.
   * @param <T> Any type.
   */
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

  /**
   * Register a new service for global availability via the Locator.
   * @param service The class of the service being provided.
   * @param instance A concrete instance of the service class.
   * @param override Whether an override should occur if a service has already been declared.
   * @param <T> Any type.
   */
  public static <T> void register(Class<T> service, T instance, boolean override) {
    if (!override && services.containsKey(service)) return;
    // OK set the service
    services.put(service, instance);
  }

  /**
   * Returns a registered instance of the provided service class
   * @param service A class of which type you want to obtain
   * @param <T> Any type
   * @return An instance of the type if it exists, else null
   */
  public static <T> T get(Class<T> service) {
    return service.cast(services.getOrDefault(service, null));
  }
}
