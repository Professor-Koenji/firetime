package com.koenji.ecs;

import com.koenji.ecs.event.observer.*;
import com.koenji.ecs.input.*;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.scene.ISceneManager;
import com.koenji.ecs.scene.SceneManager;
import processing.core.PApplet;
import processing.event.KeyEvent;
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
  // The input manager
  private IInputManager inputManager;
  // Key Manager
  private IKeyManager keyManager;
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
  }

  public Core(int fps, String title) {
    this(fps, title, 0xFF000000);
  }

  public Core(int fps, String title, int clearColour) {
    this.fps = fps;
    this.title = title;
    this.clearColour = clearColour;
  }

  @Override
  final public void settings() {
    super.settings();
    // FX2D is the only rendered which works properly
    if (width > 0 && height > 0) {
       size(width, height, P2D);
    } else {
      fullScreen(P2D);
      this.width = displayWidth;
      this.height = displayHeight;
    }
    randomSeed(millis());
    smooth(8);
    //
    sceneManager = new SceneManager(this);
    inputManager = new InputManager();
    keyManager = new KeyManager();
  }

  @Override
  final public void setup() {
    super.setup();
    // Set the title of the window
    surface.setTitle(title);
    // Set the games frame rate
    frameRate(fps);
    // Set the initial time
    time = millis();
    // Clear the background
    background(0);
    // Call init
    init();
    loop();
  }

  @Override
  final public void draw() {
    // super.draw();
    // Clear the canvas first
    noStroke();
    fill(clearColour);
    rect(0, 0, width, height);
    // Update the game logic
    int latestTime = millis();
    int dt = latestTime - time;
    update(dt);
    sceneManager.update(dt);
    time = latestTime;
  }

  @Override
  public void keyPressed(KeyEvent event) {
    super.keyPressed(event);
    int keyCode = event.getKeyCode();
    if (keyManager.isPressed(keyCode)) return;
    keyManager.pressed(keyCode);
    inputManager.notify(IKeyPress.class, event);
  }

  @Override
  final public void keyReleased(KeyEvent event) {
    super.keyReleased(event);
    int keyCode = event.getKeyCode();
    if (!keyManager.isPressed(keyCode)) return;
    keyManager.released(keyCode);
    inputManager.notify(IKeyRelease.class, event);
  }

  @Override
  final public void mouseMoved(MouseEvent event) {
    super.mouseMoved(event);

    inputManager.notify(IMouseMove.class, event);
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    super.mouseDragged(event);

    inputManager.notify(IMouseMove.class, event);
  }

  @Override
  final public void mousePressed(MouseEvent event) {
    super.mousePressed();

    inputManager.notify(IMousePress.class, event);
  }

  @Override
  final public void mouseReleased(MouseEvent event) {
    super.mouseReleased();

    inputManager.notify(IMouseRelease.class, event);
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

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
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

  public <T extends IObserver> void subscribe(Class<T> type, T instance) {
    inputManager.subscribe(type, instance);
  }
  public <T extends IObserver> void unsubscribeAll(T instance) { inputManager.unsubscribeAll(instance); }

  public void init() {}
  public void update(int dt) {}
}
