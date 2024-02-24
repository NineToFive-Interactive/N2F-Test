package com.libgdx.atb.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;

public class GameManager extends ApplicationAdapter {

	Game activeGame = new StarfishCollectorGame();

	@Override
	public void create () {
		activeGame.create();
	}

	@Override
	public void render () {
		activeGame.render();
	}
	
	@Override
	public void dispose () {
		activeGame.dispose();
	}
}
