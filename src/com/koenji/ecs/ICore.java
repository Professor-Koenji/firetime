package com.koenji.ecs;

import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.events.IKeyRelease;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
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
  void subscribe(InputEventType type, IKeyPress o);
  void subscribe(InputEventType type, IKeyRelease o);
  void subscribe(InputEventType type, IMousePress o);
  void subscribe(InputEventType type, IMouseRelease o);
}
