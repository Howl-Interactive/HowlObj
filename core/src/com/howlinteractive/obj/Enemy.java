package com.howlinteractive.obj;

public class Enemy extends Object {

	@Override
	Type type() { return Type.ENEMY; }
	
	Enemy(float x, float y) {
		super(x, y, new Sprite("enemy.png"));
	}
	
	@Override
	void update() {
		setVel((float)Math.atan2(Room.p.y - y, Room.p.x - x), true);
		super.update();
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
