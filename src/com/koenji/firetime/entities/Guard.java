package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.CircleBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.scene.IScene;

import java.util.List;

public class Guard extends Entity {

  private List<INode> route;
  private int waypointIndex;

  public Guard(List<INode> route) {
    this.route = route;
    this.waypointIndex = 0;
  }

  @Override
  public void added(IScene scene, IEventController eventController) {
    super.added(scene, eventController);
    //
    addComponents(
      new Position(route.get(waypointIndex).getX(), route.get(waypointIndex).getY()),
      new Velocity(),
      new CircleBody(32),
      new RenderCircle(32, 0xFFFF00FF)
    );
  }
}
