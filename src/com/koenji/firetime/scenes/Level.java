package com.koenji.firetime.scenes;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.entity.EntityGroup;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.entities.Bullet;
import com.koenji.firetime.entities.Player;
import com.koenji.firetime.events.EmitBulletEvent;
import com.koenji.firetime.level.LevelObject;

import java.util.List;

public class Level extends Scene {

  private IGraphicsContext gc;

  private LevelObject levelObject;

  public Level(LevelObject levelObject) {
    this.levelObject = levelObject;
    this.gc = Locator.get(IGraphicsContext.class);

    this.levelObject.setup();
  }

  @Override
  public void added() {
    super.added();
    //
    add(EntityObject.create(new Background(0xFF002299)));

    Player p = new Player(this.levelObject.playerPosition);

    for (IEntity w : levelObject.getWalls()) {
      add(w);
    }

    for (IEntity g : levelObject.getGuards(p.getComponent(Position.class))) {
      add(g);
    }

    add(p);

    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());
    add(new BasicRenderer());

    addEventHandler(EmitBulletEvent.EMIT_BULLET, this::fireBullet);
  }

  private void fireBullet(EmitBulletEvent e) {
    Bullet b = new Bullet(e.getX(), e.getY(), e.angle());
    add(b);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    // Draw le nodes
    gc.stroke(0xFFFFFFFF);
    gc.strokeWeight(3);
    for (int i = 0; i < levelObject.nodes.size(); ++i) {
      INode n = levelObject.nodes.get(i);
      List<INode> ns = n.getNeighbours();
      for (INode n2 : ns) {
        gc.line(n.getX(), n.getY(), n2.getX(), n2.getY());
      }
    }
    for (INode n : levelObject.nodes) {
      gc.fill(0xFFFF0000);
      gc.rect(n.getX() - 8, n.getY() - 8, 16, 16);
    }
    //
    gc.fill(0xFFFFFFFF);
    gc.textSize(14);
    gc.text("Entities: " + entityCount(), 20, 20);
  }
}
