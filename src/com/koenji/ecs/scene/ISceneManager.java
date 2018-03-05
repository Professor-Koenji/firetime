package com.koenji.ecs.scene;

public interface ISceneManager {
  void add(IScene scene);
  void remove(IScene scene);
  void clear();
  void update(int dt);
  int count();
}
