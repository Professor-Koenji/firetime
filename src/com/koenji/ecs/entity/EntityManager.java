package com.koenji.ecs.entity;

import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * EntityManager defines the behaviour of a manager of IEntities,
 * an implementation of an IEntityManager interface within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class EntityManager implements IEntityManager {

  // DECLARE a field to store our scene
  private IScene scene;
  // DECLARE Lists of the IEntity(ies) to add remove and keep
  private List<IEntity> toAdd;
  private List<IEntity> toRemove;
  private List<IEntity> entities;

  /**
   * Constructor: sets the scene&amp;eventController fields with the value passed
   * @param scene - scene we are bound to
   */
  public EntityManager(Scene scene) {
    this.scene = scene;

    toAdd = new ArrayList<>();
    toRemove = new ArrayList<>();
    entities = new ArrayList<>();
  }

  /**
   * Method: IEntity to add to list
   * @param entity - IEntity to add
   */
  public void add(IEntity entity) {
    toAdd.add(entity);
  }

  /**
   * Method: IEntity to remove from the list
   * @param entity - IEntity to remove
   */
  public void remove(IEntity entity) {
    toRemove.add(entity);
  }

  /**
   * Method: used to clear all List fields
   */
  public void clear() {
    toRemove.addAll(entities);
    for (IEntity entity : toRemove) {
      entity.removed();
    }
    entities.clear();
    toAdd.clear();
    toRemove.clear();
  }

  /**
   * Method: called per frame to remove, add and then update IEntities
   * @param dt - int of the delta time
   */
  public void update(int dt) {
    // Remove any entities first
    entities.removeAll(toRemove);
    for (IEntity entity : toRemove) {
      entity.removed();
    }
    // Add new scenes
    entities.addAll(toAdd);
    for (IEntity entity : toAdd) {
      entity.added(scene);
    }
    // Empty modifier lists
    toAdd.clear();
    toRemove.clear();
    // Update all current entities
    for (IEntity entity : entities) {
      entity.update(dt);
    }
  }

  /**
   * Method: GETTER returns the entities List field
   * @return - Iterable List of IEntities
   */
  public Iterable<IEntity> iterable() {
    return entities;
  }

  /**
   * Method: returns the size of the entities List field
   * @return - int of the entities field size
   */
  public int count() {
    return entities.size();
  }
}
