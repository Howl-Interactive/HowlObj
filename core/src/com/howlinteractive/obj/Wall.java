package com.howlinteractive.obj;

public class Wall extends Object {

	@Override
	Type type() { return Type.SOLID; }
	
	Wall(float x, float y) {
		super(x, y, new Sprite("wall.png"));
	}
}
