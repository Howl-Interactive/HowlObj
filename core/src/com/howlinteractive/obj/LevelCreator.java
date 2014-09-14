package com.howlinteractive.obj;

public class LevelCreator {
	
	static final int WIDTH = Game.width;
	static final int HEIGHT = Game.height;

	static int TILE_SIZE = 50;
	
	static final int UNIT = 0;
	static final int GRID = 1;
	
	static final int EMPTY = 0;
	static final int PATH = 1;
	static final int WALL = 2;
	static final int ENEMY = 3;
	
	static String[][] sPanels = {
		{ "8", "0000000011110000000100000001000000000000000000000000011100000000" },
		{ "8", "0011110000000000000000000000010000011100000000000000000011000000" },
		{ "8", "0000000000000000000000001111100000001000000000000000000000001111" },
		{ "8", "0001100000000000000000000000111100000000000000000001100000000000" },
		{ "8", "0000000011111000000000000000000000011111000000000000000000011000" },
		{ "8", "0000011100000000000000000000100000001000001110000000100000001000" },
		{ "8", "0000000011110000000100000001000000000000000000000000011100000000" }
	};
}