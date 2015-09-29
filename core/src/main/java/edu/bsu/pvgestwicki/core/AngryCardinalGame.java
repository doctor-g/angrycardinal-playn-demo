package edu.bsu.pvgestwicki.core;

import playn.core.Clock;
import playn.core.Image;
import playn.core.Mouse;
import playn.core.Platform;
import playn.scene.ImageLayer;
import playn.scene.Layer;
import playn.scene.SceneGame;
import react.Slot;

public class AngryCardinalGame extends SceneGame {

    private boolean isCharlieMoving = false;
    private Layer charlieLayer;

    public AngryCardinalGame(Platform plat) {
        super(plat, 33); // update our "simulation" 33ms (30 times per second)
        initBackground();
        initCharlie();
        plat.input().mouseEvents.connect(new Slot<Mouse.Event>() {
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
    }

    private void initBackground() {
        Image bgImage = plat.assets().getImage("images/bg.png");
        ImageLayer bgLayer = new ImageLayer(bgImage);
        bgLayer.setSize(plat.graphics().viewSize);
        rootLayer.add(bgLayer);
    }

    private void initCharlie() {
        Image charlieImage = plat.assets().getImage("images/charlie.png");
        charlieLayer = new ImageLayer(charlieImage);
        charlieLayer.setOrigin(Layer.Origin.LL);
        charlieLayer.setTy(plat.graphics().viewSize.height());
        rootLayer.add(charlieLayer);
    }

    @Override
    public void update(Clock clock) {
        super.update(clock);
        if (isCharlieMoving) {
            final float xSpeedPPS = 50;
            final float ySpeedPPS = -40;
            float x = charlieLayer.tx() + xSpeedPPS / clock.dt;
            float y = charlieLayer.ty() + ySpeedPPS / clock.dt;
            charlieLayer.setTranslation(x, y);
        }
    }
}
