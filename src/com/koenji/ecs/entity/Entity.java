package com.koenji.ecs.entity;

import com.koenji.ecs.component.IComponent;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.HashMap;
import java.util.Map;

/**
 * Entity defines the abstract behaviour of the implementation of an IEntity&amp;IEventController within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public abstract class Entity implements IEntity, IEventController {
  // DECLARE an IScene field to store current scene
  protected IScene scene;
  // DECLARE an IEventController implementation
  protected IEventController eventController;
  // DECLARE a Map to store IComponents
  private Map<Class<? extends IComponent>, IComponent> components;

  /**
   * Constructor: creates components Map field
   */
  public Entity() {
    components = new HashMap<>();
  }

  /**
   * Method: called once the Entity has been added to the scene which is passed
   * @param scene           - the current scene added
   * @param eventController - the EventController class bound
   */
  public void added(IScene scene, IEventController eventController) {
    this.scene = scene;
    this.eventController = eventController;
  }

  /**
   * Method: called one the Entity is removed from the scene&amp;systems, used for clean-up
   */
  public void removed() {}

  /**
   * Method: called every frame with the delta time
   * @param dt - int of the delta time
   */
  public void update(int dt) { }

  /**
   * Method: used to add IComponents to Map
   * @param c - IComponent concrete instance
   * @return  - this implementation of IEntity
   */
  public IEntity addComponent(IComponent c) {
    components.put(c.getClass(), c);
    if (scene != null) scene.modifiedEntity(this);
    return this;
  }

  /**
   * Method: used to add numerous IComponents
   * @param cs - IComponent concrete instances
   * @return   - this implementation of IEntity
   */
  public IEntity addComponents(IComponent... cs) {
    for (IComponent c : cs) addComponent(c);
    return this;
  }

  /**
   * Method: used to remove IComponents from the Map, notifies scene its been modified if set
   * @param c - IComponent class to remove
   * @return  - this implementation of IEntity
   */
  public IEntity removeComponent(Class<? extends IComponent> c) {
    components.remove(c);
    if (scene != null) scene.modifiedEntity(this);
    return this;
  }

  /**
   * Method: used to return IComponent in Map
   * @param c - IComponent class to retrieve
   * @return  - IComponent requested
   */
  @SuppressWarnings("unchecked")
  public <T extends IComponent> T getComponent(Class<T> c) {
    return (T) components.get(c);
  }

  /**
   * Method: used to check if implementation has IComponent(s)
   * @param cs - IComponent class(es) to verify
   * @return   - boolean flag
   */
  @SafeVarargs
  public final boolean hasComponents(Class<? extends IComponent> ...cs) {
    for (Class<? extends IComponent> c : cs) {
      if (!components.containsKey(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method: GETTER to return the scene field
   * @return  - IScene current value of scene
   */
  public IScene getScene() {
    return scene;
  }

  /**
   * Method: used to fire an event via the eventController
   * @param type  - type of event
   */
  @Override
  public void fireEvent(Event type) {
    eventController.fireEvent(type);
  }

  /**
   * Method: used to fire an event via the eventController, and boolean value on whether to propagate
   * @param type      - type of event
   * @param propagate - flag to propagate
   */
  @Override
  public void fireEvent(Event type, boolean propagate) {
    eventController.fireEvent(type, propagate);
  }

  /**
   * Method: used to add an EventHandler to the eventController
   * @param type    - type of event
   * @param handler - handler of the event
   */
  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    eventController.addEventHandler(type, handler);
  }

  /**
   * Method: used to remove an EventType from the eventController
   * @param type - type of event
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type) {
    eventController.removeEventHandler(type);
  }

  /**
   * Method: used to remove an EventType from the eventController and option to do so globally on eventController
   * @param type    - type of event
   * @param global  - flag to delete gloabally
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type, boolean global) {
    eventController.removeEventHandler(type, global);
  }

  /**
   * Method: used to remove all EventHandlers from eventController
   */
  @Override
  public void removeAllEventHandlers() {
    eventController.removeAllEventHandlers();
  }

  /**
   * Method: used to remove all EventHandlers from eventController with the option to do so globally
   * @param global
   */
  @Override
  public void removeAllEventHandlers(boolean global) {
    eventController.removeAllEventHandlers(global);
  }
}
