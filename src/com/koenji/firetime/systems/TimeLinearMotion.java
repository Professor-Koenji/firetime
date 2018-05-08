package com.koenji.firetime.systems;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.System;
import com.koenji.firetime.entities.Player;
import processing.core.PVector;

/**
 * LinearMotion system that powers the motion of objects with
 * Position, Velocity and/or Acceleration components.
 *
 * Composes with the collision systems for complex physics response
 * and locomotion from simple rules.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.1
 */
public class TimeLinearMotion extends System {

  private Player player;

  public TimeLinearMotion(Player p) {
    this.player = p;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update(int dt) {
    super.update(dt);
    //
    float speedRate = Player.SpeedRate();
    //
    for (IEntity e : entities) {
      // Velocity&amp;Friction
      if (e.hasComponents(Velocity.class, Friction.class)) {
        Velocity v = e.getComponent(Velocity.class);
        Friction f = e.getComponent(Friction.class);
        PVector iF = new PVector(1 - f.x, 1 - f.y);
        iF.mult(speedRate);
        v.x *= 1 - iF.x;
        v.y *= 1 - iF.y;
      }

      // Velocity&amp;Acceleration
      if (e.hasComponents(Velocity.class, Acceleration.class)) {
        Velocity v = e.getComponent(Velocity.class);
        Acceleration a = e.getComponent(Acceleration.class);
        v.add(PVector.mult(a, speedRate));
      }

      // Position&amp;Velocity
      if (e.hasComponents(Position.class, Velocity.class)) {
        Position p = e.getComponent(Position.class);
        Velocity v = e.getComponent(Velocity.class);
        p.add(PVector.mult(v, speedRate));
      }

      // Reset acceleration
      if (e.hasComponents(Acceleration.class)) {
        Acceleration a = e.getComponent(Acceleration.class);
        if (e.hasComponents(Gravity.class)) {
          Gravity g = e.getComponent(Gravity.class);
          a.set(PVector.mult(g, speedRate));
        } else {
          a.set(0, 0);
        }
      }

      // Angular rotation
      if (e.hasComponents(Rotation.class, AngularVelocity.class)) {
        Rotation r = e.getComponent(Rotation.class);
        AngularVelocity av = e.getComponent(AngularVelocity.class);
        r.angle += av.velocity;
      }

      // Bounding box stuff
      if (e.hasComponents(Position.class, Velocity.class, BoundingBox.class)) {
        Position p = e.getComponent(Position.class);
        Velocity v = e.getComponent(Velocity.class);
        BoundingBox b = e.getComponent(BoundingBox.class);

        if (p.x < b.position.x) {
          switch(b.type) {
            case BoundingBox.REFLECT:
              p.x = b.position.x;
              v.x *= -1;
              break;
            case BoundingBox.WRAP:
              p.x = b.position.x + b.size.x;
              break;
          }
        } else if (p.x > b.position.x + b.size.x) {
          switch(b.type) {
            case BoundingBox.REFLECT:
              p.x = b.position.x + b.size.x;
              v.x *= -1;
              break;
            case BoundingBox.WRAP:
              p.x = b.position.x;
              break;
          }
        }

        if (p.y < b.position.y) {
          switch(b.type) {
            case BoundingBox.REFLECT:
              p.y = b.position.y;
              v.y *= -1;
              break;
            case BoundingBox.WRAP:
              p.y = b.position.y + b.size.y;
              break;
          }
        } else if (p.y > b.position.y + b.size.y) {
          switch(b.type) {
            case BoundingBox.REFLECT:
              p.y = b.position.y + b.size.y;
              v.y *= -1;
              break;
            case BoundingBox.WRAP:
              p.y = b.position.y;
              break;
          }
        }
      }
    }
  }
}
