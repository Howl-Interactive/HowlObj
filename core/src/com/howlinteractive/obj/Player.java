package com.howlinteractive.obj;

public class Player extends Object {

	@Override
	Type type() { return Type.PLAYER; };
	
	Player(float x, float y) {
		super(x, y, new Sprite("player.png"));
	}
	
	@Override
	void update() {
		if(cooldown != 0) { cooldown--; }
		super.update();
	}
	
	int cooldown = 0;
	final int FIRE_RATE = 20;
	void shoot(float angle) {
		if(cooldown == 0) {
			//shoot
			cooldown = FIRE_RATE;
		}
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