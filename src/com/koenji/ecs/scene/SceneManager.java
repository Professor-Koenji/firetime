package com.koenji.ecs.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneManager implements ISceneManager {

  private List<IScene> toAdd;
  private List<IScene> toRemove;
  private List<IScene> scenes;

  public SceneManager() {

    toAdd = new ArrayList<>();
    toRemove = new ArrayList<>();
    scenes = new ArrayList<>();
  }

  public void add(IScene scene) {
    toAdd.add(scene);
  }

  public void remove(IScene scene) {
    toRemove.add(scene);
  }

  public void clear() {
    toAdd.clear();
    toRemove.clear();
    scenes.clear();
  }

  public void update(int dt) {
    // Remove any scenes first
    for (IScene scene : toRemove) {
      scenes.remove(scene);
      scene.removed(true);
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

  public int count() {
    return scenes.size();
  }
}
