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

import playn.core.Game;
import playn.core.Graphics;
import playn.core.Mouse;
import react.Connection;
import react.Slot;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.util.Colors;

public class TitleScreen extends ScreenStack.UIScreen {

    private final ScreenStack screenStack;
    private Connection mouseConnection;

    public TitleScreen(ScreenStack screenStack) {
        this.screenStack = screenStack;
    }

    @Override
    protected Root createRoot() {
        return new Root(iface, AxisLayout.vertical().gap(30), SimpleStyles.newSheet(graphics()))
                .addStyles(Style.BACKGROUND.is(Background.solid(Colors.LIGHT_GRAY)))
                .add(new Label("Angry Cardinal")
                        .addStyles(Style.COLOR.is(Colors.WHITE)))
                .add(new Label("A simple demonstration of PlayN"))
                .add(new Label("Click to continue"))
                .setSize(size());
    }

    private Graphics graphics() {
        return game().plat.graphics();
    }

    @Override
    public Game game() {
        return AngryCardinalGame.game;
    }

    @Override
    public void wasShown() {
        super.wasShown();
        mouseConnection = game().plat.input().mouseEvents.connect(new Slot<Mouse.Event>() {
            @Override
            public void onEmit(Mouse.Event event) {
                if (event instanceof Mouse.ButtonEvent) {
                    screenStack.push(new PlayingScreen(), screenStack.slide());
                    mouseConnection.close();
                }
            }
        });
    }
}
