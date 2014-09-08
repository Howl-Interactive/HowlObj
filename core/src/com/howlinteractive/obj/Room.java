package com.howlinteractive.obj;

import java.util.ArrayList;

public class Room {

	static float playerStartX = Game.width / 2, playerStartY = 200;
	
	ArrayList<Object> objs;
	Player p;
	
	float scrollX, scrollY;
	
	Room() {
		objs = new ArrayList<Object>();
		objs.add(p = new Player(playerStartX, playerStartY));
	}
	
	Room(float playerStartX, float playerStartY, float scrollX, float scrollY) {
		this();
		p.x = playerStartX;
		p.y = playerStartY;
		this.scrollX = scrollX;
		this.scrollY = scrollY;
	}
	
	void update() {
		for(Object obj : objs) {
			obj.update();
		}
		for(int i = objs.size() - 1; i >= 0; i--) {
			if(!objs.get(i).isAlive) { objs.remove(i); }
		}
		scroll();
	}
	
	void scroll() {
		for(Object obj : objs){
			obj.x += scrollX;
			obj.y += scrollY;
		}
	}
	
	void draw() {
		for(Object obj : objs) {
			obj.draw();
		}
	}
	
	void onTouch() {
		
	}
}
