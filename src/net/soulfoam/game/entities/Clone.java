package net.soulfoam.game.entities;

import java.awt.*;

import net.soulfoam.game.Game;
import net.soulfoam.game.Tile;
import net.soulfoam.game.level.Level;

//Clone enemy
public class Clone extends Mob {

	public Clone(int x, int y, int width, int height, Game game, Level level){
		super(x, y, width, height, Tile.mobClone, game, level);
		
	}
	
	public void tick(){
		super.tick();
	}
	
	public void render(Graphics g){
		super.render(g);
	}
}