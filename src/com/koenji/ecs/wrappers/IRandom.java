package com.koenji.ecs.wrappers;

/**
 * A wrapper interface that should sit on-top of any object that implements
 * pseudo-random number generator functionality.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */
public interface IRandom {

  /**
   * Gets a random number within the specified range.
   * @param min The smallest number to return.
   * @param max The largest number to return.
   * @return A random number between min and max (both inclusive).
   */
  float random(float min, float max);

}
