package com.koenji.firetime.scenes;

import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.scene.example.CollisionDetection;
import com.koenji.ecs.scene.example.Pathfinding;
import com.koenji.ecs.scene.example.SimplePhysics;
import com.koenji.ecs.scene.example.SimplePhysicsQT;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRootScene;

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

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);

    int gap = 64;
    gc.textSize(gap);
    gc.fill(0xFF224466);
    gc.text("1. Simple Physics", 32, gap);
    gc.text("2. Simple Physics (QT)", 32, gap * 2);
    gc.text("3. Collision Detection", 32, gap * 3);
    gc.text("4. Path-Finding 1", 32, gap * 4);
    gc.text("5. Path-Finding 2", 32, gap * 5);
    gc.text("6. Path-Finding 3", 32, gap * 6);
    gc.text("7. Game Prototype", 32, gap * 7);
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
        activate(new Pathfinding());
        break;
      case 53:
        activate(new Pathfinding(true));
        break;
      case 54:
        activate(new Pathfinding(true, true));
        break;
      case 55:
        activate(new GamePrototype());
        break;
      default:
        System.out.println(e.keyCode());
    }
  }
}
