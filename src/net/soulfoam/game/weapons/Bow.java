package net.soulfoam.game.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.soulfoam.game.Game;
import net.soulfoam.game.Tile;

public class Bow extends WeaponVars implements Weapon{

	public int useTimer = 0;
	public int useTime = 50;
	public Game game;
	
	public Bow(Game game){
		this.width = 8;
		this.height = 8;
		this.facingDir = 0;
		this.id = Tile.bow;
		this.game = game;
		useTimer = useTime;
	}
	
	public void tick() {
		
		if (useTimer > 0) {
			useTimer--;
			canUse = false;
	
		}
		
		if (useTimer <= 0){
			canUse = true;
			useTimer = useTime;
		}
		
		if (isUsing){
			shootArrow();
		}
	}

	
	public void render(Graphics g) {
		if (isUsing){
			if (facingDir == 1) {// animation to go right
				g.drawImage(Tile.tileset, (int) x - (int) Game.camX,
						(int) y - (int) Game.camY, (int) (x + width)
								- (int) Game.camX, (int) (y + height)
								- (int) Game.camY,/**/

						(id[0] * Tile.tileSize)
								+ (Tile.tileSize * animation), id[1]
								* Tile.tileSize,
						(id[0] * Tile.tileSize)
								+ (Tile.tileSize * animation) + (int) width,
						id[1] * Tile.tileSize + (int) height, null);
			} else {// reverse animation to go left
				g.drawImage(Tile.tileset, (int) x - (int) Game.camX,
						(int) y - (int) Game.camY, (int) (x + width)
								- (int) Game.camX, (int) (y + height)
								- (int) Game.camY,/**/

						(id[0] * Tile.tileSize)
								+ (Tile.tileSize * animation) + (int) width,
						id[1] * Tile.tileSize,
						(id[0] * Tile.tileSize)
								+ (Tile.tileSize * animation), id[1]
								* Tile.tileSize + (int) height, null);

			}
		}
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	
	public void setUse(boolean isUsing) {
		this.isUsing = isUsing;
	}
	
	public void setCanUse(boolean canUse){
		this.canUse = canUse;
	}
	
	public void shootArrow(){
		if (canUse){
			game.projectiles.add(new Arrow(x, y, facingDir, game));
		}
	}


	public void setPos(int x, int y, byte facingDir) {
		this.y = y;
		this.facingDir = facingDir;
		
		if (facingDir == 0){
			this.x = x - 8 ;
		}
		else{
			this.x = x + 8 ;
		}
		
	}

}
