package com.koenji.ecs.scene;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.system.ISystem;

/**
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.2
 */
public interface IScene {
  /**
   * Called when the Scene is added to the root scene
   */
  void added();

  /**
   * Called when the scene is removed from the root scene
   */
  void removed();

  /**
   * Called every update cycle.
   * @param dt The passed time since the last frame (in ms).
   */
  void update(int dt);

  /**
   * Adds the given entity to the scene.
   * @param entity The entity to add.
   */
  void add(IEntity entity);

  /**
   * Adds a given EntityGroup to the scene.
   * @param entityGroup The entity group to add.
   */
  void add(IEntityGroup entityGroup);

  /**
   * Adds a given System to the scene.
   * @param system The System to add.
   */
  void add(ISystem system);

  /**
   * Removes an entity from the scene.
   * @param entity The entity to remove.
   */
  void remove(IEntity entity);

  /**
   * Removes an entity group from a scene.
   * @param entityGroup The entity group to remove.
   */
  void remove(IEntityGroup entityGroup);

  /**
   * Removes a system from the scene.
   * @param system The system to remove.
   */
  void remove(ISystem system);

  /**
   * Removes every entity from the scene.
   */
  void removeAllEntities();

  /**
   * Removes every system from the scene.
   */
  void removeAllSystems();

  /**
   * Removes everything (Entities&amp;Systems) from the scene.
   */
  void removeAll();

  /**
   * Called whenever an Entity has been modified
   * @param entity The modified entity
   */
  void modifiedEntity(IEntity entity);

  /**
   * @return The number of entities within the scene.
   */
  int entityCount();

  /**
   * @return The number of systems within the scene.
   */
  int systemCount();
}
