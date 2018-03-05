package com.koenji.ecs.scene;

import com.koenji.ecs.event.bus.IEventBus;

public interface ISceneManager {
  void add(IScene scene);
  void remove(IScene scene);
  void clear();
  void update(int dt);
  int count();
}
