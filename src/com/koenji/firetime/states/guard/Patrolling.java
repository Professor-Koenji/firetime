package com.koenji.firetime.states.guard;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.firetime.states.IStateMachine;
import processing.core.PVector;

import java.util.List;

public class Patrolling extends BaseState {

  public List<INode> route;
  public int waypointIndex;

  public Patrolling(List<INode> route) {
    this(route, 0);
  }

  public Patrolling(List<INode> route, int waypointIndex) {
    this.route = route;
    this.waypointIndex = waypointIndex;
  }

  @Override
  public void update(IStateMachine fsm, int dt, IEntity entity) {
    PVector pos = entity.getComponent(Position.class);
    PVector vel = entity.getComponent(Velocity.class);

    // State action
    PVector nextNode = getRouteNodeVector(waypointIndex);
    float distance = PVector.dist(pos, nextNode);
    if (distance < 10) {
      waypointIndex++;
      if (waypointIndex >= route.size()) waypointIndex = 0;
    }
    PVector newNode = getRouteNodeVector(waypointIndex);
    PVector path = PVector.sub(newNode, pos);
    vel.add(path.normalize());
    vel.limit(2f);

    // Do we need to change state?
    // if player in sight -> combat
    // if heard gunfire -> alerted
    // if shot by player
  }

  private PVector getRouteNodeVector(int index) {
    return new PVector(route.get(index).getX(), route.get(index).getY());
  }
}
