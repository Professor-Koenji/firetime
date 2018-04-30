package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.service.Locator;
import com.koenji.firetime.level.LevelObject;
import com.koenji.firetime.scenes.Level;

public class Game extends Core {

  Level level;

  public Game() {
    // Set initial resolution, fps&amp;title
    super(1600, 900, 60, "Firetime", 0xFFF8FBFE);
  }

  @Override
  public void init() {
    super.init();
    //
    LevelObject lo = LevelObject.fromPath("level-02");
    add(level = new Level(lo));
    //
    IEventBus eb = Locator.get(IEventBus.class);
    eb.addEventHandler(InputEvents.KEY_PRESSED, e -> {
      if (e.keyCode() == 82) {
        remove(level);
        add(new Level(LevelObject.fromPath("level-02")));
      }
    });

  }
}
