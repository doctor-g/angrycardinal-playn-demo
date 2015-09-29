package edu.bsu.pvgestwicki.core;

import playn.core.*;
import playn.scene.ImageLayer;
import playn.scene.Layer;
import react.Slot;
import tripleplay.game.ScreenStack;
import tripleplay.ui.Root;
import tripleplay.ui.SimpleStyles;
import tripleplay.ui.layout.AbsoluteLayout;

public class PlayingScreen extends ScreenStack.UIScreen {
    private boolean isCharlieMoving = false;
    private Layer charlieLayer;

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
            @Override
            public void onEmit(Clock clock) {
                if (isCharlieMoving) {
                    final float xSpeedPPS = 50;
                    final float ySpeedPPS = -40;
                    float x = charlieLayer.tx() + xSpeedPPS / clock.dt;
                    float y = charlieLayer.ty() + ySpeedPPS / clock.dt;
                    charlieLayer.setTranslation(x, y);
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
        return new Root(iface, new AbsoluteLayout(), SimpleStyles.newSheet(graphics()));
    }


}
