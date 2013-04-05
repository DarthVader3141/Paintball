package com.carboncraft.Paintball;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.Sound;

public class SnowballHitListener implements Listener {
	
	private PaintballPlayerController playerController;
	private Logger log;
	
	public SnowballHitListener(PaintballPlayerController pc, Logger log) {
		this.log = log;
		this.playerController = pc;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void MessageOnHit (EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if (!(damager instanceof Projectile)) {
			return;
		}
		
		Projectile snowball = (Projectile)event.getDamager();
		if (!((Entity)snowball.getShooter() instanceof Player)){
			return;
		}
		
		if (!((event.getEntity()) instanceof Player)){
			return;
		}
		
		Player shooter = (Player)snowball.getShooter();
		String shootername = (String)shooter.getName();
		Player target = (Player)event.getEntity();
		String targetname = (String)target.getName();

		for ( Sound s : Sound.values()) {
			shooter.playSound(shooter.getLocation(), s, 1.0f, 1.0f);
		}
		
		shooter.sendMessage(ChatColor.GREEN+"+1 Point! You hit "+(targetname)+".");
		if (playerController.getPaintballPlayer(shooter) != null) {
			playerController.getPaintballPlayer(shooter).changePoints(1);
			log.info("Changed points successfully.");
		}
		else {
			log.warning("null player returned for: "+shooter.toString()+" @"+shooter.hashCode());
			log.warning("Dump of hashmap: "+playerController.data.keySet().toString());
		}
		
		target.sendMessage(ChatColor.RED+"-1 Point! You were hit by "+(shootername)+".");
		playerController.getPaintballPlayer(target).changePoints(-1);
		playerController.getPaintballPlayer(target).changeHits(1);
		if (playerController.getPaintballPlayer(target).getHits() >= 3) {
			target.setHealth(0);
			playerController.getPaintballPlayer(target).resetHits();
		}
	}

}
