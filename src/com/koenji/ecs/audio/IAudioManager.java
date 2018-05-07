package com.koenji.ecs.audio;

/**
 * IAudioManager defines the behaviour of an implementation of an Audio manager
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;

public interface IAudioManager {

  /**
   * Method: load a named music file into memory
   * @param name     - representation of the file
   * @param filepath - relative path
   */
  void loadMusic(String name, String filepath);

  /**
   * Method: load a named music file into memory
   * @param name     - representation of the file
   * @param filepath - relative path
   * @param stream   - boolean value to stream audio
   */
  void loadMusic(String name, String filepath, boolean stream);

  /**
   * Method: load a named sound file into memory
   * @param name     - representation of the sound file
   * @param filepath - relative path
   */
  void loadSound(String name, String filepath);

  /**
   * Method: load a named sound file into memory
   * @param name     - representation of the sound file
   * @param filepath - relative path
   * @param stream   - boolean value to stream sound
   */
  void loadSound(String name, String filepath, boolean stream);

  /**
   * Method: play the music file
   * @param name - name of the music file used when loading
   */
  void playMusic(String name);

  /**
   * Method: play the music file continuously/not
   * @param name - name of the music file used when loading
   * @param loop - boolean option to loop
   */
  void playMusic(String name, boolean loop);

  /**
   * Method: play the music file continuously/not and set volume
   * @param name   - name of the music file used when loading
   * @param loop   - boolean option to loop
   * @param volume - value of the volume when playing
   */
  void playMusic(String name, boolean loop, float volume);

  /**
   * Method: play the sound file given the name
   * @param name - name of the sound file used when loading
   */
  void playSound(String name);

  /**
   * Method: play the sound file and set a volume
   * @param name   - name of the sound file used when loading
   * @param volume - value of the volume when playing
   */
  void playSound(String name, float volume);

  /**
   * Method: set the global volume
   * @param volume - value of the global volume
   */
  void setVolume(float volume);

  /**
   * Method: getter to the volume field
   * @return - float: value of the volume field
   */
  float getVolume();

  /**
   * Method: getter to return the tinysound.Music file
   * @param name - name of the music file
   * @return     - tinysound.Music file
   */
  Music getMusic(String name);

  /**
   * Method: getter to return the tinysound.Sound file
   *
   * @param name - name of the sound file
   * @return - tinysound.Sound file
   */
  Sound getSound(String name);

}
