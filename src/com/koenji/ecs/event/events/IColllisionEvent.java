package com.koenji.ecs.event.events;

import com.koenji.ecs.entity.IEntity;

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
