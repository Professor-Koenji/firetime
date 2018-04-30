package com.koenji.ecs.scene;

import java.util.ArrayList;
import java.util.List;

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
public class SceneManager implements ISceneManager {

  private List<IScene> toAdd;
  private List<IScene> toRemove;
  private List<IScene> scenes;

  /**
   * Sets up the internal state of the SceneManager, ready for usage.
   */
  public SceneManager() {

    toAdd = new ArrayList<>();
    toRemove = new ArrayList<>();
    scenes = new ArrayList<>();
  }

  /**
   * Adds the given scene to the root scenelist.
   * @param scene The scene to add.
   */
  public void add(IScene scene) {
    toAdd.add(scene);
  }

  /**
   * Removes the given scene from the root scenelist.
   * @param scene The scene to remove.
   */
  public void remove(IScene scene) {
    toRemove.add(scene);
  }

  /**
   * Removes all scenes from the scenelist.
   * Includes any scenes that are to be added in the next update cycle.
   */
  public void clear() {
    toAdd.clear();
    toRemove.clear();
    scenes.clear();
  }

  /**
   * Updates every scene within the root scenelist.
   * @param dt The time since the last frame (in ms).
   */
  public void update(int dt) {
    // Remove any scenes first
    for (IScene scene : toRemove) {
      scenes.remove(scene);
      scene.removed();
    }
    // Add new scenes
    scenes.addAll(toAdd);
    for (IScene scene : toAdd) {
      scene.added();
    }
    // Empty modifier lists
    toAdd.clear();
    toRemove.clear();
    // Update all current scenes
    for (IScene scene : scenes) {
      scene.update(dt);
    }
  }

  /**
   * @return The number of scenes within the root scenelist.
   */
  public int count() {
    return scenes.size();
  }
}
