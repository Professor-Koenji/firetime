package com.koenji.firetime.systems;

import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.render.BasicRenderer;
import processing.core.PVector;

import java.awt.event.InputEvent;

public class Minimap extends BasicRenderer {

  private PVector pos;
  private PVector size;

  private boolean big;
  private ISubscriber handler;

  public Minimap(PVector pos, PVector size) {
    this.pos = pos;
    this.size = size;
    this.big = false;
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
    handler = Locator.get(IEventBus.class).addEventHandler(InputEvents.KEY_PRESSED, e -> {
      if (e.keyCode() == 77) {
        this.big = !this.big;
      }
    });
  }

  @Override
  public void removed() {
    super.removed();
    handler.unsubscribe();
  }

  @Override
  public void update(int dt) {
    gc.pushMatrix();
    if (big) {
      gc.clip(50, 50, gc.getWidth() - 100, gc.getHeight() - 100);
      gc.translate(350, 225);
      gc.scale(0.25f);
      render();
      gc.noClip();
    } else {
      gc.clip(pos.x, pos.y, size.x, size.y);
      gc.translate(pos.x + 50, pos.y + 45);
      gc.scale(0.055f);
      render();
      gc.noClip();
    }
    gc.popMatrix();
  }
}
