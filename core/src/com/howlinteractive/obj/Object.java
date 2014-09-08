package com.howlinteractive.obj;

import java.util.ArrayList;

public abstract class Object {

	enum Type { NONE, SOLID, HAZARD, PLAYER }
	abstract Type type();
	
	Sprite sprite;
	float x, y, velX, velY, accX, accY;
	int w, h;
	
	boolean isAlive = true;
	
	Object(float x, float y, int w, int h, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.sprite = sprite;
	}
	
	Object(float x, float y, Sprite sprite) {
		this(x, y, sprite.width(), sprite.height(), sprite);
	}
	
	void update() {
		accelerate();
		move();
	}
	
	void accelerate() {
		velX += accX;
		velY += accY;
	}
	
	void move() {
		float xStep = getXStep(), yStep = getYStep();
		for(int i = 0; i < Math.max(Math.abs(velX), Math.abs(velY)); i++) {
			x += xStep;
			y += yStep;
			handleCollisions();
		}
	}
	
	boolean isColliding(Object obj) {
		return  x - w / 2 < obj.x + w / 2 &&
				x + w / 2 > obj.x - w / 2 &&
				y - h / 2 < obj.y + h / 2 &&
				y + h / 2 > obj.y - h / 2;
	}
	
	ArrayList<Object> getCollisions() {
		ArrayList<Object> collisions = new ArrayList<Object>();
		for(Object obj : Game.room.objs){
			if(isColliding(obj)) {
				collisions.add(obj);
			}
		}
		return collisions;
	}
	
	void handleCollisions() {
		ArrayList<Object> collisions = getCollisions();
		for(Object obj : collisions) {
			collision(obj.type());
		}
	}
	
	/**call super.collision(type) for solid collisions**/
	@SuppressWarnings("incomplete-switch")
	void collision(Type type) {
		switch(type) {
		case SOLID:
			solidCollision();
			break;
		}
	}
	
	private float getXStep() {
		return Math.abs(velX) > Math.abs(velY) ? 1 : Math.abs(velX / velY);
	}
	
	private float getYStep() {
		return Math.abs(velY) > Math.abs(velX) ? 1 : Math.abs(velY) / Math.abs(velX);
	}
	
	void solidCollision() {
		velX = 0;
		velY = 0;
		x -= getXStep();
		y -= getYStep();
	}
	
	float getDir() {
		return (float)Math.atan2(velY, velX);
	}
	
	void setDir(float angle, boolean adjustRotation) {
		double hypot = Math.hypot(velX, velY);
		velX = (float)(Math.cos(angle) * hypot);
		velY = (float)(Math.sin(angle) * hypot);
		if(adjustRotation) { adjustRotation(); }
	}
	
	void adjustRotation() {
		sprite.rotation = (float)Math.atan2(velX, velY);
	}
	
	void setRotation(float rotation) {
		sprite.rotation = rotation;
	}
	
	void draw() {
		sprite.draw(x - w / 2, y - h / 2, w, h);
	}
}
