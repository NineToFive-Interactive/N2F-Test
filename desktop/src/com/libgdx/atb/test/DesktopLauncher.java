package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.libgdx.atb.test.gamelaunchers.*;
import com.libgdx.atb.test.spacerocks.SpaceGame;
import com.libgdx.atb.test.turtle.TurtleGameV3;
import com.libgdx.atb.test.turtle.TurtleGameV4;
import com.libgdx.atb.test.turtle.TurtleGameV5;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] args) {
		Game currentGame = new TurtleGameV5();
		BaseLauncher currentGameLauncher = new TurtleGameLauncherV5();

		Lwjgl3Application launcher = new Lwjgl3Application(currentGame, currentGameLauncher.setConfig());
	}

}
