package com.koenji.firetime.scene;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.firetime.entity.game.Player;
import com.koenji.firetime.entity.game.Wall;

public class GamePrototype extends Scene {

  @Override
  public void added(ICore core) {
    super.added(core);
    //
    add(EntityObject.create(
      new Background(0x10000040)
    ));

    Wall wall = new Wall();
    add(wall);
    //d
    Player p = new Player();
    addEventHandler(InputEvents.KEY_PRESSED, p::keyPress);
    addEventHandler(InputEvents.KEY_RELEASED, p::keyRelease);
    addEventHandler(InputEvents.MOUSE_PRESSED, p::mousePress);
    add(p);

    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());
    add(new BasicRenderer());
  }
}
