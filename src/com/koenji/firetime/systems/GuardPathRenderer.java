package com.koenji.firetime.systems;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.System;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.components.GuardState;
import com.koenji.firetime.entities.Guard;
import com.koenji.firetime.states.IState;
import com.koenji.firetime.states.guard.Patrolling;
import javafx.geometry.Pos;
import processing.core.PVector;

public class GuardPathRenderer extends BasicRenderer {

  public GuardPathRenderer(PVector offset) {
    super(offset);
  }

  @Override
  public void entityAdded(IEntity entity) {
    if (entity instanceof Guard) {
      entities.add(entity);
    }
  }

  @Override
  public void update(int dt) {
    pushCamera();
    for (IEntity g : entities) {
      Position p = g.getComponent(Position.class);
      GuardState gs = g.getComponent(GuardState.class);
      IState state = gs != null ? gs.currentState() : null;
      if (state != null && state instanceof Patrolling) {
        Patrolling patrol = (Patrolling) gs.currentState();
        INode nextNode = patrol.route.get(patrol.waypointIndex);
        // Pos -> nextNode
        gc.strokeWeight(16);
        gc.stroke(0xFF3399FF);
        gc.line(p.x, p.y, nextNode.getX(), nextNode.getY());
        for (int i = 0; i < 5; ++i) {
          INode n1 = patrol.route.get((patrol.waypointIndex + i) % patrol.route.size());
          INode n2 = patrol.route.get((patrol.waypointIndex + i + 1) % patrol.route.size());
          int alpha = 0xFF000000 - (0x30000000 * i);
          gc.stroke(alpha | 0x003399FF);
          gc.line(n1.getX(), n1.getY(), n2.getX(), n2.getY());
        }
      }
    }
    popCamera();
  }
}
