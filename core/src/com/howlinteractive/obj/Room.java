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
			if(obj.isAlive) { obj.update(); }
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
	
	void addSection() {
		String[] section = LevelSection.sPanels[Game.rand.nextInt(LevelSection.sPanels.length)];
		int rows = Integer.parseInt(section[0]);
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < 8; col++) {
				switch(Integer.parseInt("" + section[1].charAt(row * rows + col))) {
				case LevelSection.WALL:
					objs.add(new Wall(col * LevelSection.TILE_SIZE,  (rows - 1 - row) * LevelSection.TILE_SIZE + LevelSection.HEIGHT - offset, Wall.WIDTH, Wall.HEIGHT));
					break;
				case LevelSection.ZOMBIE1:
					objs.add(Zombie.create(col * LevelSection.TILE_SIZE, (rows - 1 - row) * LevelSection.TILE_SIZE + LevelSection.HEIGHT - offset, Zombie.Type.ZOMBIE1));
					break;
				case LevelSection.ZOMBIE2:
					objs.add(Zombie.create(col * LevelSection.TILE_SIZE, (rows - 1 - row) * LevelSection.TILE_SIZE + LevelSection.HEIGHT - offset, Zombie.Type.ZOMBIE2));
					break;
				case LevelSection.GUN2:
					objs.add(Item.create(col * LevelSection.TILE_SIZE, (rows - 1 - row) * LevelSection.TILE_SIZE + LevelSection.HEIGHT - offset, Item.Type.GUN2));
					break;
				}
			}
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
