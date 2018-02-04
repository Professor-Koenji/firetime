package com.koenji.firetime.scenes;

import com.koenji.ecs.ICore;
import com.koenji.ecs.scene.Scene;
import com.koenji.firetime.entities.DebugBox;
import com.koenji.firetime.systems.Renderer;

public class Splash extends Scene {

  @Override
  public void added(ICore core) {
    super.added(core);

    System.out.println("Added!!!");

    add(new DebugBox());

    add(new Renderer());
  }
}
