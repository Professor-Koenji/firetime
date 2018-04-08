package com.koenji.ecs.audio;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * AudioManager defines the concrete implementation of the IAudioManager interface which uses the TinySound library
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */


public class AudioManager implements IAudioManager {

  // DECLARE a static boolean to check if instance created
  private static boolean instantiated = false;
  // DECLARE a float to represent the global volume used when playing sound/music
  private float globalVolume;
  // DECLARE a Map<String,Sound> to store the required sound files
  private Map<String, Sound> sounds;
  // DECLARE a Map<String,Sound> to store the required music files
  private Map<String, Music> musics;

  /**
   * Constructor: ensures only one instance has been created, sets field:globalVolume = 1
   */
  public AudioManager() {
    // check if already created
    if (instantiated) {
      System.out.println("AudioManager cannot be instantiated more than once!");
      System.exit(1);
    }
    // set field:instantiated flag to true
    instantiated = true;
    // Initialise TinySound lib
    TinySound.init();
    sounds = new HashMap<>();
    musics = new HashMap<>();
    globalVolume = 1;
  }

  /**
   * Method: load a named music file into memory
   *
    * @param name - representation of the file
   * @param filepath - relative path
   */
  public void loadMusic(String name, String filepath) {
    loadMusic(name, filepath, false);
  }

  /**
   * Method: load a named music file into memory with flag to stream
   *
   * @param name - representation of the file
   * @param filepath - relative path
   * @param stream - boolean value to stream audio
   */
  public void loadMusic(String name, String filepath, boolean stream) {
    File f = new File(filepath);
    musics.put(name, TinySound.loadMusic(f, stream));
  }

  /**
   * Method: load a named sound file into memory
   *
   * @param name - representation of the sound file
   * @param filepath - relative path
   */
  public void loadSound(String name, String filepath) {
    loadSound(name, filepath, false);
  }

  /**
   * Method: load a name sound file into memory with flag to stream
   *
   * @param name - representation of the sound file
   * @param filepath - relative path
   * @param stream - boolean value to stream sound
   */
  public void loadSound(String name, String filepath, boolean stream) {
    File f = new File(filepath);
    sounds.put(name, TinySound.loadSound(f, stream));
  }

  /**
   * Method: play the music file
   *
   * @param name - name of the music file used when loading
   */
  @Override
  public void playMusic(String name) {
    playMusic(name, false);
  }

  /**
   * Method: play the music file continuously/not
   *
   * @param name - name of the music file used when loading
   * @param loop - boolean option to loop
   */
  @Override
  public void playMusic(String name, boolean loop) {
    playMusic(name, false, 1);
  }

  /**
   * Method: play the music file continuously/not and set its volume
   *
   * @param name - name of the music file used when loading
   * @param loop - boolean option to loop
   * @param volume - value of the volume when playing
   */
  @Override
  public void playMusic(String name, boolean loop, float volume) {
    Music m = musics.get(name);
    if (m == null) {
      System.out.println("AudioManager -> Music not loaded '" + name + "'!");
      return;
    }
    m.play(loop, volume * globalVolume);
  }

  /**
   * Method: play the sound file given the name
   *
   * @param name - name of the sound file used when loading
   */
  @Override
  public void playSound(String name) {
    playSound(name, 1);
  }

  /**
   * Method: play the sound file and set its volume
   *
   * @param name - name of the sound file used when loading
   * @param volume - value of the volume when playing
   */
  @Override
  public void playSound(String name, float volume) {
    Sound s = sounds.get(name);
    if (s == null) {
      System.out.println("AudioManager -> Sound not loaded '" + name + "'!");
      return;
    }
    s.play(volume * globalVolume);
  }

  /**
   * Method: set the global volume
   *
   * @param volume - value of the global volume
   */
  @Override
  public void setVolume(float volume) {
    globalVolume = volume;
    if (globalVolume < 0) globalVolume = 0;
    if (globalVolume > 1) globalVolume = 1;
  }

  /**
   * Method: getter to the volume field
   *
   * @return - float: value of the volume field
   */
  @Override
  public float getVolume() {
    return globalVolume;
  }

  /**
   * Method: getter to return the tinysound.Music file
   *
   * @param name - name of the music file
   * @return - tinysound.Music file
   */
  @Override
  public Music getMusic(String name) {
    return musics.get(name);
  }

  /**
   * Method: getter to return the tinysound.Sound file
   *
   * @param name - name of the sound file
   * @return - tinysound.Sound file
   */
  @Override
  public Sound getSound(String name) {
    return sounds.get(name);
  }

}
