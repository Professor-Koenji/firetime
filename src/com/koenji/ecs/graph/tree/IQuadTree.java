package com.koenji.ecs.graph.tree;

import com.koenji.ecs.entity.IEntity;

import java.util.List;

public interface IQuadTree {
  void clear();
  void insert(IRect rect);
  List<IRect> retrieve(IRect rect);
}
