package net.soulfoam.game.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Projectile {
	public void tick();
	
	public void render(Graphics g);
	
	public Rectangle getBounds();
}

class ProjectileVars {
	public double x, y, width, height;
	public byte facingDir;
	public int[] id = { -1, -1 };
	public byte animation = 0;
	public byte animationFrame = 0, animationTime = 30;
}
