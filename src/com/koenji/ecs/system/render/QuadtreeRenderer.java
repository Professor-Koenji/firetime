package com.koenji.ecs.system.render;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.graph.tree.IQuadTree;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.System;
import com.koenji.ecs.wrappers.IGraphicsContext;

public class QuadtreeRenderer extends System {

  private IQuadTree qt;
  private int rgba;
  private IGraphicsContext gc;

  public QuadtreeRenderer(IQuadTree qt, int rgba) {
    this.qt = qt;
    this.rgba = rgba;
    this.gc = Locator.get(IGraphicsContext.class);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    // Draw quadtree
    drawNode(qt, 0, 0, gc.getWidth(), gc.getHeight());
  }

  public void drawNode(IQuadTree node, float x, float y, float w, float h) {
    IQuadTree[] nodes = node.getNodes();
    if (nodes[0] != null) {
      float w2 = w * .5f;
      float h2 = h * .5f;
      drawNode(nodes[0], x, y, w2, h2);
      drawNode(nodes[1], x + w2, y, w2, h2);
      drawNode(nodes[2], x, y + h2, w2, h2);
      drawNode(nodes[3], x + w2, y + h2, w2, h2);
    } else {
      // Actually draw it!
      gc.stroke(rgba);
      gc.noFill();
      gc.strokeWeight(4);
      gc.rect(x, y, w, h);
    }
  }
}
