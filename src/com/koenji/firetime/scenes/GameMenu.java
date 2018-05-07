package com.koenji.firetime.scenes;

import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;

public class GameMenu extends Scene {

  private float rotation;

  public GameMenu() {
    this.rotation = 0;
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    rotation += .05f;
    //
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);
    // Clear background
    gc.fill(0x10000000);
    gc.rect(0, 0, gc.getWidth(), gc.getHeight());

    // Draw title text
    gc.pushMatrix();
    gc.translate(gc.getWidth() / 2, 200);
    gc.rotate((float) Math.sin(rotation) / 8f);
    gc.scale(((float) Math.cos(rotation / 4f) + 1) * .5f + 1);
    gc.textSize(128);
    gc.fill(0xFFFF4500);
    gc.textAlign(gc.CENTER, gc.CENTER);
    gc.text("FIRETIME", 0, 0);
    gc.popMatrix();

    // Draw level text
    gc.pushMatrix();
    gc.translate(gc.getWidth() / 2, 550);
    gc.rotate((float) Math.cos(rotation * .2f) / 10f);
    gc.scale(((float) Math.sin(rotation / 2f) + 1) * .2f + 1);
    gc.textSize(48);
    gc.fill(0xFFFFFFFF);
    gc.textAlign(gc.CENTER, gc.CENTER);
    gc.text("1: level-00", 0, -70);
    gc.text("2: level-01", 0, 0);
    gc.text("3: level-02", 0, 70);
    gc.popMatrix();
  }
}
