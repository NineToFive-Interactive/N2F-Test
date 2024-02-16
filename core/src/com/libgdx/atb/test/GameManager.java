package com.libgdx.atb.test;

import com.badlogic.gdx.ApplicationAdapter;

public class GameManager extends ApplicationAdapter {

	BaseGame activeGame = new DropletGame();

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
