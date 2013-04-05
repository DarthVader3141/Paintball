package com.carboncraft.Paintball;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class PaintballPlayerController {

	private HashMap<Player, PaintballPlayer> data;
	
	public PaintballPlayerController() {
		this.data = new HashMap<Player, PaintballPlayer>();
	}
	
	public void newPlayer(Player p) {
		data.put(p, new PaintballPlayer(p));
	}
	
	PaintballPlayer getPaintballPlayer(Player p) {
		return data.get(p);
	}
}
