package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.debug.DebugScene;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.events.IMouseMove;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
import com.koenji.ecs.scene.example.CirclePhysics;
import com.koenji.ecs.scene.example.ConvexCollisions;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(1600, 900, 60, "Firetime");
//    super(60, "Firetime", 0x00FF2299);
  }

  @Override
  public void init() {
    // Add initial scene here
//    CirclePhysics cp = new CirclePhysics();
//    subscribe(IMousePress.class, cp);
//    subscribe(IMouseMove.class, cp);
//    subscribe(IMouseRelease.class, cp);
//    add(cp);

    ConvexCollisions cc = new ConvexCollisions();
    subscribe(IMouseMove.class, cc);
    add(cc);

    DebugScene ds = new DebugScene();
    subscribe(IKeyPress.class, ds);
    add(ds);
  }
}
