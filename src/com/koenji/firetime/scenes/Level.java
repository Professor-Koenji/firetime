package com.koenji.firetime.scenes;

import com.koenji.ecs.Core;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.CameraOffset;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRootScene;
import com.koenji.firetime.entities.Goal;
import com.koenji.firetime.events.GameEvent;
import com.koenji.firetime.input.InputHandler;
import com.koenji.firetime.input.command.*;
import com.koenji.firetime.systems.*;
import com.koenji.firetime.entities.Bullet;
import com.koenji.firetime.entities.Player;
import com.koenji.firetime.events.EmitBulletEvent;
import com.koenji.firetime.level.LevelObject;
import com.koenji.firetime.systems.GuardPathRenderer;
import com.koenji.firetime.systems.TimeLinearMotion;
import processing.core.PApplet;
import processing.core.PVector;
import processing.opengl.PShader;

import java.util.ArrayList;
import java.util.List;

public class Level extends Scene {

  private IGraphicsContext gc;

  private LevelObject levelObject;

  private Player p;

  private InputHandler inputHandler;

  private BasicRenderer renderer;
  private BasicRenderer guardPathRenderer;

  private List<ISubscriber> handlers;

  private float scale;
  private float dScale;

  private int kills;
  private int totalGuards;
  private int time;

  private PShader hueShader;
  private PShader channelsShader;
  private PShader glitchShader;

  public Level(LevelObject levelObject) {
    this.levelObject = levelObject;
    this.gc = Locator.get(IGraphicsContext.class);
    this.scale = 0.2f;
    this.dScale = 0;
    this.handlers = new ArrayList<>();
    this.kills = 0;
    this.time = 0;
    this.levelObject.setup();

    Core core = Locator.get(Core.class);
    this.hueShader = core.loadShader("shaders/hue.glsl");
    this.channelsShader = core.loadShader("shaders/channels.glsl");
    this.glitchShader = core.loadShader("shaders/glitch-lite.glsl");

    channelsShader.set("rmult", 1f, 1f);
    channelsShader.set("gmult", 1f, 1f);
    channelsShader.set("bmult", 1f, 1f);
  }

  @Override
  public void added() {
    super.added();
    //
    removeAll();
    add(EntityObject.create(new Background(0xA0301939)));

    p = new Player(this.levelObject.playerPosition);
    p.addComponent(new CameraOffset(p.getComponent(Position.class)));

    IEntity goal = new Goal(this.levelObject.exit, p.getComponent(Position.class));

    for (IEntity w : levelObject.getWalls()) {
      add(w);
    }

    for (IEntity d : levelObject.getDoors()) {
      add(d);
    }

    for (IEntity k : levelObject.getKeys(p.getComponent(Position.class))) {
      add(k);
    }

    List<IEntity> guardList = levelObject.getGuards(p.getComponent(Position.class));
    totalGuards = guardList.size();
    for (IEntity g : guardList) {
      add(g);
    }

    add(goal);
    add(p);

    add(new GuardFSM(p.getComponent(Position.class)));
    add(new TimeLinearMotion(p));
    add(new CircleCollider());
    add(new ExtendedCollider());
    renderer = new BasicRenderer(p.getComponent(Position.class));
    add(renderer);
    add(guardPathRenderer = new GuardPathRenderer(p.getComponent(Position.class)));
    add(new Minimap(new PVector(0, gc.getHeight() - 200), new PVector(300, 200)));

    // command stuff
    inputHandler = new InputHandler();
    float speed = 0.2f;
    inputHandler.bindKeyCommand(87, new MoveUpCommand(speed));
    inputHandler.bindKeyCommand(83, new MoveDownCommand(speed));
    inputHandler.bindKeyCommand(65, new MoveLeftCommand(speed));
    inputHandler.bindKeyCommand(68, new MoveRightCommand(speed));

    IEventBus eb = Locator.get(IEventBus.class);

    ISubscriber bulletEvent = eb.addEventHandler(EmitBulletEvent.EMIT_BULLET, this::fireBullet);
    ISubscriber keyPressEvent = eb.addEventHandler(InputEvents.KEY_PRESSED, e -> {
      if (e.keyCode() == 32) {
        System.out.println(p.getComponent(Position.class).toString());
      }
      if (e.keyCode() == 82) {
        IRootScene rootScene = Locator.get(IRootScene.class);
        rootScene.remove(this);
        rootScene.add(new EndOfLevel(kills, time));
      }
    });

    ISubscriber endOfLevelEvent = eb.addEventHandler(GameEvent.END_OF_LEVEL, e -> {
      IRootScene rootScene = Locator.get(IRootScene.class);
      rootScene.remove(this);
      rootScene.add(new EndOfLevel(kills, time));
    });

    ISubscriber deadGuard = eb.addEventHandler(GameEvent.KILLED_GUARD, e -> {
      kills++;
    });

    // Add these handlers
    handlers.add(bulletEvent);
    handlers.add(keyPressEvent);
    handlers.add(endOfLevelEvent);
    handlers.add(deadGuard);
  }

  @Override
  public void removed() {
    super.removed();//
    for(ISubscriber s : handlers) {
      s.unsubscribe();
    }
    handlers.clear();
    inputHandler.end();
  }

  private void fireBullet(EmitBulletEvent e) {
    Bullet b = new Bullet(e.getX(), e.getY(), e.angle());
    add(b);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    time += dt;
    //
    //System.out.println(p.getComponent(Velocity.class).mag());
    scale = (p.getComponent(Velocity.class).mag() / 9.45f) * .8f + .7f;
    //
    renderer.scale = scale;
    guardPathRenderer.scale = scale;

    // update commands
    for(ICommand e :  inputHandler.update()) {
      e.execute(p);
    }

    // Get player velocity
    PVector vel = p.getComponent(Velocity.class);

    //
    // SHADERS!!!!!
    //
    Core core = Locator.get(Core.class);

    // Draw HUD text
    gc.textSize(32);
    gc.fill(kills == totalGuards ? 0xFFFF0000 : 0xFFFFF0FF);
    gc.textAlign(gc.LEFT, gc.TOP);
    gc.text("Kills: " + kills + "/" + totalGuards, 128, 128);

    // ModColour
    // Hue
    float hue = PApplet.map((float) Math.sin(time / 1000f), 0f, 10f, 0f, (float) Math.PI * 2f);
    hueShader.set("hue", hue);

    // Channels
//    float offsetX = PApplet.map(vel.x, -3f, 3f, -.002f, .002f);
//    float offsetY = PApplet.map(vel.y, -3f, 3f, -.002f, .002f);
//    channelsShader.set("rbias", 0f, 0f);
//    channelsShader.set("gbias", -offsetX, -offsetY);
//    channelsShader.set("bbias", offsetX, offsetY);
    float offsetX = PApplet.map(Math.abs(vel.x), 0, 6f, 0.005f, 0);
    float offsetY = PApplet.map(Math.abs(vel.y), 0, 6f, 0.005f, 0);
    channelsShader.set("rbias", 0f, 0f);
    channelsShader.set("gbias", -offsetX, -offsetY);
    channelsShader.set("bbias", offsetX, offsetY);

    glitchShader.set("iGlobalTime", core.millis() / 10000f);

    // Apply shaders
    gc.filter(hueShader);
    gc.filter(channelsShader);
    gc.filter(glitchShader);
  }
}
