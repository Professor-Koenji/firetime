package com.koenji.ecs;

import com.koenji.ecs.audio.AudioManager;
import com.koenji.ecs.audio.IAudioManager;
import com.koenji.ecs.event.EventBus;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.scene.ISceneManager;
import com.koenji.ecs.scene.SceneManager;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRandom;
import com.koenji.ecs.wrappers.IRootScene;
import processing.core.PApplet;

/**
 * The main class that provides the full ECS and Processing functionality.
 * Your own implementation should extend this class, and have it instantiated
 * statically through PApplet.main(class);
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */
public abstract class Core extends PApplet implements IRandom, IGraphicsContext, IRootScene {

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

  // The event bus
  private IEventBus eventBus;
  // The scene manager
  private ISceneManager sceneManager;
  // The last millis time
  private int time;

  /**
   * Define a new Core instance with default Processing dimensions (512x512)
   */
  public Core() {
    this(512, 512);
  }

  /**
   * Define a new PApplet with the specified width and height.
   * @param width The width of the drawable window (in pixels).
   * @param height The height of the drawable window (in pixels).
   */
  public Core(int width, int height) {
    this(width, height, 60);
  }

  /**
   * Define a new PApplet with the specified width and height, and requested FPS.
   * @param width The width of the drawable window (in pixels).
   * @param height The height of the drawable window (in pixels).
   * @param fps The requested update speed in frames-per-second.
   *            Note that the PApplet may not actually run at this framerate.
   */
  public Core(int width, int height, int fps) {
    this(width, height, fps, "KoenjiCore");
  }

  /**
   * Define a new PApplet with the specified width and height, and requested FPS.
   * @param width The width of the drawable window (in pixels).
   * @param height The height of the drawable window (in pixels).
   * @param fps The requested update speed in frames-per-second.
   *            Note that the PApplet may not actually run at this framerate.
   * @param title The title of the window to go in the window bar (MacOS&amp;Windows only).
   */
  public Core(int width, int height, int fps, String title) {
    this(width, height, fps, title, 0xFF000000);
  }

  /**
   * Define a new PApplet with the specified width and height, and requested FPS.
   * @param width The width of the drawable window (in pixels).
   * @param height The height of the drawable window (in pixels).
   * @param fps The requested update speed in frames-per-second.
   *            Note that the PApplet may not actually run at this framerate.
   * @param title The title of the window to go in the window bar (MacOS&amp;Windows only).
   * @param clearColour The buffer clear colour to use on each re-draw of the window.
   */
  public Core(int width, int height, int fps, String title, int clearColour) {
    this.width = width;
    this.height = height;
    this.fps = fps;
    this.title = title;
    this.clearColour = clearColour;
  }

  /**
   * Define a new PApplet.
   * @param fps The requested update speed in frames-per-second.
   *            Note that the PApplet may not actually run at this framerate.
   * @param title The title of the window to go in the window bar (MacOS&amp;Windows only).
   */
  public Core(int fps, String title) {
    this(fps, title, 0xFF000000);
  }

  /**
   * Define a new PApplet.
   * @param fps The requested update speed in frames-per-second.
   *            Note that the PApplet may not actually run at this framerate.
   * @param title The title of the window to go in the window bar (MacOS&amp;Windows only).
   * @param clearColour The buffer clear colour to use on each re-draw of the window.
   */
  public Core(int fps, String title, int clearColour) {
    this.fps = fps;
    this.title = title;
    this.clearColour = clearColour;
  }

