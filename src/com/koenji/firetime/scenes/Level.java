package com.koenji.firetime.scenes;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.CameraOffset;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRootScene;
import com.koenji.firetime.entities.Goal;
import com.koenji.firetime.events.GameEvent;
import com.koenji.firetime.input.InputHandler;
import com.koenji.firetime.input.command.*;
import com.koenji.firetime.systems.ExtendedCollider;
import com.koenji.firetime.systems.GuardFSM;
import com.koenji.firetime.entities.Bullet;
import com.koenji.firetime.entities.Player;
import com.koenji.firetime.events.EmitBulletEvent;
import com.koenji.firetime.level.LevelObject;
import com.koenji.firetime.systems.GuardPathRenderer;
import com.koenji.firetime.systems.TimeLinearMotion;
import javafx.geometry.Pos;

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

  private int score;
  private int time;

  public Level(LevelObject levelObject) {
    this.levelObject = levelObject;
    this.gc = Locator.get(IGraphicsContext.class);
    this.scale = 0.2f;
    this.dScale = 0;
    this.handlers = new ArrayList<>();
    this.score = 0;
    this.time = 0;
    this.levelObject.setup();
  }

  @Override
  public void added() {
    super.added();
    //
    add(EntityObject.create(new Background(0xFF002299)));

    p = new Player(this.levelObject.playerPosition);
    p.addComponent(new CameraOffset(p.getComponent(Position.class)));

    IEntity goal = new Goal(this.levelObject.exit, p.getComponent(Position.class));

    for (IEntity w : levelObject.getWalls()) {
      add(w);
    }

    for (IEntity g : levelObject.getGuards(p.getComponent(Position.class))) {
      add(g);
    }

    add(goal);
    add(p);

    add(new GuardFSM(p.getComponent(Position.class)));
    add(new TimeLinearMotion(p));
    add(new CircleCollider());
    add(new ExtendedCollider());
    renderer = new BasicRenderer(p.getComponent(Position.class));
    add(guardPathRenderer = new GuardPathRenderer(p.getComponent(Position.class)));
    add(renderer);

    // command stuff
    this.inputHandler = new InputHandler();
    float speed = 0.2f;
    inputHandler.bindKeyCommand(87, new MoveUpCommand(speed));
    inputHandler.bindKeyCommand(83, new MoveDownCommand(speed));
    inputHandler.bindKeyCommand(65, new MoveLeftCommand(speed));
    inputHandler.bindKeyCommand(68, new MoveRightCommand(speed));

    IEventBus eb = Locator.get(IEventBus.class);

    ISubscriber bulletEvent = eb.addEventHandler(EmitBulletEvent.EMIT_BULLET, this::fireBullet);
    ISubscriber keyPressEvent = eb.addEventHandler(InputEvents.KEY_PRESSED, e -> {
      if (e.keyCode() == 90) {
        if (dScale == 0f) {
          dScale = this.scale > 0.6f ? -0.05f : 0.05f;
        } else {
          dScale *= -1;
        }
      }
      if (e.keyCode() == 32) {
        System.out.println(p.getComponent(Position.class).toString());
      }
    });

    ISubscriber endOfLevelEvent = eb.addEventHandler(GameEvent.END_OF_LEVEL, e -> {
      IRootScene rootScene = Locator.get(IRootScene.class);
      rootScene.remove(this);
      rootScene.add(new EndOfLevel(score, time));
    });

    // Add these handlers
    handlers.add(bulletEvent);
    handlers.add(keyPressEvent);
    handlers.add(endOfLevelEvent);
  }

  @Override
  public void removed() {
    super.removed();//
    for(ISubscriber s : handlers) {
      s.unsubscribe();
    }
    handlers.clear();
  }

  private void fireBullet(EmitBulletEvent e) {
    Bullet b = new Bullet(e.getX(), e.getY(), e.angle());
    add(b);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    //System.out.println(p.getComponent(Velocity.class).mag());
    scale = 1f - (p.getComponent(Velocity.class).mag() / 9.45f) * .5f;
    //
    renderer.scale = scale;
    guardPathRenderer.scale = scale;

    // update commands
    for(ICommand e :  inputHandler.update()) {
      e.execute(p);
    }
  }
}
