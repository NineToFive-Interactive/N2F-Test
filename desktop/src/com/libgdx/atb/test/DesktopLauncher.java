package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.libgdx.atb.test.gamelaunchers.BaseLauncher;
import com.libgdx.atb.test.gamelaunchers.TurtleGameLauncherV3;
import com.libgdx.atb.test.turtle.TurtleGameV3;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] args) {
		Game currentGame = new TurtleGameV3();
		BaseLauncher currentGameLauncher = new TurtleGameLauncherV3();

		Lwjgl3Application launcher = new Lwjgl3Application(currentGame, currentGameLauncher.setConfig());
	}

}
