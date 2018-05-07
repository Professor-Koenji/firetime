package com.koenji.firetime.systems;

import com.koenji.ecs.system.render.BasicRenderer;
import processing.core.PVector;

public class Minimap extends BasicRenderer {

  private PVector pos;
  private PVector size;

  public Minimap(PVector pos, PVector size) {
    this.pos = pos;
    this.size = size;
  }

  @Override
  public void update(int dt) {
    gc.pushMatrix();
    gc.clip(pos.x, pos.y, size.x, size.y);
    gc.translate(pos.x + 50, pos.y + 45);
    gc.scale(0.055f);
    render();
    gc.noClip();
    gc.popMatrix();
  }
}
