package com.koenji.ecs.debug;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.Text;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.ISystem;
import com.koenji.ecs.system.render.BasicRenderer;
import processing.event.KeyEvent;

public class DebugScene extends Scene implements IKeyPress {

  private boolean show;
  private ISystem renderer;

  private Text fpsDisplay;

  @Override
  public void added(ICore core) {
    super.added(core);
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
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    fpsDisplay.set("FPS: " + (1000 / dt));
  }

  @Override
  public void keyPress(KeyEvent event) {
    java.lang.System.out.println(event.getKeyCode());
    // F1 Key
    if (event.getKeyCode() == 97) {
      show = !show;
      if (show) {
        add(renderer = new BasicRenderer());
      } else {
        remove(renderer);
      }
    }
  }
}
