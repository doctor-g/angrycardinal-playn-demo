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
