package com.koenji.firetime.event;

import javafx.event.Event;

public class WeaponFireEvent extends Event {

  public int bob;

  public WeaponFireEvent() {
    super(Events.WEAPON_FIRE);
    bob = (int) (Math.random() * 100);
  }
}
