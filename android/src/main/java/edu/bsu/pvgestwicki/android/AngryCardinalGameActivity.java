package edu.bsu.pvgestwicki.android;

import playn.android.GameActivity;

import edu.bsu.pvgestwicki.core.AngryCardinalGame;

public class AngryCardinalGameActivity extends GameActivity {

  @Override public void main () {
    new AngryCardinalGame(platform());
  }
}
