package com.libgdx.atb.test.gamelaunchers;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libgdx.atb.test.BaseActor;

public class DungeonGameLauncher extends BaseLauncher {

    @Override
    public Lwjgl3ApplicationConfiguration setConfig() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Neon Dungeons");
        config.setWindowedMode(1920,1080);
        config.setForegroundFPS(60);
        config.setMaximized(true);
        config.setWindowIcon("logo.png");
        return config;
    }
}
