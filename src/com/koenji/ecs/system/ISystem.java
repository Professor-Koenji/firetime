package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.scene.IScene;

public interface ISystem {
  void added(IScene scene);
  void removed();
  void update(Iterable<IEntity> entities, int dt);
}
