package com.koenji.ecs.wrappers;

import com.koenji.ecs.scene.IScene;

/**
 * An interface that should sit on-top of the root scene, either a scene manager
 * or the root PApplet instance depending on whichever governs the global scene list.
 */
public interface IRootScene {
  /**
   * Add a scene to the root scene list.
   * @param scene The scene to add.
   */
  void add(IScene scene);

  /**
   * Remove a scene from the root scene list.
   * @param scene The scene to remove.
   */
  void remove(IScene scene);
}
