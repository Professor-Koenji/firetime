package com.koenji.firetime.scene;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.Acceleration;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.events.*;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.firetime.InputTest.Command.*;
import com.koenji.firetime.entity.game.Bullet;
import com.koenji.firetime.entity.game.Player;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class GamePrototype extends Scene {

  private Player p;

  @Override
  public void added(ICore core) {
    super.added(core);
    //
    add(EntityObject.create(
      new Background(0x10000040)
    ));

    ConvexBody wall = new ConvexBody(
      250,
      new PVector(-200, -20),
      new PVector(200, -20),
      new PVector(200, 20),
      new PVector(-200, 20)
    );
    wall.isStatic = true;
    add(EntityObject.create(
      new Position(300, 400),
      new Velocity(),
      new Acceleration(),
      wall,
      new RenderPolygon(wall, 0xFF3399CC)
    ));

    //
    inputHandler.bindCommand(87, new MoveUpCommand());
    inputHandler.bindCommand(83, new MoveDownCommand());
    inputHandler.bindCommand(65, new MoveLeftCommand());
    inputHandler.bindCommand(68, new MoveRightCommand());

    p = new Player();

    core.subscribe(IKeyPress.class, this);
    core.subscribe(IKeyRelease.class, this);
    core.subscribe(IMousePress.class, this);
    core.subscribe(IMouseMove.class, this);
    add(p);
    //
    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());
    add(new BasicRenderer());
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    for(ICommand e :  inputHandler.update()) {
      e.execute(p);
    }
  }

  @Override
  public void keyPress(KeyEvent event) {
//    if(inputHandler.executeOnce(event) != null) {
//      inputHandler.executeOnce(event).execute(p);
//    }
    inputHandler.handleKeyPress(event);
  }

  @Override
  public void keyRelease(KeyEvent event) {
    inputHandler.handleKeyRelease(event);
  }

  @Override
  public void mousePress(MouseEvent event) {
    Position playerPosition = p.getComponent(Position.class);

    PVector v = PVector.sub(new PVector(event.getX(), event.getY()), playerPosition);

    Bullet bill = new Bullet(PVector.add(playerPosition, v.setMag(20f)), v.setMag(5f));

    add(bill);
  }


}
