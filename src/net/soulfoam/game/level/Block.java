package net.soulfoam.game.level;

import java.awt.*;

import net.soulfoam.game.Game;
import net.soulfoam.game.Tile;

public class Block extends Rectangle {

	private static final long serialVersionUID = 1L;

	public int[] id = { -1, -1 };// indicate types of blocks, dirt and sand, etc

	// Constructor
	public Block(Rectangle size, int[] id) {

		setBounds(size);
		this.id = id;
		
	}



	
	public void render(Graphics g) {// takes the Graphics g object as usual
		
		if (id != Tile.air) {
			g.drawImage(Tile.tileset, x - (int) Game.camX, y
					- (int) Game.camY, x + width - (int) Game.camX, y
					+ height - (int) Game.camY, id[0] * Tile.tileSize, id[1]
					* Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize,
					id[1] * Tile.tileSize + Tile.tileSize, null);
		}


	}
}
