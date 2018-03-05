package com.koenji.firetime.scene;

import com.koenji.ecs.event.bus.IEventBus;
import com.koenji.ecs.event.observer.IMousePress;
import com.koenji.ecs.scene.Scene;
import processing.event.MouseEvent;

public class Menu extends Scene implements IMousePress {

  @Override
  public void removed() {
    super.removed();
    //
    core.unsubscribeAll(this);
  }

  @Override
  public void mousePress(MouseEvent event) {
    core.remove(this);
    GamePrototype gp = new GamePrototype();
    core.add(gp);
  }
}
