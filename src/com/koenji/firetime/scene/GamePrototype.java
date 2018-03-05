package com.koenji.firetime.scene;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.event.bus.IEventBus;
import com.koenji.ecs.event.observer.IKeyPress;
import com.koenji.ecs.event.observer.IKeyRelease;
import com.koenji.ecs.event.observer.IMouseMove;
import com.koenji.ecs.event.observer.IMousePress;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.firetime.entity.game.Player;
import com.koenji.firetime.entity.game.Wall;
import com.koenji.firetime.event.Events;
import com.koenji.firetime.event.WeaponFireEvent;
import javafx.event.Event;

public class GamePrototype extends Scene {

  @Override
  public void added(ICore core, IEventBus eventBus) {
    super.added(core, eventBus);
    //
    add(EntityObject.create(
      new Background(0x10000040)
    ));

    Wall wall = new Wall();
    add(wall);
    //d
    Player p = new Player();
    core.subscribe(IKeyPress.class, p);
    core.subscribe(IKeyRelease.class, p);
    core.subscribe(IMousePress.class, p);
    core.subscribe(IMouseMove.class, p);
    add(p);
    //
    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());
    add(new BasicRenderer());
  }
}
