package com.koenji.ecs.graph.tree;

import java.util.List;

public interface IQuadTree {
  void clear();
  <T extends IRect> void insert(T rect);
  <T extends IRect> List<T> retrieve(T rect);
}