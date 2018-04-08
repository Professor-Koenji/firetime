package com.koenji.ecs.entity;

/**
 * IEntityManager defines the behaviour of an implementation of managing IEntities within the system
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */

public interface IEntityManager {
  /**
   * Method: used to add an IEntity
   * @param entity - IEntity to add
   */
  void add(IEntity entity);

  /**
   * Method: used to remove an IEntity
   * @param entity - IEntity to remove
   */
  void remove(IEntity entity);

  /**
   * Method: used to remove all IEntities
   */
  void clear();

  /**
   * Method: called each frame with the delta time
   * @param dt - int of the delta time
   */
  void update(int dt);

  /**
   * Method: used to iterate through all IEntities
   * @return - Iterable of IEntity
   */
  Iterable<IEntity> iterable();

  /**
   * Method: used to return the number of IEntities
   * @return - int of the IEntities
   */
  int count();
}
