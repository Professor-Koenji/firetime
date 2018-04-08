package com.koenji.firetime.scenes;

import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.scene.example.CollisionDetection;
import com.koenji.ecs.scene.example.PathFinding;
import com.koenji.ecs.scene.example.SimplePhysics;
import com.koenji.ecs.scene.example.SimplePhysicsQT;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IRootScene;
import javafx.event.Event;

public class Menu extends Scene {

  private IRootScene rootScene;

  private IScene activeScene;

  private BasicRenderer renderer;

  @Override
  public void added() {
    super.added();
    //
    rootScene = Locator.get(IRootScene.class);
    renderer = new BasicRenderer();
    //
    active();
    //
    addEventHandler(InputEvents.KEY_PRESSED, this::keyPressed);
  }

  private void active() {
    if (activeScene != null) rootScene.remove(activeScene);
    activeScene = null;
    add(renderer);
  }

  private void activate(IScene scene) {
    // Remove active scene
    active();
    activeScene = scene;
    remove(renderer);
    rootScene.add(activeScene);
  }

  private void keyPressed(KeyEvent e) {
    switch (e.keyCode()) {
      case 8: // Backspace
        active();
        break;
      case 49:
        // Simple Physics
        activate(new SimplePhysics());
        break;
      case 50:
        // Simple Physics QT
        activate(new SimplePhysicsQT());
        break;
      case 51:
        activate(new CollisionDetection());
        break;
      case 52:
        activate(new PathFinding());
        break;
    }
  }
}
