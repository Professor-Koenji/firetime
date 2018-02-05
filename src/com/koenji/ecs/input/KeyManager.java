package com.koenji.ecs.input;

import java.util.HashMap;
import java.util.Map;

public class KeyManager implements IKeyManager {

  private Map<Integer, Boolean> keys;

  public KeyManager() {
    keys = new HashMap<>();
  }

  @Override
  public void pressed(int keyCode) {
    keys.put(keyCode, true);
  }

  @Override
  public void released(int keyCode) {
    keys.put(keyCode, false);
  }

  @Override
  public boolean isPressed(int keyCode) {
    return keys.getOrDefault(keyCode, false);
  }
}
