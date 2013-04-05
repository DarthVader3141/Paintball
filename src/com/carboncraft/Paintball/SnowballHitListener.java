package com.carboncraft.Paintball;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SnowballHitListener implements Listener {
	
	private PaintballPlayerController playerController;
	
	public SnowballHitListener(PaintballPlayerController pc) {
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
		
		shooter.sendMessage(ChatColor.GREEN+"+1 Point! You hit "+(targetname)+".");
		playerController.getPaintballPlayer(shooter).changePoints(1);
		
		target.sendMessage(ChatColor.RED+"-1 Point! You were hit by "+(shootername)+".");
		playerController.getPaintballPlayer(target).changePoints(-1);
		playerController.getPaintballPlayer(target).changeHits(1);
		if (playerController.getPaintballPlayer(target).getHits() >= 3) {
			target.damage(100);
		}
	}

}