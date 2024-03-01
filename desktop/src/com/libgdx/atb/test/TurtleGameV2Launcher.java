package com.libgdx.atb.test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class TurtleGameV2Launcher extends BaseLauncher{
    @Override
    public Lwjgl3ApplicationConfiguration setConfig() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Turtle Game V2");
        config.setWindowedMode(1024,1024);
        config.setForegroundFPS(60);
        return config;
    }
}
