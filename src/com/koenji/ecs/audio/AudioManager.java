package com.koenji.ecs.audio;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AudioManager implements IAudioManager {

  private static boolean instantiated = false;

  private float globalVolume;

  private Map<String, Sound> sounds;
  private Map<String, Music> musics; // Variable name literally makes no sense

  public AudioManager() {
    if (instantiated) {
      System.out.println("AudioManager cannot be instantiated more than once!");
      System.exit(1);
    }
    instantiated = true;
    // Initialise TinySound lib
    TinySound.init();
    sounds = new HashMap<>();
    musics = new HashMap<>();
    globalVolume = 1;
  }

  public void loadMusic(String name, String filepath) {
    loadMusic(name, filepath, false);
  }

  public void loadMusic(String name, String filepath, boolean stream) {
    File f = new File(filepath);
    musics.put(name, TinySound.loadMusic(f, stream));
  }

  public void loadSound(String name, String filepath) {
    loadSound(name, filepath, false);
  }

  public void loadSound(String name, String filepath, boolean stream) {
    File f = new File(filepath);
    sounds.put(name, TinySound.loadSound(f, stream));
  }

  @Override
  public void playMusic(String name) {
    playMusic(name, false);
  }

  @Override
  public void playMusic(String name, boolean loop) {
    playMusic(name, false, 1);
  }

  @Override
  public void playMusic(String name, boolean loop, float volume) {
    Music m = musics.get(name);
    if (m == null) {
      System.out.println("AudioManager -> Music not loaded '" + name + "'!");
      return;
    }
    m.play(loop, volume * globalVolume);
  }

  @Override
  public void playSound(String name) {
    playSound(name, 1);
  }

  @Override
  public void playSound(String name, float volume) {
    Sound s = sounds.get(name);
    if (s == null) {
      System.out.println("AudioManager -> Sound not loaded '" + name + "'!");
      return;
    }
    s.play(volume * globalVolume);
  }

  @Override
  public void setVolume(float volume) {
    globalVolume = volume;
    if (globalVolume < 0) globalVolume = 0;
    if (globalVolume > 1) globalVolume = 1;
  }

  @Override
  public float getVolume() {
    return globalVolume;
  }

  @Override
  public Music getMusic(String name) {
    return musics.get(name);
  }

  @Override
  public Sound getSound(String name) {
    return sounds.get(name);
  }

}
