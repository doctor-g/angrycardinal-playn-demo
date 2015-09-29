package edu.bsu.pvgestwicki.core;

import playn.core.Platform;
import playn.scene.SceneGame;
import tripleplay.game.ScreenStack;

public class AngryCardinalGame extends SceneGame {

    public static AngryCardinalGame game;

    public AngryCardinalGame(Platform plat) {
        super(plat, 33);
        AngryCardinalGame.game = this;
        ScreenStack screens = new ScreenStack(this, rootLayer);
        screens.push(new TitleScreen(screens));
    }
}
