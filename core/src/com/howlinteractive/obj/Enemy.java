package com.howlinteractive.obj;

public class Enemy extends Object {

	@Override
	Type type() { return Type.ENEMY; }
	
	Enemy(float x, float y) {
		super(x, y, new Sprite("enemy.png"));
	}
	
	@Override
	void collision(Object obj) {
		super.collision(obj);
		switch(obj.type()) {
		case FRIENDLY:
			if(obj.isAlive) {
				takeDamage();
				obj.takeDamage();
			}
			break;
		case PLAYER:
			Room.p.takeDamage();
			break;
		default:
			break;
		}
	}
}
