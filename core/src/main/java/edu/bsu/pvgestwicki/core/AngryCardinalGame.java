package edu.bsu.pvgestwicki.core;

import playn.core.Image;
import playn.core.Platform;
import playn.scene.ImageLayer;
import playn.scene.Layer;
import playn.scene.SceneGame;

public class AngryCardinalGame extends SceneGame {

    public AngryCardinalGame(Platform plat) {
        super(plat, 33); // update our "simulation" 33ms (30 times per second)
        initBackground();
        initCharlie();
    }

    private void initBackground() {
        Image bgImage = plat.assets().getImage("images/bg.png");
        ImageLayer bgLayer = new ImageLayer(bgImage);
        bgLayer.setSize(plat.graphics().viewSize);
        rootLayer.add(bgLayer);
    }

    private void initCharlie() {
        Image charlieImage = plat.assets().getImageSync("images/charlie.png");
        ImageLayer charlieLayer = new ImageLayer(charlieImage);
        charlieLayer.setOrigin(Layer.Origin.LL);
        charlieLayer.setTy(plat.graphics().viewSize.height());
        rootLayer.add(charlieLayer);
    }
}
