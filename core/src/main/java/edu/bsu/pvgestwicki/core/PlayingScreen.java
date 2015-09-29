/*
 * This file is part of Angry Cardinal PlayN Demo
 *
 * Angry Cardinal PlayN Demo is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * Angry Cardinal PlayN Demo is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Angry Cardinal PlayN Demo.  If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * Copyright 2015 Paul Gestwicki
 */

package edu.bsu.pvgestwicki.core;

import playn.core.*;
import playn.scene.ImageLayer;
import playn.scene.Layer;
import pythagoras.f.Vector;
import react.Slot;
import react.Value;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.util.Colors;

public class PlayingScreen extends ScreenStack.UIScreen {
    private boolean isCharlieMoving = false;
    private Layer charlieLayer;
    private final Value<Integer> score = Value.create(0);
    private final Label scoreLabel = new Label() {
        {
            updateScore();
            score.connect(new Slot<Integer>() {
                @Override
                public void onEmit(Integer event) {
                    updateScore();
                }
            });
        }

        private void updateScore() {
            text.update("Score: " + score.get());
        }
    };

    public PlayingScreen() {
        initBackground();
        initCharlie();
        game().plat.input().mouseEvents.connect(new Slot<Mouse.Event>() {
            @Override
            public void onEmit(Mouse.Event event) {
                if (event instanceof Mouse.ButtonEvent) {
                    Mouse.ButtonEvent buttonEvent = (Mouse.ButtonEvent) event;
                    if (buttonEvent.down) {
                        isCharlieMoving = !isCharlieMoving;
                    }
                }
            }
        });
        game().update.connect(new Slot<Clock>() {
            private final Vector change = new Vector(0, 0);
            @Override
            public void onEmit(Clock clock) {
                if (isCharlieMoving) {
                    final float xSpeedPPS = 50;
                    final float ySpeedPPS = -40;
                    change.x = xSpeedPPS / clock.dt;
                    change.y = ySpeedPPS / clock.dt;
                    charlieLayer.setTx(charlieLayer.tx() + change.x);
                    charlieLayer.setTy(charlieLayer.ty() + change.y);
                    score.update(score.get() + (int) change.length());
                }
            }
        });
    }

    private void initBackground() {
        final Image bgImage = assets().getImage("images/bg.png");
        final ImageLayer bgLayer = new ImageLayer(bgImage);
        bgLayer.setSize(graphics().viewSize);
        layer.add(bgLayer);
    }

    private Assets assets() {
        return game().plat.assets();
    }

    private Graphics graphics() {
        return game().plat.graphics();
    }

    private void initCharlie() {
        Image charlieImage = assets().getImage("images/charlie.png");
        charlieLayer = new ImageLayer(charlieImage);
        charlieLayer.setOrigin(Layer.Origin.LL);
        charlieLayer.setTy(graphics().viewSize.height());
        layer.add(charlieLayer);
    }

    @Override
    public Game game() {
        return AngryCardinalGame.game;
    }

    @Override
    protected Root createRoot() {
        return new Root(iface, AxisLayout.horizontal(), SimpleStyles.newSheet(graphics()))
                .addStyles(Style.BACKGROUND.is(Background.solid(Colors.WHITE)))
                .add(new Shim(0, 0)
                        .setConstraint(AxisLayout.stretched()))
                .add(scoreLabel)
                .setSize(size().width(), 30);
    }


}
