package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;

public final class EntityObject extends Entity {
  @SuppressWarnings("unchecked")
  public static <T extends IComponent> IEntity create(T ...cs) {
    return new EntityObject().addComponents(cs);
  }
}
