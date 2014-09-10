package com.howlinteractive.obj;

import java.util.ArrayList;
import java.util.Collections;

public class Room {
	
	static float playerStartX = Game.width / 2, playerStartY = 200;
	static Player p;
	
	ArrayList<Object> objs;
	
	float scrollX, scrollY;
	
	Room() {
		objs = new ArrayList<Object>();
		if(p == null) { p = new Player(playerStartX, playerStartY); }
		objs.add(p);
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
		rearrangeByDepth();
		for(Object obj : objs) {
			obj.draw();
		}
	}
	
	void rearrangeByDepth() {
		Collections.sort(objs);
	}
	
	void onTouch() {
		
	}
	
	static void drawLine(float x1, float y1, float x2, float y2) {
		//TODO
	}
}
