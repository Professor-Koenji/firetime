package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.debug.DebugScene;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.scene.example.CirclePhysics;
import com.koenji.ecs.scene.example.ConvexCollisions;
import com.koenji.firetime.event.Events;
import com.koenji.firetime.event.WeaponFireEvent;
import com.koenji.firetime.scene.Menu;
import javafx.scene.shape.Circle;
import jdk.internal.util.xml.impl.Input;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0x10000000);
  }

  @Override
  public void init() {
    add(new Menu());

    addEventHandler(Events.WEAPON_FIRE, event -> {
      System.out.println("WEAPON!");
    });

    DebugScene ds = new DebugScene();
    add(ds);
  }
}
