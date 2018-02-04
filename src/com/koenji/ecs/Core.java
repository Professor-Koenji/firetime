package com.koenji.ecs;

import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.scene.ISceneManager;
import com.koenji.ecs.scene.SceneManager;
import processing.core.PApplet;
import processing.event.MouseEvent;

public abstract class Core extends PApplet implements ICore {

  // The width of the window
  private int width;
  // The height of the window
  private int height;
  // The intended frame rate of the window
  private int fps;
  // The title of the window
  private String title;
  // The background colour of the canvas clear
  private int clearColour;

  // The scene manager
  private ISceneManager sceneManager;
  // The last millis time
  private int time;

  public Core() {
    this(512, 512);
  }

  public Core(int width, int height) {
    this(width, height, 60);
  }

  public Core(int width, int height, int fps) {
    this(width, height, fps, "KoenjiCore");
  }

  public Core(int width, int height, int fps, String title) {
    this(width, height, fps, title, 0xFF000000);
  }

  public Core(int width, int height, int fps, String title, int clearColour) {
    this.width = width;
    this.height = height;
    this.fps = fps;
    this.title = title;
    this.clearColour = clearColour;

    this.sceneManager = new SceneManager(this);
  }

  @Override
  final public void settings() {
    super.settings();
    // FX2D is the only rendered which works properly
    size(width, height, FX2D);
  }

  @Override
  final public void setup() {
    super.setup();
    // Set the title of the window
    surface.setTitle(title);
    // Set the games frame rate
    frameRate((float) fps);
    // Set the initial time
    time = millis();
    // Call init
    init();
  }

  @Override
  final public void draw() {
    super.draw();
    // Clear the canvas first
    background(clearColour);
    // Update the game logic
    int latestTime = millis();
    int dt = latestTime - time;
    update(dt);
    sceneManager.update(dt);
    time = latestTime;

    fill(0xFFFFFFFF);
    rect(mouseX, mouseY, 50, 50);
  }

  @Override
  final public void keyPressed() {
    super.keyPressed();
  }

  @Override
  final public void keyReleased() {
    super.keyReleased();
  }

  @Override
  final public void mouseMoved(MouseEvent event) {
    super.mouseMoved(event);
  }

  @Override
  final public void mousePressed() {
    super.mousePressed();
  }

  @Override
  final public void mouseReleased() {
    super.mouseReleased();
  }

  public void setClearColour(int rgba) {
    clearColour = rgba;
  }

  public void setTitle(String title) {
    this.title = title;
    surface.setTitle(title);
  }

  public void setFramerate(int fps) {
    this.fps = fps;
    this.frameRate((float) fps);
  }

  // TODO: Refactor this to use a PGraphics instance
  public Core gc() {
    return this;
  }

  public void add(IScene scene) {
    sceneManager.add(scene);
  }

  public void remove(IScene scene) {
    sceneManager.remove(scene);
  }

  public void init() {}
  public void update(int dt) {}
}
