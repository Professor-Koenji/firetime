package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.CircleBody;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.components.GuardState;
import com.koenji.firetime.events.EmitBulletEvent;
import com.koenji.firetime.states.guard.Patrolling;
import processing.core.PVector;

import java.util.List;

public class Guard extends Entity {

  private List<INode> route;

  public Guard(List<INode> route) {
    this.route = route;
  }

  @Override
  public void added(IScene scene, IEventController eventController) {
    super.added(scene, eventController);
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
}
