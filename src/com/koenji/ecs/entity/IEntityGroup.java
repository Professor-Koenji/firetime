package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;

/**
 * IEntityGroup defines the behaviour of an implementation of a number of Entities within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public interface IEntityGroup {
  /**
   * Method: used to add an IEntity
   * @param e - IEntity to add
   */
  void add(IEntity e);

  /**
   * Method: used to remove a specified IEntity
   * @param e - IEntity to remove
   */
  void remove(IEntity e);

  /**
   * Method: used to clear all IEntity/IEntities
   */
  void clear();

  /**
   * Method: return an iterable list of current IEntities in group
   * @return - Iterable list of IEntities
   */
  Iterable<IEntity> iterable();

  /**
   * Method: return the number of IEntities in group
   * @return - int value of IEntities in group
   */
  int count();

  /**
   * Method: add the IComponent to group
   * @param c - IComponent to add
   */
  void addComponent(IComponent c);

  /**
   * Method: add the IComponents to the group
   * @param cs - IComponents to add
   */
  void addComponents(IComponent ...cs);

  /**
   * Method: remove the IComponent from the group
   * @param c - IComponent to remove
   */
  void removeComponent(Class<? extends IComponent> c);
}
