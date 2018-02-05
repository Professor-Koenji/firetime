package com.koenji.ecs;

import com.koenji.ecs.events.*;
import com.koenji.ecs.input.InputEventType;
import com.koenji.ecs.scene.IScene;

public interface ICore {
  void setClearColour(int rgba);
  void setTitle(String title);
  void setFramerate(int fps);
  int getWidth();
  int getHeight();
  Core gc();
  void init();
  void update(int dt);
  void add(IScene scene);
  void remove(IScene scene);
  <T extends IObserver> void subscribe(Class<T> type, T instance);
}
