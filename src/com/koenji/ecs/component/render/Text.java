package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

/**
 * Text component to represent text content,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class Text implements IComponent {

  // DECLARE a String to hold its value
  public String contents;
  // DECLARE an int of its size
  public int size;
  // DECLARE an int of the colour
  public int rgba;
  // DECLARE a PVector of the position
  public PVector bounds;

  /**
   * Constructor: set the value, size and colour of text
   * @param contents  - String value of text
   * @param size      - int value of the size
   * @param rgba      - int value of colour
   */
  public Text(String contents, int size, int rgba) {
    this(contents, size, rgba, null);
  }

  /**
   * Constructor: set the value, size, colour & position of text
   * @param contents  - String value of text
   * @param size      - int value of the size
   * @param rgba      - int value of colour
   * @param bounds    - PVector of the position
   */
  public Text(String contents, int size, int rgba, PVector bounds) {
    set(contents);
    this.size = size;
    this.rgba = rgba;
    this.bounds = bounds;
  }

  /**
   * Method: SETTER for the contents field
   * @param contents - value of the text
   */
  public void set(String contents) {
    this.contents = contents;
  }

}
