package com.koenji.firetime.scenes;

import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.level.LevelObject;

import java.util.List;

public class Level extends Scene {

  private IGraphicsContext gc;

  private LevelObject levelObject;

  public Level(LevelObject levelObject) {
    this.levelObject = levelObject;
    this.gc = Locator.get(IGraphicsContext.class);

    this.levelObject.setup();
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    // Draw le nodes
    gc.stroke(0xFFFFFFFF);
    gc.strokeWeight(3);
    for (int i = 0; i < levelObject.nodes.size(); ++i) {
      INode n = levelObject.nodes.get(i);
      List<INode> ns = n.getNeighbours();
      for (INode n2 : ns) {
        gc.line(n.getX(), n.getY(), n2.getX(), n2.getY());
      }
    }
    for (INode n : levelObject.nodes) {
      gc.fill(0xFFFF0000);
      gc.rect(n.getX() - 8, n.getY() - 8, 16, 16);
    }
    //
    gc.fill(0xFFFFFFFF);
    gc.textSize(14);
    gc.text("Entities: " + entityCount(), 20, 20);
  }
}
