package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.service.Locator;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.io.File;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0xFF000000);
  }

  @Override
  public void init() {
    super.init();
    //
    TinySound.init();
    File sfxFile = new File("assets/sfx/cannon.wav");
    Sound sfx = TinySound.loadSound(sfxFile);

    Locator.get(IEventBus.class).addEventHandler(InputEvents.KEY_PRESSED, e-> {
      sfx.play();
    }, null);
  }
}
