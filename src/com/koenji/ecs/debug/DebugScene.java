package com.koenji.ecs.debug;

import com.koenji.ecs.ICore;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.scene.Scene;
import processing.event.KeyEvent;

public class DebugScene extends Scene implements IKeyPress {

  private boolean show;

  @Override
  public void added(ICore core) {
    super.added(core);
    //
    show = false;
  }

  @Override
  public void keyPress(KeyEvent event) {
    if (event.getKeyCode() == 192) {
      show = !show;
    }
  }
}
