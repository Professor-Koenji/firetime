package com.koenji.ecs.system.render;

import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Rotation;
import com.koenji.ecs.component.render.*;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityManager;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.System;
import processing.core.PVector;

/**
 * A general-purpose basic renderer that draws most primitives and polgon shapes.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.2
 */
public class BasicRenderer extends System {

  public float scale;

  private IGraphicsContext gc;
  private PVector offset;

  public BasicRenderer() {
    this(new PVector(0, 0));
  }

  public BasicRenderer(PVector offset) {
    this.offset = offset;
    this.scale = 1f;
  }

  @Override
  public void added(IScene scene, IEntityManager entityManager) {
    super.added(scene, entityManager);
    //
    this.gc = Locator.get(IGraphicsContext.class);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update(int dt) {
    super.update(dt);
    // Ensure no nullptr exceptions from null gc
    // Automatically removes this system if gc isn't available for some reason.
    if (gc == null) {
      java.lang.System.out.println("GraphicsContext is not available -> BasicRenderer will remove itself.");
      scene.remove(this);
      return;
    }
    //
    gc.pushMatrix();
    gc.scale(this.scale);
    gc.translate(-offset.x, -offset.y);
    float invScale = 1 / this.scale;
    gc.translate(gc.getWidth() / 2f * invScale, gc.getHeight() / 2f * invScale);
    //
    for (IEntity e : entities) {
      Stroke stroke = e.getComponent(Stroke.class);
      CameraOffset cameraOffset = e.getComponent(CameraOffset.class);

      // Background renderer
      if (e.hasComponents(Background.class)) {
        Background b = e.getComponent(Background.class);
        gc.background(b.rgba);
      }

      // Rendering polygons
      if (e.hasComponents(Position.class, RenderPolygon.class)) {
        Position p = e.getComponent(Position.class);
        RenderPolygon rc = e.getComponent(RenderPolygon.class);
        Rotation r = e.getComponent(Rotation.class);
        // Save matrix state
        gc.pushMatrix();
        gc.translate(p.x, p.y);
        // Rotation matrix
        if (r != null) gc.rotate(r.angle);
        gc.fill(rc.rgba);
        if (stroke != null) {
          gc.strokeWeight(stroke.weight);
          gc.stroke(stroke.rgba);
        } else {
          gc.noStroke();
        }
        gc.beginShape();
        for (PVector v : rc.vertices) gc.vertex(v.x, v.y);
        gc.endShape(gc.CLOSE);
        // Restore transformation matrix
        gc.popMatrix();
      }

      // Circles
      if (e.hasComponents(Position.class, RenderCircle.class)) {
        Position p = e.getComponent(Position.class);
        RenderCircle rc = e.getComponent(RenderCircle.class);
        gc.fill(rc.rgba);
        if (stroke != null) {
          gc.strokeWeight(stroke.weight);
          gc.stroke(stroke.rgba);
        } else {
          gc.noStroke();
        }
        gc.arc(p.x, p.y, rc.r*2, rc.r*2, 0, gc.TWO_PI);
      }

      // Lines
      if (e.hasComponents(Position.class, RenderLine.class)) {
        Position p = e.getComponent(Position.class);
        RenderLine rl = e.getComponent(RenderLine.class);
        gc.strokeWeight(rl.weight);
        gc.stroke(rl.rgba);
        gc.line(p.x, p.y, rl.to.x, rl.to.y);
      }

      // Text
      if (e.hasComponents(Position.class, Text.class)) {
        Position p = e.getComponent(Position.class);
        Text t = e.getComponent(Text.class);
        gc.textSize(t.size);
        gc.noStroke();
        gc.fill(t.rgba);
        gc.textAlign(gc.LEFT, gc.TOP);
        if (t.bounds != null) {
          gc.text(t.contents, p.x, p.y, t.bounds.x, t.bounds.y);
        } else {
          gc.text(t.contents, p.x, p.y);
        }
      }
    }
    gc.popMatrix();
  }
}
