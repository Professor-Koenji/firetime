package com.koenji.firetime.scene;

import com.koenji.ecs.ICore;
import com.koenji.ecs.event.bus.IEventBus;
import com.koenji.ecs.event.observer.IMousePress;
import com.koenji.ecs.scene.Scene;
import com.koenji.firetime.event.Events;
import com.koenji.firetime.event.WeaponFireEvent;
import javafx.event.Event;
import processing.event.MouseEvent;

public class Menu extends Scene implements IMousePress {

  @Override
  public void added(ICore core, IEventBus eventBus) {
    super.added(core, eventBus);
    //
    getEventBus().addEventHandler(Events.WEAPON_FIRE, this::callback);
  }

  private void callback(WeaponFireEvent event) {
    System.out.println(event.bob);
  }

  @Override
  public void removed() {
    super.removed();
    //
    getEventBus().removeEventHandler(Events.WEAPON_FIRE);
    core.unsubscribeAll(this);
  }

  @Override
  public void update(int dt) {
    super.update(dt);

    getEventBus().fireEvent(new WeaponFireEvent());
  }

  @Override
  public void mousePress(MouseEvent event) {
    core.remove(this);
    GamePrototype gp = new GamePrototype();
    core.add(gp);
  }
}
