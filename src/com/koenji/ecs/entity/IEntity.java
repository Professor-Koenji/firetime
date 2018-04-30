package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.scene.IScene;

/**
 * IEntity defines the behaviour of an implementation of an Entity within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public interface IEntity {
  /**
   * Method: used when the implementation has been created
   * @param scene           - the current scene added
   */
  void added(IScene scene);

  /**
   * Method: used to clean-up
   */
  void removed();

  /**
   * Method: called per frame and passed delta time
   * @param dt - int of the delta time
   */
  void update(int dt);

  /**
   * Method: used to add a component to Entity
   * @param c - IComponent concrete instance
   * @return  - self instance of IEntity
   */
  IEntity addComponent(IComponent c);

  /**
   * Method: used to add multiple components
   * @param cs - IComponent concrete instances
   * @return   - self instance of IEntity
   */
  IEntity addComponents(IComponent ...cs);

  /**
   * Method: used to remove an component from Entity
   * @param c - IComponent class to remove
   * @return  - self instance of IEntity
   */
  IEntity removeComponent(Class<? extends IComponent> c);

  /**
   * Method: GETTER of an IComponent
   * @param c - IComponent class to retrieve
   * @return
   */
  <T extends IComponent> T getComponent(Class<T> c);

  /**
   * Method: Boolean flag to check if Entity has IComponent(s)
   * @param cs - IComponent class(es) to verify
   * @return   - boolean flag
   */
  @SuppressWarnings("unchecked")
  boolean hasComponents(Class<? extends IComponent> ...cs);

  /**
   * Method: GETTER to return the current scene
   * @return - instance of IScene
   */
  IScene getScene();
}
