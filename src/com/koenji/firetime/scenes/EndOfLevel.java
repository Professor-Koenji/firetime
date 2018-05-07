package com.koenji.firetime.scenes;

import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRootScene;

public class EndOfLevel extends Scene {

  private int time;
  private int score;

  private ISubscriber handler;

  public EndOfLevel(int score, int time) {
    this.score = score;
    this.time = time;
  }

  @Override
  public void added() {
    super.added();
    //
    handler = Locator.get(IEventBus.class).addEventHandler(InputEvents.KEY, e -> {
      IRootScene rs = Locator.get(IRootScene.class);
      rs.remove(this);
      rs.add(new GameMenu());
    });
  }

  @Override
  public void remove(IEntity entity) {
    super.remove(entity);
    //
    handler.unsubscribe();
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);
    gc.background(0xFF000000);
    gc.text("Score: " + score, 40, 40);
    gc.text("Time: " + time + "s", 40, 140);
    gc.text("Press any key to go back to level select", 40, 240);
  }
}
