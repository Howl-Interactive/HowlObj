package com.howlinteractive.obj;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	
	static Random rand = new Random();
	
	static SpriteBatch sB;
	
	static int width;
	static int height;
	
	static ArrayList<Room> rooms;
	static private int curRoom;
	static Room room;
	static void nextRoom() { curRoom++; room = rooms.get(curRoom); }
	static void changeRoom(int nextRoom) { curRoom = nextRoom; room = rooms.get(curRoom); }
	
	@Override
	public void create () {
		sB = new SpriteBatch();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		loadTextures();
		rooms = new ArrayList<Room>();
		rooms.add(new Room());
		changeRoom(0);
	}
	
	void loadTextures() {
		
	}
	
	void handleInput() {
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			room.onTouch();
		}
	}

	void update() {
		room.update();
	}
	
	void draw() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sB.begin();
		room.draw();
		sB.end();
	}
	
	@Override
	public void render () {
		handleInput();
		update();
		draw();
	}
	
	static int getXInput() {
		return Gdx.input.getX();
	}
	
	static int getYInput() {
		return Game.height - Gdx.input.getY();
	}
	
	static void gameOver() {
		
	}
}