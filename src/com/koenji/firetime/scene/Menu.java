package com.koenji.firetime.scene;

import com.koenji.ecs.ICore;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.scene.example.ConvexCollisions;
import processing.event.MouseEvent;

public class Menu extends Scene {

  @Override
  public void mousePress(MouseEvent event) {
    core.remove(this);
    GamePrototype gp = new GamePrototype();
    core.add(gp);
  }
}
