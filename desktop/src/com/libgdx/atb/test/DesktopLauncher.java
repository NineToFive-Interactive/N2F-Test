package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.libgdx.atb.test.breakout.BreakoutGame;
import com.libgdx.atb.test.cards.CardGame;
import com.libgdx.atb.test.dodger.DodgerGame;
import com.libgdx.atb.test.gamelaunchers.*;
import com.libgdx.atb.test.homework.HomeworkGame;
import com.libgdx.atb.test.puzzle.PuzzleGame;
import com.libgdx.atb.test.spacerocks.SpaceGame;
import com.libgdx.atb.test.turtle.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] args) {
		Game currentGame = new TurtleGameV7();
		BaseLauncher currentGameLauncher = new TurtleGameLauncherV7();
		Lwjgl3Application launcher = new Lwjgl3Application(currentGame, currentGameLauncher.setConfig());
	}
}