  /**
   * Callback defined via the PApplet.
   * Called before the window or the Graphics API has been initialised.
   * Perfect for seeding RNG's, setting up initial non-graphics dependant stuff.
   */
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
    // Register locatable services
    Locator.register(IEventBus.class, eventBus = new EventBus());
    Locator.register(IAudioManager.class, new AudioManager());
    // Core interface wrappers
    Locator.register(IGraphicsContext.class, this);
    Locator.register(IRandom.class, this);
    Locator.register(IRootScene.class, this);
    Locator.register(Core.class, this);
    sceneManager = new SceneManager();
  }

  /**
   * Called once the graphics window has been created and the Graphics API
   * is available. You can now set the surface metadata and framerate.
   */
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

  /**
   * Called every update frame, all update&amp;drawing logic should go here.
   */
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

  /**
   * Hook for the keyPress event from any attached keyboard devices or keyevent dispatchers.
   * @param event The KeyEvent that raised the event.
   */
  @Override
  public void keyPressed(processing.event.KeyEvent event) {
    super.keyPressed(event);
    int keyCode = event.getKeyCode();
    boolean isAutoRepeat = event.isAutoRepeat();
    eventBus.fireEvent(new KeyEvent(InputEvents.KEY_PRESSED, keyCode, isAutoRepeat));
  }

  /**
   * Hook for the keyReleased event from any attached keyboard devices or keyevent dispatchers.
   * @param event The KeyEvent that raised the event.
   */
  @Override
  final public void keyReleased(processing.event.KeyEvent event) {
    super.keyReleased(event);
    int keyCode = event.getKeyCode();
    boolean isAutoRepeat = event.isAutoRepeat();
    eventBus.fireEvent(new KeyEvent(InputEvents.KEY_RELEASED, keyCode, isAutoRepeat));
  }

  /**
   * Hook for the mouseMoved event from any attached mice or pointer devices.
   * @param event The MouseEvent that raised the event.
   */
  @Override
  final public void mouseMoved(processing.event.MouseEvent event) {
    super.mouseMoved(event);
    int x = event.getX();
    int y = event.getY();
    int button = event.getButton();
    eventBus.fireEvent(new MouseEvent(InputEvents.MOUSE_MOVED, x, y, button));
  }

  /**
   * Hook for the mouseDragged event from any attached mice or pointer devices.
   * @param event The MouseEvent that raised the event.
   */
  @Override
  public void mouseDragged(processing.event.MouseEvent event) {
    super.mouseDragged(event);
    int x = event.getX();
    int y = event.getY();
    int button = event.getButton();
    eventBus.fireEvent(new MouseEvent(InputEvents.MOUSE_MOVED, x, y, button));
  }

  /**
   * Hook for the mousePressed event from any attached mice or pointer devices.
   * @param event The MouseEvent that raised the event.
   */
  @Override
  final public void mousePressed(processing.event.MouseEvent event) {
    super.mousePressed();
    int x = event.getX();
    int y = event.getY();
    int button = event.getButton();
    eventBus.fireEvent(new MouseEvent(InputEvents.MOUSE_PRESSED, x, y, button));
  }

  /**
   * Hook for the mouseReleased event from any attached mice or pointer devices.
   * @param event The MouseEvent that raised the event.
   */
  @Override
  final public void mouseReleased(processing.event.MouseEvent event) {
    super.mouseReleased();
    int x = event.getX();
    int y = event.getY();
    int button = event.getButton();
    eventBus.fireEvent(new MouseEvent(InputEvents.MOUSE_RELEASED, x, y, button));
  }

  /**
   * Changes the buffer clear colour.
   * @param rgba The new clear colour.
   */
  public void setClearColour(int rgba) {
    clearColour = rgba;
  }

  /**
   * Changes the window title.
   * Caution: Don't change this more than once a second, as it causes issues on MacOS and Windows.
   * @param title The new title of the window.
   */
  public void setTitle(String title) {
    this.title = title;
    surface.setTitle(title);
  }

  /**
   * Attempts to change the framerate of the active window.
   * Note that on some platforms, this may be completely ignored.
   * @param fps The requested frame rate of the window.
   */
  public void setFramerate(int fps) {
    this.fps = fps;
    this.frameRate((float) fps);
  }

  /**
   * Gets the width of the window (in pixels).
   * @return The width of the window (in pixels).
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets the height of the window (in pixels).
   * @return The height of the window (in pixels).
   */
  public int getHeight() {
    return height;
  }

  /**
   * Adds a scene to the root scene.
   * @param scene The scene to add.
   */
  public void add(IScene scene) {
    sceneManager.add(scene);
  }

  /**
   * Removes a scene from the root scene.
   * @param scene The scene to remove.
   */
  public void remove(IScene scene) {
    sceneManager.remove(scene);
  }

  /**
   * Called once the setup() function has resolved.
   */
  public void init() {}

  /**
   * Called once per frame, before any other engine update logic.
   * Don't use this to draw things unless you really know what you are doing.
   * @param dt The delta time since the last update call (in ms).
   */
  public void update(int dt) {}

}
