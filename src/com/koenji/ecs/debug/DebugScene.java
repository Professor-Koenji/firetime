package com.koenji.ecs.debug;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.Text;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.ISystem;
import com.koenji.ecs.system.render.BasicRenderer;

public class DebugScene extends Scene {

  private boolean show;
  private ISystem renderer;

  private Text fpsDisplay;

  @Override
  public void added(ICore core, IEventBus eventBus) {
    super.added(core, eventBus);
    //
    add(EntityObject.create(
      new Background(0xA0000000)
    ));
    add(EntityObject.create(
      new Position(10, 10),
      fpsDisplay = new Text("FPS: ", 16, 0xFFFFFFFF)
    ));
    //
    show = false;

    addEventHandler(InputEvents.KEY_PRESSED, this::keyPress);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    fpsDisplay.set("FPS: " + (1000 / dt));
  }

  private void keyPress(KeyEvent event) {
    // F1 Key
    if (event.keyCode() == 97) {
      show = !show;
      if (show) {
        add(renderer = new BasicRenderer());
      } else {
        remove(renderer);
      }
    }
  }
}
