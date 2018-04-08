package com.koenji.ecs.system.render;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.graph.tree.IQuadTree;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.System;
import com.koenji.ecs.wrappers.IGraphicsContext;

/**
 * A debug renderer that can visualise a given QuadTree. The QuadTree
 * should also be passed into a Collider system, and this system will
 * simply observe the quadtree structure and draw a simple visualisation
 *
 * @author Brad Davies
 * @version 1.0
 */
public class QuadtreeRenderer extends System {

  private IQuadTree qt;
  private int rgba;
  private IGraphicsContext gc;

  /**
   * Creates a QuadTree renderer from the given QuadTree, and draws the
   * visualisation in the provided colour.
   * @param qt The QuadTree to visualise.
   * @param rgba The colour of the grid lines.
   */
  public QuadtreeRenderer(IQuadTree qt, int rgba) {
    this.qt = qt;
    this.rgba = rgba;
    this.gc = Locator.get(IGraphicsContext.class);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    // Draw quadtree (recursively because it's cool).
    drawNode(qt, 0, 0, gc.getWidth(), gc.getHeight());
  }

  /**
   * Draws the node and all children recursively.
   * @param node The current node
   * @param x The current x position
   * @param y The current y position
   * @param w The current node width
   * @param h The current node height
   */
  private void drawNode(IQuadTree node, float x, float y, float w, float h) {
    IQuadTree[] nodes = node.getNodes();
    // If this nodelist has children
    if (nodes[0] != null) {
      // Split the width and height into halves
      float w2 = w * .5f;
      float h2 = h * .5f;
      // Draw the 4 child nodes with the given data
      for (int i = 0; i < 4; ++i) {
        drawNode(nodes[i], x + (i % 2 == 0 ? 0 : w2), y + (i > 1 ? h2 : 0), w2, h2);
      }
    } else {
      // Actually draw it!
      gc.stroke(rgba);
      gc.noFill();
      gc.strokeWeight(4);
      gc.rect(x, y, w, h);
    }
  }
}
