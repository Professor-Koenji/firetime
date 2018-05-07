package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * EntityGroup defines the behaviour of a number of IEntities,
 * an implementation of an IEntityGroup interface within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class EntityGroup implements IEntityGroup {
  //DECLARE a List of IEntities
  private List<IEntity> entities;

  /**
   * Constructor: used to create entites List
   */
  public EntityGroup() {
    entities = new ArrayList<>();
  }

  /**
   * Method: used to add IEntity to the entites List
   * @param e - IEntity to add
   */
  public void add(IEntity e) {
    entities.add(e);
  }

  /**
   * Method: used to remove IEntity from entities List
   * @param e - IEntity to remove
   */
  public void remove(IEntity e) {
    entities.remove(e);
  }

  /**
   * Method: used to empty entities List
   */
  public void clear() {
    entities.clear();
  }

  /**
   * Method: used to retrieve the size of entities List
   * @return - int size of entities field
   */
  public int count() {
    return entities.size();
  }

  /**
   * Method: used to retrieve the entities List as an Iterable
   * @return - Iterable of entities field
   */
  public Iterable<IEntity> iterable() {
    return entities;
  }

  /**
   * Method: used to add an IComponent to IEntities in entities field
   * @param c - IComponent to add
   */
  public void addComponent(IComponent c) {
    for (IEntity e : entities) {
      e.addComponent(c);
    }
  }

  /**
   * Method: used to add IComponents to IEntities in entities field
   * @param cs - IComponents to add
   */
  public void addComponents(IComponent... cs) {
    for (IEntity e : entities) {
      e.addComponents(cs);
    }
  }

  /**
   * Method: used to remove IComponent from add IEntities in entities field
   * @param c - IComponent to remove
   */
  public void removeComponent(Class<? extends IComponent> c) {
    for (IEntity e : entities) {
      e.removeComponent(c);
    }
  }
}
