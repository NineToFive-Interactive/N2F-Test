package com.libgdx.atb.test.gamelaunchers;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DodgerLauncher extends BaseLauncher{
    @Override
    public Lwjgl3ApplicationConfiguration setConfig() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Plane Dodger");
        config.setWindowedMode(800,600);
        config.setForegroundFPS(60);
        return config;
    }
}
