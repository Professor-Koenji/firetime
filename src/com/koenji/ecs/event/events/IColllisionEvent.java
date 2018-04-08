package com.koenji.ecs.event.events;

import com.koenji.ecs.entity.IEntity;

/**
 * ICollisionEvent interface defines the behaviour of a collision Event within the system
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */
public interface IColllisionEvent {

  /**
   * The first colliding entity.
   * @return One of the colliding entities.
   */
  IEntity a();

  /**
   * The second colliding entity.
   * @return The other colliding entity.
   */
  IEntity b();

}
