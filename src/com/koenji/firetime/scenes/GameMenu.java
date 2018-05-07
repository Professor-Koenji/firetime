package com.koenji.firetime.scenes;

import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.scene.Scene;

public class GameMenu extends Scene {

  public GameMenu() {
    add(EntityObject.create(
      new Background(0xFF3399ff)
    ));
  }

}
