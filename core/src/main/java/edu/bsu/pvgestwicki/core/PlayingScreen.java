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
