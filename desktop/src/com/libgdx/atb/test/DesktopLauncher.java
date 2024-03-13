package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.libgdx.atb.test.gamelaunchers.*;
import com.libgdx.atb.test.homework.HomeworkGame;
import com.libgdx.atb.test.rythm.RhythmGame;
import com.libgdx.atb.test.spacerocks.SpaceGame;
import com.libgdx.atb.test.turtle.TurtleGameV3;
import com.libgdx.atb.test.turtle.TurtleGameV4;
import com.libgdx.atb.test.turtle.TurtleGameV5;
import com.libgdx.atb.test.turtle.TurtleGameV6;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] args) {
		Game currentGame = new RhythmGame();
		BaseLauncher currentGameLauncher = new RhythmGameLauncher();
		Lwjgl3Application launcher = new Lwjgl3Application(currentGame, currentGameLauncher.setConfig());
	}
}
