package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.libgdx.atb.test.gamelaunchers.BaseLauncher;
import com.libgdx.atb.test.gamelaunchers.SpaceGameLauncher;
import com.libgdx.atb.test.gamelaunchers.TurtleGameLauncherV3;
import com.libgdx.atb.test.gamelaunchers.TurtleGameLauncherV4;
import com.libgdx.atb.test.spacerocks.SpaceGame;
import com.libgdx.atb.test.turtle.TurtleGameV3;
import com.libgdx.atb.test.turtle.TurtleGameV4;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] args) {
		Game currentGame = new SpaceGame();
		BaseLauncher currentGameLauncher = new SpaceGameLauncher();

		Lwjgl3Application launcher = new Lwjgl3Application(currentGame, currentGameLauncher.setConfig());
	}

}
