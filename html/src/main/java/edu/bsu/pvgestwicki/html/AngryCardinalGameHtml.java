package edu.bsu.pvgestwicki.html;

import com.google.gwt.core.client.EntryPoint;
import edu.bsu.pvgestwicki.core.AngryCardinalGame;
import playn.html.HtmlPlatform;

public class AngryCardinalGameHtml implements EntryPoint {

    @Override
    public void onModuleLoad() {
        HtmlPlatform.Config config = new HtmlPlatform.Config();
        // use config to customize the HTML platform, if needed
        HtmlPlatform plat = new HtmlPlatform(config);
        plat.assets().setPathPrefix("angrycardinal/");
        new AngryCardinalGame(plat);
        plat.start();
    }
}
