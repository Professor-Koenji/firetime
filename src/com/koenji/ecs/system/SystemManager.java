package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all systems within a Scene.
 * This encapsulates serving to control the list of the active systems, and also
 * to serve as an event controller for these systems.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */
public class SystemManager implements ISystemManager {

  private IScene scene; // The root scene (from this Systems POV).
  private IEventController eventController; // An event controller
  private IEntityManager entityManager; // The entity manager paired with this SystemManager

  private List<ISystem> toAdd;
  private List<ISystem> toRemove;
  private List<ISystem> systems;

  /**
   * Creates a new SystemManager, with the given Scene and EntityManager.
   * @param scene The scene that this SystemManager is serving on behalf of.
   * @param entityManager The EntityManager that this System will interact with.
   */
  public SystemManager(Scene scene, IEntityManager entityManager) {
    this.scene = scene;
    this.eventController = scene;
    this.entityManager = entityManager;

    toAdd = new ArrayList<>();
    toRemove = new ArrayList<>();
    systems = new ArrayList<>();
  }

  /**
   * Add a System to the manager.
   * @param system The system to add into this manager.
   */
  public void add(ISystem system) {
    toAdd.add(system);
  }

  /**
   * Remove a System from the manager.
   * @param system The system to remove from this manager.
   */
  public void remove(ISystem system) {
    toRemove.add(system);
  }

  /**
   * Removes all System's from the manager.
   * This includes any pending System's to be added.
   */
  public void clear() {
    toAdd.clear();
    toRemove.clear();
    systems.clear();
  }

  public void update(int dt) {
    // Remove any systems first
    systems.removeAll(toRemove);
    for (ISystem system : toRemove) {
      system.removed();
    }
    // Add new scenes
    systems.addAll(toAdd);
    for (ISystem system : toAdd) {
      system.added(scene, eventController, entityManager);
    }
    // Empty modifier lists
    toAdd.clear();
    toRemove.clear();
    // Update all current scenes
    for (ISystem system : systems) {
      // Update system
      system.update(dt);
    }
  }

  /**
   * @return The number of System's within this manager.
   */
  public int count() {
    return systems.size();
  }

  /**
   * A callback for when a Entity is added to the Scene.
   * Systems can optionally filter this Entity at this point,
   * if the System-dependant filtering code is computationally
   * expensive to perform every frame.
   * @param entity The new Entity.
   */
  @Override
  public void entityAdded(IEntity entity) {
    for (ISystem system : systems) {
      system.entityAdded(entity);
    }
  }

  /**
   * A callback for when a Entity is removed from the Scene.
   * Should almost always just remove the Entity from every
   * System, but in exceptional circumstances you may wish
   * to retain a removed Entity within a System.
   * @param entity The removed Entity.
   */
  @Override
  public void entityRemoved(IEntity entity) {
    for (ISystem system : systems) {
      system.entityRemoved(entity);
    }
  }
}
