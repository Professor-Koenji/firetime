package com.koenji.ecs;

import com.koenji.ecs.scene.IScene;

public interface ICore {
  void setClearColour(int rgba);
  void setTitle(String title);
  void setFramerate(int fps);
  Core gc();
  void init();
  void update(int dt);
  void add(IScene scene);
  void remove(IScene scene);
}
