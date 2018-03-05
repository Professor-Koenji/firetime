package com.koenji.ecs.scene;

import com.koenji.ecs.ICore;
import com.koenji.ecs.event.bus.EventBus;
import com.koenji.ecs.event.bus.IEventBus;

import java.util.ArrayList;
import java.util.List;

public class SceneManager implements ISceneManager {

  private ICore core;

  private IEventBus eventBus;

  private List<IScene> toAdd;
  private List<IScene> toRemove;
  private List<IScene> scenes;

  public SceneManager(ICore core) {
    this.core = core;

    eventBus = new EventBus();

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
      scene.removed();
    }
    // Add new scenes
    scenes.addAll(toAdd);
    for (IScene scene : toAdd) {
      scene.added(core, eventBus);
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
