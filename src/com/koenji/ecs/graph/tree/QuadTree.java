package com.koenji.ecs.graph.tree;

import java.util.ArrayList;
import java.util.List;

public class QuadTree implements IQuadTree {

  private int maxObjects;
  private int maxDepth;

  private int depth;
  private List<IRect> objects;
  private IRect bounds;
  private QuadTree[] nodes;

  public QuadTree(IRect bounds, int maxObjects, int maxDepth) {
    this(bounds, maxObjects, maxDepth, 0);
  }

  private QuadTree(IRect bounds, int maxObjects, int maxDepth, int depth) {
    this.bounds = bounds;
    this.maxObjects = maxObjects;
    this.maxDepth = maxDepth;
    this.depth = depth;

    objects = new ArrayList<>();
    nodes = new QuadTree[4];
  }

  @Override
  public void clear() {
    objects.clear();
    for (QuadTree qt : nodes) {
      if (qt == null) continue;
      qt.clear();
    }
    nodes = new QuadTree[4];
  }

  private void split()  {
    int w = (int) (bounds.getW() * 0.5);
    int h = (int) (bounds.getH() * 0.5);

    for (int i = 0; i < 4; ++i) {
      int x = (int) bounds.getX() + (i % 2 == 0 ? 0 : w);
      int y = (int) bounds.getY() + (i < 2 ? 0 : h);
      nodes[i] = new QuadTree(new Rect(x, y, w, h), maxObjects, maxDepth, this.depth + 1);
    }
  }

  private int getRegion(IRect rect) {
    int midX = (int) bounds.getX() + (int) (bounds.getW() * 0.5);
    int midY = (int) bounds.getY() + (int) (bounds.getH() * 0.5);

    boolean topHalf = (rect.getY() + rect.getH()) < midY;
    boolean bottomHalf = rect.getY() > midY;

    boolean leftHalf = (rect.getX() + rect.getW()) < midX;
    boolean rightHalf = rect.getX() > midX;

    if (topHalf) {
      if (leftHalf) {
        // Top left
        return 0;
      } else if (rightHalf) {
        // Top Right
        return 1;
      }
    } else if (bottomHalf) {
      if (leftHalf) {
        // Bottom left
        return 2;
      } else if (rightHalf) {
        // Bottom right
        return 3;
      }
    }

    return -1;
  }

  @Override
  public <T extends IRect> void insert(T rect) {
    // -1 -> 3
    // -1 is not classified into a single QT
    // 0 = TL, 1 = TR, 2 = BL, 3 = BR
    if (nodes[0] != null) {
      // We have split this QT up, baby.
      int region = getRegion(rect);
      if (region != -1) {
        // A defined region can be used
        nodes[region].insert(rect);
        return;
      }
    }
    // Either;
    // -> QT hasn't split up yet
    // -> QT has split but region == -1
    objects.add(rect);
    // Split if QT is overloaded, and not at max depth.
    if (objects.size() > maxObjects && depth < maxDepth) {
      // If we haven't split this region, then split it
      if (nodes[0] == null) split();
      //
      for (int i = objects.size() - 1; i >= 0; --i) {
        int rRegion = getRegion(objects.get(i));
        if (rRegion != -1) {
          nodes[rRegion].insert(objects.remove(i));
        }
      }
    }
  }

  @Override
  public <T extends IRect> List retrieve(T rect) {
    List<IRect> objects = new ArrayList<>();
    int region = getRegion(rect);
    if (region != -1 && nodes[0] != null) {
      objects.addAll(nodes[region].retrieve(rect));
    }
    objects.addAll(this.objects);
    return objects;
  }

  @Override
  public IQuadTree[] getNodes() {
    return nodes;
  }
}
