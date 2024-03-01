package com.libgdx.atb.test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.libgdx.atb.test.gamelaunchers.BaseLauncher;
import com.libgdx.atb.test.gamelaunchers.TurtleGameV2Launcher;
import com.libgdx.atb.test.turtle.StarfishCollectorGameV2;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg)
	{
		BaseLauncher currentGame = new TurtleGameV2Launcher();
		Lwjgl3Application launcher = new Lwjgl3Application(new StarfishCollectorGameV2(), currentGame.setConfig());
	}

}
