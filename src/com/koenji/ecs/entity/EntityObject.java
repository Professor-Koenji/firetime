package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;

/**
 * EntityObject defines the behaviour to create an Entity with a given amount of IComponents,
 * an implementation of an Entity within the system
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public final class EntityObject extends Entity {

  /**
   * Method: static call returns an IEntity with given IComponents
   * @param cs  - list of IComponents to add
   * @return    - IEntity with IComponents added
   */
  @SuppressWarnings("unchecked")
  public static <T extends IComponent> IEntity create(T ...cs) {
    return new EntityObject().addComponents(cs);
  }
}
