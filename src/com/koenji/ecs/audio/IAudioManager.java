package com.koenji.ecs.audio;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;

public interface IAudioManager {

  void loadMusic(String name, String filepath);
  void loadMusic(String name, String filepath, boolean stream);
  void loadSound(String name, String filepath);
  void loadSound(String name, String filepath, boolean stream);

  void playMusic(String name);
  void playMusic(String name, boolean loop);
  void playMusic(String name, boolean loop, float volume);
  void playSound(String name);
  void playSound(String name, float volume);

  void setVolume(float volume);

  float getVolume();
  Music getMusic(String name);
  Sound getSound(String name);

}
