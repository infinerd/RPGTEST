package net.soulfoam.game.entities;



import java.awt.*;

import net.soulfoam.game.Game;
import net.soulfoam.game.KeyInput;
import net.soulfoam.game.Tile;
import net.soulfoam.game.level.Level;
import net.soulfoam.game.weapons.Bow;
import net.soulfoam.game.weapons.Weapon;

public class Player extends DoubleRectangle {

	public double fallingSpeed = 0.4;
	public double movementSpeed = 0.5;
	public double jumpingSpeed = 0.4;
	public double dir = 0;

	public byte facingDir = 0;
	
	public byte jumpingHeight = 35;
	public byte jumpingCount = 0;
	public byte animation = 0;
	public byte animationFrame = 0, animationTime = 30;

	private byte timer = 0;
	private byte timerTotal = 5;
	
	public int blockBreakTimer = 0;
	public int blockBreakTime = 50;

	public byte health = 10;
	public byte fullHealth = 10;
	public byte healthBarWidth = health;
	public byte healthBarHeight = 2;
	public byte redHealthBarWidth = healthBarWidth;
	public byte fullHealthBarWidth = healthBarWidth;
	

	private int amount = 1;
	
	public boolean isFiring = false;
	public boolean isJumping = false;
	public boolean isJumping2 = false;
	public boolean isMoving = false;
	public boolean isDead = false;
	public boolean isHurting = false;

	public Game game;
	public KeyInput input;
	public Level level;
	
	
	public Weapon bow;
	public Weapon currentWeapon;
	
	public Player(int width, int height, Game game, KeyInput input, Level level) {
		
		this.game = game;
		this.input = input;
		this.level = level;
		bow = new Bow(game);
		currentWeapon = bow;
		setBounds((Game.pixel.width / 2) - (width /2), (Game.pixel.height / 2) - (height /2), width, height);
	}

	public void tick(int camX, int camY, int renW, int renH) {
		
		
		if (input.left.isPressed()){
			isMoving = true;
			dir = -movementSpeed;
		}
		else{
			if (dir == -movementSpeed){
				isMoving = false;
			}
		}
		
		
		if (input.right.isPressed()){
			isMoving = true;
			dir = movementSpeed;
		}
		else{
			if (dir == movementSpeed){
				isMoving = false;
			}
		}
		
		if (input.jump.isPressed()){
			isJumping2 = true;
		}
		else{
			isJumping2 = false;
		}
		
		
		if (input.shoot.isPressed()){
			currentWeapon.setUse(true);
			currentWeapon.setPos((int)x, (int)y, facingDir);
		}
		else{
			currentWeapon.setUse(false);
		}
		
		if (dir == -movementSpeed) {
			facingDir = 0;
		}
		
		if (dir == movementSpeed) {
			facingDir = 1;
		}
		if (isMoving){
			System.out.println(x);
		}
		


		if (!isJumping
				&& !isCollidingWithBlock(new Point((int) x + 2,
						(int) (y + height)), new Point((int) (x + width - 2),
						(int) (y + height)))) {
			y += fallingSpeed;
			Game.camY += fallingSpeed;

		} else {

			if (isJumping2) {
				isJumping = true;

			}
		}

		if (isMoving) {
			boolean canMove = false;

			if (dir == movementSpeed) {

				canMove = isCollidingWithBlock(new Point((int) (x + width -1),
						(int) y), new Point((int) (x + width - 1),
						(int) (y + (height - 2))));

			} else if (dir == -movementSpeed) {

				canMove = isCollidingWithBlock(new Point((int) x -2, (int) y),
						new Point((int) x-2, (int) (y + (height - 2))));

			}

			if (animationFrame >= animationTime) {
				if (animation > 1) {
					animation = 1;
				} else {
					animation += 1;
				}

				animationFrame = 0;

			} else {
				animationFrame += 1;
			}

			if (!canMove) {

				x += dir;

				Game.camX += dir;
			}
		} else {
			animation = 0;
		}

		if (isJumping) {
			if (!isCollidingWithBlock(new Point((int) (x + 2), (int) y),
					new Point((int) (x + width - 2), (int) y))) {
				if (jumpingCount >= jumpingHeight) {
					isJumping = false;
					jumpingCount = 0;
				} else {
					y -= jumpingSpeed;
					Game.camY -= jumpingSpeed;

					jumpingCount += 1;
				}
			} else {
				isJumping = false;
				jumpingCount = 0;
			}
		}

		
		for (int i = 0; i < game.mob.toArray().length; i++) {
			if (isCollidingWithMob(game.mob.get(i).getRectangle())) {
				System.out.println("ouch hiut");
			}
		}
		
		
		currentWeapon.tick();

	}


	public boolean isCollidingWithBlock(Point pt1, Point pt2) { 

		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x
				/ Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y
					/ Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < game.level.block.length
						&& y < game.level.block[0].length)
					if (game.level.block[x][y].id != Tile.air && game.level.block[x][y].id != Tile.tree) {
						if (game.level.block[x][y].contains(pt1)
								|| game.level.block[x][y].contains(pt2)) {
					
							return true;
						
						}
					}
			}
		}
		return false;
	}


	
	public boolean isCollidingWithMob(Rectangle monsterRectangle) {
		if (getRectangle().intersects(monsterRectangle)) {
			return true;
		} else {
			return false;
		}
	}


	public void hurt(int amount) {
		this.amount = amount;
		if (health > 0) {

			health -= amount;
			healthBarWidth -= amount;
		}
		if (health <= 0) {
			isDead = true;
		}
	}

	public int getHealth() {
		return health;
	}
	
	

	public void render(Graphics g) {

		currentWeapon.render(g);
		
		
		if (health > 0) {
			g.setColor(Color.RED);
	
			g.fillRect((int)x - (int) Game.camX - 2,(int) y - (int)Game.camY - 5, redHealthBarWidth, healthBarHeight);

			g.setColor(Color.GREEN);
			g.fillRect((int)x - (int) Game.camX - 2,(int) y - (int)Game.camY - 5, healthBarWidth, healthBarHeight);
		}
		if (health <= 0) {
			g.setColor(Color.RED);
			g.fillRect((int)x - (int) Game.camX - 2,(int) y - (int)Game.camY - 5, redHealthBarWidth, healthBarHeight);
		}
		


		if (dir == movementSpeed) {
			g.drawImage(Tile.tileset, (int) x - (int) Game.camX,
					(int) y - (int) Game.camY, (int) (x + width)
							- (int) Game.camX, (int) (y + height)
							- (int) Game.camY,/**/

					(Tile.character[0] * Tile.tileSize)
							+ (Tile.tileSize * animation), Tile.character[1]
							* Tile.tileSize,
					(Tile.character[0] * Tile.tileSize)
							+ (Tile.tileSize * animation) + (int) width,
					Tile.character[1] * Tile.tileSize + (int) height, null);
		} else {
			g.drawImage(Tile.tileset, (int) x - (int) Game.camX,
					(int) y - (int) Game.camY, (int) (x + width)
							- (int) Game.camX, (int) (y + height)
							- (int) Game.camY,/**/

					(Tile.character[0] * Tile.tileSize)
							+ (Tile.tileSize * animation) + (int) width,
					Tile.character[1] * Tile.tileSize,
					(Tile.character[0] * Tile.tileSize)
							+ (Tile.tileSize * animation), Tile.character[1]
							* Tile.tileSize + (int) height, null);

		}
		

	}

}
