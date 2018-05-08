package com.koenji.firetime.scenes;

import com.koenji.ecs.Core;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRootScene;
import processing.opengl.PShader;

public class EndOfLevel extends Scene {

  private int time;
  private int score;

  private PShader hueShader;
  private PShader glitchShader;

  private ISubscriber handler;
  private float counter = 10f;

  public EndOfLevel(int score, int time) {
    this.score = score;
    this.time = time;
    Core core = Locator.get(Core.class);
    hueShader = core.loadShader("shaders/hue.glsl");
    glitchShader = core.loadShader("shaders/glitch.glsl");
  }

  @Override
  public void added() {
    super.added();
    //
    handler = Locator.get(IEventBus.class).addEventHandler(InputEvents.KEY, e -> {
      if(e.keyCode() != 32) return;
      IRootScene rs = Locator.get(IRootScene.class);
      rs.remove(this);
      rs.add(new GameMenu());
    });
  }

  @Override
  public void removed() {
    super.removed();

    handler.unsubscribe();
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    Core core = Locator.get(Core.class);
    //
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);
    gc.pushMatrix();
    gc.fill(0xFF03590a);
    gc.rect(0, 0, gc.getWidth(), gc.getHeight());
    gc.translate(gc.getWidth() / 2, gc.getHeight() / 2);
    gc.rotate((float) Math.sin(core.millis() / 1000f) * .2f);
    gc.fill(0xFFFFF444);
    gc.textAlign(gc.CENTER, gc.CENTER);
    gc.textSize(128);
    gc.text("Score: " + score, 0, -150);
    gc.text("Time: " + (time / 1000) + " seconds", 0, 0);
    gc.textSize(32);
    gc.text("Press the 'space' key to go back to level select", 40, 150);

    gc.popMatrix();

    // Shaders
    hueShader.set("hue", core.millis() / 1000f);
    glitchShader.set("iGlobalTime", core.millis() / 5000f);
    gc.filter(hueShader);
    gc.filter(glitchShader);
  }
}
