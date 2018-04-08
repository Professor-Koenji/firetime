package com.koenji.ecs.system;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.ArrayList;
import java.util.List;

/**
 * A System (The 'S' in ECS) that operates upon Entities within
 * individual scenes. Systems encapsulate & dictate the behaviour
 * of such Entities, often through progressive and hierarchical
 * modelling of simple behaviours into more complex wholes.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.1
 */
public abstract class System implements ISystem, IEventController {

  protected IScene scene;
  protected IEventController eventController;
  protected List<IEntity> entities;

  /**
   * Creates a new System.
   */
  public System() {
    entities = new ArrayList<>();
  }

  /**
   * Called when this System has actually been added into the Scene.
   * @param scene The Scene this system has been added to.
   * @param eventController The EventController API this System can now use.
   */
  public void added(IScene scene, IEventController eventController) {
    this.scene = scene;
    this.eventController = eventController;
  }

  /**
   * Called when this System has actually been added into the Scene.
   * @param scene The Scene this system has been added to.
   * @param eventController The EventController API this System can now use.
   * @param entityManager The EntityManager this System can now access.
   */
  public void added(IScene scene, IEventController eventController, IEntityManager entityManager) {
    added(scene, eventController);
    //
    for (IEntity e : entityManager.iterable()) {
      entityAdded(e);
    }
  }

  /**
   * Called whenever the System has been removed from it's Scene.
   */
  public void removed() {}

  /**
   * Called every frame. This is where the majority of System behaviour should be defined.
   * @param dt The delta time from the last frame (in ms).
   */
  public void update(int dt) {}

  /**
   * Called whenever a Entity has been added to the Scene.
   * By default, the Entity will be added to the Systems own internal
   * entity list, however you can optionally filter out the Entity if
   * it does not pass a component filter.
   * @param entity The Entity that has been added (or modified).
   */
  public void entityAdded(IEntity entity) {
    entities.add(entity);
  }

  /**
   * Called whenever a Entity has been removed from the Scene.
   * @param entity The Entity that has been removed.
   */
  final public void entityRemoved(IEntity entity) {
    entities.remove(entity);
  }

  /**
   * A System can act as an event dispatcher.
   * @param type The type of event to dispatch.
   */
  @Override
  public void fireEvent(Event type) {
    eventController.fireEvent(type);
  }

  /**
   * A System can act as an event dispatcher.
   * @param type The type of event to dispatch.
   * @param propagate Whether to propagate this event outside this Scene.
   */
  @Override
  public void fireEvent(Event type, boolean propagate) {
    eventController.fireEvent(type, propagate);
  }

  /**
   * A System can act as an event listener.
   * @param type The type of Event to listen to.
   * @param handler What to do when the event occurs.
   * @param <T> An Event or superclass.
   */
  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    eventController.addEventHandler(type, handler);
  }

  /**
   * Remove an event handle on a given event type.
   * @param type The type of event.
   * @param <T> An Event or superclass.
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type) {
    eventController.removeEventHandler(type);
  }

  /**
   * Remove an event handler globally on a given event type.
   * @param type The event type.
   * @param global Whether to remove it globally.
   * @param <T> An Event or superclass.
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type, boolean global) {
    eventController.removeEventHandler(type, global);
  }

  /**
   * Removes all event handlers from this event controller.
   */
  @Override
  public void removeAllEventHandlers() {
    eventController.removeAllEventHandlers();
  }

  /**
   * Removes all event handlers from this event controller.
   * @param global Whether to remove every event handlers on all controllers.
   */
  @Override
  public void removeAllEventHandlers(boolean global) {
    eventController.removeAllEventHandlers(global);
  }
}
