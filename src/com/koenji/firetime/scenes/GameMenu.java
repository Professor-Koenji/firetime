package com.koenji.firetime.scenes;

import com.koenji.ecs.Core;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRootScene;
import com.koenji.firetime.level.LevelObject;
import jdk.internal.util.xml.impl.Input;
import processing.core.PApplet;
import processing.core.PFont;
import processing.opengl.PShader;

import java.awt.event.InputEvent;

public class GameMenu extends Scene {

  private float rotation;
  private PFont font;
  private PShader hueShader;
  private PShader channelsShader;
  private ISubscriber keyHandler;

  public GameMenu() {
    this.rotation = 0;
    Core core = Locator.get(Core.class);
    font = core.createFont("fonts/showcase.ttf", 128);
    hueShader = core.loadShader("shaders/hue.glsl");
    channelsShader = core.loadShader("shaders/channels.glsl");

    keyHandler = Locator.get(IEventBus.class).addEventHandler(InputEvents.KEY_PRESSED, e -> {
      IRootScene root = Locator.get(IRootScene.class);
      LevelObject lo = LevelObject.fromPath("level-01");
      root.remove(this);
      root.add(new Level(lo));
    });
  }

  @Override
  public void removed() {
    super.removed();
    //
    keyHandler.unsubscribe();
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    rotation += .05f;
    //
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);

    // Clear background
    gc.fill(0x30440400);
    gc.rect(0, 0, gc.getWidth(), gc.getHeight());

    // Draw title text
    gc.pushMatrix();
    gc.translate(gc.getWidth() / 2, 200);
    gc.rotate((float) Math.sin(rotation) / 8f);
    gc.scale(((float) Math.cos(rotation / 4f) + 1) * .5f + 1);
    gc.textSize(128);
    gc.textFont(font);
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

    Core core = Locator.get(Core.class);
    hueShader.set("hue", rotation * .1f);
    float offsetX = PApplet.map(core.mouseX, 0, core.getWidth(), -.01f, .01f);
    float offsetY = PApplet.map(core.mouseY, 0, core.getHeight(), -.005f, .005f);
    channelsShader.set("rbias", 0f, offsetY);
    channelsShader.set("gbias", -offsetX, 0f);
    channelsShader.set("bbias", offsetX, -offsetY);
    channelsShader.set("rmult", 1f, 1f);
    channelsShader.set("gmult", 1f, 1f);
    channelsShader.set("bmult", 1f, 1f);
    gc.filter(hueShader);
    gc.filter(channelsShader);
  }
}
