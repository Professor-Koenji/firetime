package com.koenji.ecs.scene;

/**
 * The SceneManager acts as the de-facto RootScene of all scenes. It allows
 * for management of the scenes directly from Core (not advised, except for
 * Core itself), or via the Service Locator pattern which exposes a subset
 * of the SceneManager functionality through the IRootScene interface which
 * ultimately falls through to controlling a ISceneManager instance.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */
public interface ISceneManager {
  /**
   * @param scene The scene to add.
   */
  void add(IScene scene);

  /**
   * @param scene The scene to remove.
   */
  void remove(IScene scene);

  /**
   * Remove all scenes.
   */
  void clear();

  /**
   * @param dt The time since the last frame (in ms).
   */
  void update(int dt);

  /**
   * @return The number of scenes.
   */
  int count();
}
