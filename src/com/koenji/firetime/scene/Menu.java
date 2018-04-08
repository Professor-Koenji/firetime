package com.koenji.firetime.scene;

import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IRootScene;
import com.koenji.firetime.event.Events;
import com.koenji.firetime.event.WeaponFireEvent;

public class Menu extends Scene {

  @Override
  public void added() {
    super.added();
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
    //
    IRootScene rs = Locator.get(IRootScene.class);
    //
    rs.remove(this);
    GamePrototype gp = new GamePrototype();
    rs.add(gp);
  }
}
