package net.soulfoam.game.level;

import java.util.*;

import net.soulfoam.game.Game;
import net.soulfoam.game.Tile;
import net.soulfoam.game.entities.Clone;
import net.soulfoam.game.entities.Mob;

//Spawns mobs
public class Spawner implements Runnable {
	public boolean isRunning = true;

	public Game game;
	public Level level;
	
	public Spawner(Game game, Level level) {
		this.game = game;
		this.level = level;
		new Thread(this).start();
	}

	public void spawnMob(Mob mob) {
		game.mob.add(mob);
	}

	public void run() {
		while (isRunning) {

			if (game.mob.toArray().length < Tile.maxMobs) {
				spawnMob(new Clone(new Random().nextInt((Level.worldW - 2)
						* Tile.tileSize)
						+ Tile.tileSize + 30, 50, Tile.tileSize, Tile.tileSize, game, level));
			}

			try {
				Thread.sleep(new Random().nextInt(8000) + 5000);
			} catch (Exception e) {
			}

		}
	}
}
