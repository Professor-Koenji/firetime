package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.CircleBody;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.components.GuardState;
import com.koenji.firetime.states.guard.Patrolling;
import com.koenji.ecs.service.Locator;
import com.koenji.firetime.events.EmitBulletEvent;
import processing.core.PVector;

import java.util.List;

public class Guard extends Entity {

  private List<INode> route;

  public Guard(List<INode> route) {
    this.route = route;
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    INode start = route.get(0);
    //
    addComponents(
      new Position(start.getX(), start.getY()),
      new Velocity(),
      new CircleBody(32),
      ConvexBody.polygon(8, 32),
      new RenderCircle(32, 0xFFFF00FF),
      new GuardState(new Patrolling(route), this)
    );
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    Position p = getComponent(Position.class);
    Velocity v = getComponent(Velocity.class);
    //
    if (state == PATROL_STATE) {
      PVector nextNode = getRouteNodeVector(waypointIndex);
      float distance = PVector.dist(p, nextNode);
      if (distance < 10) {
        waypointIndex++;
        if (waypointIndex >= route.size()) waypointIndex = 0;
      }
      PVector newNode = getRouteNodeVector(waypointIndex);
      PVector path = PVector.sub(newNode, p);
      v.add(path.setMag(speed));
    } else if (state == CHASE_STATE) {
      // DO something
      fireDelay -= dt;

      // If fire delay <= 0 -> FIRE THE MISSILES
      if (fireDelay <= 0) {
        float angle = PVector.sub(chasePoint, p).heading();
        PVector angleVec = PVector.fromAngle(angle);
        PVector pos = PVector.add(p, angleVec.setMag(64f));
        IEventBus eb = Locator.get(IEventBus.class);
        eb.fireEvent(new EmitBulletEvent(EmitBulletEvent.EMIT_BULLET, pos.x, pos.y, angle));
        v.add(PVector.sub(chasePoint, p).setMag(speed));

        fireDelay = FIRE_DELAY;
      }
    }
    v.limit(speed * 2);
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
    RenderCircle rc = getComponent(RenderCircle.class);
    if (state == PATROL_STATE) {
      rc.rgba = 0xFFFF00FF;
    }
    else if (state == CHASE_STATE) rc.rgba = 0xFFFF0022;
  }

  private PVector getRouteNodeVector(int index) {
    return new PVector(route.get(index).getX(), route.get(index).getY());
  }
}
