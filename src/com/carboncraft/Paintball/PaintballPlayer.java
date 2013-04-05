package com.carboncraft.Paintball;

import java.util.Random;

import org.bukkit.entity.Player;

public class PaintballPlayer {
	
	static Random r = new Random(); //temporary
	
	private Player player;
	private int points;
	private Team team;
	private int hits;
	
	public PaintballPlayer(Player player) {
		this.player = player;
		this.points = 0;
		this.hits = 0;
		setTeam(Team.values()[r.nextInt(4)]);
	}

	public Player getPlayer() {
		return player;
	}
	
	public void changePoints(int delta) {
		points+=delta;
	}
	
	public int getPoints() {
		return points;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public int getHits() {
		return hits;
	}
	
	public void changeHits(int delta) {
		hits+=delta;
	}
	
	public void resetHits() {
		hits = 0;
	}
}
