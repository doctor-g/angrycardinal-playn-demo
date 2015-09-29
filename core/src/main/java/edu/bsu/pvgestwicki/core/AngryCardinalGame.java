/*
 * This file is part of angrycardinal
 *
 * angrycardinal is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * angrycardinal is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with angrycardinal.  If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * Copyright 2015 Paul Gestwicki
 */

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
