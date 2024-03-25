package com.libgdx.atb.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.libgdx.atb.test.dodger.DodgerGame;
import com.libgdx.atb.test.gamelaunchers.*;
import com.libgdx.atb.test.koala.KoalaGame;
import com.libgdx.atb.test.neondungeons.DungeonGame;
import com.libgdx.atb.test.quest.QuestGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] args) {
		Game currentGame = new GameHub();
		BaseLauncher currentGameLauncher = new GameHubLauncher();
		Lwjgl3Application launcher = new Lwjgl3Application(currentGame, currentGameLauncher.setConfig());
	}
}
