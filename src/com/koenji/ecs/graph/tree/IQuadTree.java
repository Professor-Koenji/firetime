package com.koenji.ecs.graph.tree;

import java.util.List;

/**
 * Generic quad-tree implementation based upon a spatial 2D rectangular system
 * with recursively linked QuadTree nodes.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */
public interface IQuadTree {
  /**
   * Clear's the quadtree
   */
  void clear();

  /**
   * Insert a rectangular area into the quad-tree.
   * @param rect The rectangular area to insert
   * @param <T> A type that implements IRect functionality.
   */
  <T extends IRect> void insert(T rect);

  /**
   * Retrieves a list of rectangular areas within the same quad as the given rect.
   * @param rect The rectangular area to match against.
   * @param <T> Any type that implements IRect functionality.
   * @return A list of matched IRect superclasses.
   */
  <T extends IRect> List<T> retrieve(T rect);

  /**
   * @return The raw QuadTree nodes this branch contains.
   */
  IQuadTree[] getNodes();
}
