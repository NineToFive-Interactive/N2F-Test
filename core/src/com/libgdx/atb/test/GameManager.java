package com.libgdx.atb.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.libgdx.atb.test.turtle.TurtleGameV2;

public class GameManager extends ApplicationAdapter {

	Game activeGame = new TurtleGameV2();

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
