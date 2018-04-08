package com.koenji.ecs.wrappers;

import com.koenji.ecs.scene.IScene;

public interface IRootScene {
  void add(IScene scene);
  void remove(IScene scene);
}
