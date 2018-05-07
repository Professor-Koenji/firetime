package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;

/**
 * The interface for the SystemManager class.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */
public interface ISystemManager {
  void add(ISystem system);
  void remove(ISystem system);
  void clear();
  void update(int dt);
  int count();
  void entityAdded(IEntity entity);
  void entityRemoved(IEntity entity);
}
