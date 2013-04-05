package com.carboncraft.Paintball;

import org.bukkit.Color;

public enum Team {
	RED(Color.RED),
	BLUE(Color.BLUE),
	GREEN(Color.GREEN),
	YELLOW(Color.YELLOW);
	
	private Color color;
	
	private Team(Color color) {
		this.color = color;
	}
	
	public Color getArmorColor() {
		return color;
	}
}
