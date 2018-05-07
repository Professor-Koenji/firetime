package com.koenji.firetime.systems;

import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.physics.ConvexCollider;
import processing.core.PVector;

public class ExtendedCollider extends ConvexCollider {

  public ExtendedCollider() {
    super();
  }

  protected void doCollision(IEntity a, IEntity b, PVector mtv, float minOverlap, Position pA, Position pB, ConvexBody bA, ConvexBody bB) {

    //
    super.doCollision(a, b, mtv, minOverlap, pA, pB, bA, bB);

  }

}
