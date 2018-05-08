package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.audio.IAudioManager;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.service.Locator;
import com.koenji.firetime.level.LevelObject;
import com.koenji.firetime.scenes.GameMenu;
import com.koenji.firetime.scenes.Level;

public class Game extends Core {

  Level level;

  public Game() {
    // Set initial resolution, fps&amp;title
    super(1600, 900, 60, "Firetime", 0x01000000);
  }

  @Override
  public void init() {
    super.init();
    // Load audio
    IAudioManager audio = Locator.get(IAudioManager.class);
    audio.loadSound("alert", "data/sfx/tindeck_1.wav");
    //
    add(new GameMenu());
  }
}
