package edu.bsu.pvgestwicki.java;

import edu.bsu.pvgestwicki.core.AngryCardinalGame;
import playn.java.LWJGLPlatform;

public class AngryCardinalGameJava {

    public static void main(String[] args) {
        LWJGLPlatform.Config config = new LWJGLPlatform.Config();
        // use config to customize the Java platform, if needed
        LWJGLPlatform plat = new LWJGLPlatform(config);
        new AngryCardinalGame(plat);
        plat.start();
    }
}
