package com.koenji.firetime.scene;

import com.koenji.ecs.ICore;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.Scene;
import com.koenji.firetime.event.Events;
import com.koenji.firetime.event.WeaponFireEvent;

public class Menu extends Scene {

  @Override
  public void added(ICore core, IEventBus eventBus) {
    super.added(core, eventBus);
    //
    addEventHandler(InputEvents.MOUSE_PRESSED, this::mousePress);
    addEventHandler(Events.WEAPON_FIRE, this::callback);
  }

  private void callback(WeaponFireEvent event) {
    System.out.println(event.bob);
  }

  @Override
  public void update(int dt) {
    super.update(dt);

    fireEvent(new WeaponFireEvent(), false);
  }

  public void mousePress(MouseEvent event) {
    core.remove(this);
    GamePrototype gp = new GamePrototype();
    core.add(gp);
  }
}
