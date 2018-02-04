package com.koenji.ecs.system;

public interface ISystemManager {
  void add(ISystem system);
  void remove(ISystem system);
  void clear();
  void update(int dt);
  int count();
}
