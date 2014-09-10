package com.howlinteractive.obj;

import java.util.ArrayList;

public abstract class Object implements Comparable<Object> {

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
	
	static Object collisionPoint(float x, float y, Type type) {
		for(Object obj : Game.room.objs) {
			if(obj.type() == type && collisionPoint(x, y, obj)) {
				return obj;
			}
		}
		return null;
	}
	
	static boolean collisionPoint(float x, float y, Object obj) {
		if(x > obj.x - obj.w / 2 && x < obj.x + obj.w / 2 && y > obj.y - obj.h / 2 && y < obj.y + obj.h / 2) {
			return true;
		}
		return false;
	}
	
	static boolean collisionLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		float denom = ((y4 - y3) * (x2 - x1)) -	((x4 - x3) * (y2 - y1));
		if (denom == 0) {
			return false;
		}
		else {
			float ua = (((x4 - x3) * (y1 - y3)) - ((y4 - y3) * (x1 - x3))) / denom;
			float ub = (((x2 - x1) * (y1 - y3)) - ((y2 - y1) * (x1 - x3))) / denom;
			if (ua < 0 || ua > 1 || ub < 0 || ub > 1) {
				return false;
			}
			return true;
		}
	}
	
	static boolean collisionLine(float x1, float y1, float x2, float y2, Object obj) {
		for(int i = 0; i < 4; i++) {
			if(collisionLine(x1, y1, x2, y2, obj.x + (i % 2 == 0 ? -1 : 1) * obj.w / 2, obj.y + (i < 2 ? -1 : 1), obj.x + (i + 1 % 2 == 0 ? -1 : 1) * obj.w / 2, obj.y + (i + 1 < 2 ? -1 : 1))) {
				return true;
			}
		}		
		return false;
	}
	
	static Object collisionLine(float x1, float y1, float x2, float y2, Type type) {
		for(Object obj : Game.room.objs) {
			if(obj.type() == type && collisionLine(x1, y1, x2, y2, obj)) {
				return obj;
			}
		}
		return null;
	}
	
	void draw() {
		sprite.draw(x - w / 2, y - h / 2, w, h);
	}

	@Override
	public int compareTo(Object obj) {
		return sprite.depth == obj.sprite.depth ? 0 : 1;
	}
}