package net.soulfoam.game.level;


import java.awt.*;
import java.util.*;

import net.soulfoam.game.Game;
import net.soulfoam.game.Tile;

public class Level {

	public static int worldW = 500, worldH = 500;

	public Block[][] block = new Block[worldW][worldH];
	
	public Game game;

	// Constructor
	public Level(Game game) {
		
		this.game = game;
		
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[0].length; y++) {
				block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y
						* Tile.tileSize, Tile.tileSize, Tile.tileSize),
						Tile.air);

			}
		}

		generateLevel();

	}

	public void generateLevel() {

		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if (y > worldH/50) {

					try {
							block[x][y].id = Tile.earth;

					} catch (Exception e) {
					}
				}
			}
		}
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if (y > worldH /50) {

					try {
						if (new Random().nextInt(100) <= 10) {

							for (int i = 0; i < new Random().nextInt(5) + 4; i++) {
							block[x + i][y - 1].id = Tile.earth;
							}
					}

					} catch (Exception e) {
					}
				}
			}
		}
		

		
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				try {
					if (block[x][y + 1].id == Tile.earth
							&& block[x][y].id == Tile.air) {
						if (new Random().nextInt(100) <= 10) {

								block[x][y].id = Tile.tree;
								block[x][y + 1].id = Tile.grass;
						}
					}
				} catch (Exception e) {
				}
			}
		}


		for (int y = 0; y < block.length; y++) { 
			for (int x = 0; x < block[0].length; x++) {
				if (block[x][y].id == Tile.earth
						&& block[x][y - 1].id == Tile.air) {
					block[x][y].id = Tile.grass;
				}
			}
		}



		for (int y = 0; y < block.length; y++) { 
			for (int x = 0; x < block[0].length; x++) {
				if (x == 0 || y == 0 || x == block[0].length - 1
						|| y == block[0].length - 1) {
					block[x][y].id = Tile.solidair;
				}
			}
		}

	}




	public void render(Graphics g, int camX, int camY, int renW, int renH) {
		for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) { 
			for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize)
					+ renH; y++) {
				if (x >= 0 && y >= 0 && x < worldW && y < worldH) {
					block[x][y].render(g);
					
					if (block[x][y].id != Tile.solidair){
						g.setColor(new Color(0, 0, 0, 0));
						g.fillRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width, block[x][y].height);
						
						if (getBlocksAbove(x, y) == 1) {
							g.setColor(new Color(0, 0, 0, 60));
							g.fillRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width, block[x][y].height);
						} else {
							for (int i = 0; i < getBlocksAbove(x, y); i++) {
								g.setColor(new Color(0, 0, 0, 60));
								g.fillRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width, block[x][y].height);
							}
						}

						
					}

					if (block[x][y].id != Tile.air
							&& block[x][y].id != Tile.solidair
						) {
	
						if (block[x][y].contains(new Point(
								(Game.mse.x / Game.pixelSize)
										+ (int) Game.camX,
								(Game.mse.y / Game.pixelSize)
										+ (int) Game.camY))) {
							g.setColor(new Color(255, 255, 255, 60));
							g.fillRect(block[x][y].x - camX, block[x][y].y
									- camY, block[x][y].width,
									block[x][y].height);
						}
					}
				}
			}
		}
	}
	
	public int getBlocksAbove(int x, int y) {
		int count = 0;

		for (int u = 0; u < y; u++) {
			if (block[x][u].id != Tile.solidair && block[x][u].id != Tile.air
					) {
				count++;
			}
		}

		return count;
	}
}
