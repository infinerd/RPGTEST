package net.soulfoam.game.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.soulfoam.game.Game;
import net.soulfoam.game.Tile;

public class Arrow extends ProjectileVars implements Projectile {

	public int destroyTimer = 0;
	public int destroyTime = 150;
	
	public Game game;
	
	public Arrow(double x, double y, byte facingDir, Game game){
		this.x = x;
		this.y = y;
		this.width = 8;
		this.height = 8;
		this.facingDir = facingDir;
		this.id = Tile.arrow;
		this.game = game;
	}
	
	public void tick() {
		
		if (facingDir == 0){
			x-= 1;
		}
		if (facingDir == 1){
			x+= 1;
		}

		
		if (destroyTime <= destroyTimer){
			game.projectiles.remove(this);
		}
		else{
			destroyTimer++;
		}
		
		checkCollision();
	}
	
	public void checkCollision(){
		for (int i = 0; i < game.mob.toArray().length; i++) {
			if (isCollidingWithMob(game.mob.get(i).getRectangle())) {
				System.out.println("arrow hit");
			}
		}
	}
	
	public boolean isCollidingWithMob(Rectangle monsterRectangle) {
		if (getBounds().intersects(monsterRectangle)) {
			return true;
		} else {
			return false;
		}
	}


	public void render(Graphics g) {
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


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, (int)width, (int)height);
	}

}
