package net.soulfoam.game;

import java.awt.image.*;

import javax.imageio.ImageIO;
import java.io.*;

public class Tile {

	public static byte tileSize = 8;
	public static byte maxMobs = 6; 

	public static final int[] air = { -1, -1 };
	public static final int[] earth = { 0, 0 };
	public static final int[] grass = { 1, 0 }; 
	public static final int[] solidair = { -1, -1 };
	public static final int[] tree = { 10, 0};

	public static int[] mobClone = { 0, 1 };

	public static int[] character = { 0, 1 };
	
	public static int[] bow = { 0, 2 };
	public static int[] arrow = { 1, 2 };


	public static BufferedImage tileset;



	public Tile() {

		try {
			Tile.tileset = ImageIO.read(new File(
					"res/tileset.png"));
		} catch (Exception e) {

		}

	}

}
