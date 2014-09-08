package com.howlinteractive.obj;

public class Player extends Object {

	@Override
	Type type() { return Type.PLAYER; };
	
	Player(float x, float y) {
		super(x, y, new Sprite("player.png"));
	}
	
	@Override
	void collision(Type type) {
		switch(type) {
		case HAZARD:
			Game.gameOver();
			break;
		default:
			break;
		}
	}
}