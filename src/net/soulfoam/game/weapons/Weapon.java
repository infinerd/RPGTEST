package net.soulfoam.game.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

import net.soulfoam.game.Game;
import net.soulfoam.game.Tile;

public interface Weapon {
		
	public void tick();
	
	public void render(Graphics g);
	
	public Rectangle getBounds();
	
	public void setUse(boolean using);
	public void setCanUse(boolean canUse);
	
	public void setPos(int x, int y, byte facingDir);
}

class WeaponVars {
	public int x, y, width, height;
	public byte facingDir;
	public int[] id = { -1, -1 };
	public boolean isUsing = false;
	public boolean canUse = false;
	public byte animation = 0;
	public byte animationFrame = 0, animationTime = 30;
}
