package com.carboncraft.Paintball;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EggHitListener implements Listener{
	
	private PaintballPlayerController playerController;
	
	public EggHitListener(PaintballPlayerController pc) {
		this.playerController = pc;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void explodeOnHit (ProjectileHitEvent event) {
		
		Projectile projectile = event.getEntity();
		
		if (!(projectile instanceof Egg)) {
			return;
		}
		
		Location impactlocation = projectile.getLocation();
		
		//create explosion at impactlocation
		
		World world = impactlocation.getWorld();
		
		double xcoord = impactlocation.getX();
		double ycoord = impactlocation.getY();
		double zcoord = impactlocation.getZ();
		
		world.createExplosion(xcoord, ycoord, zcoord, 1, false, false);
		
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void handlePointsOnHit (EntityDamageByEntityEvent event) {
		Bukkit.getServer().broadcastMessage(event.toString());
		
		if (!(event.getCause() == DamageCause.ENTITY_EXPLOSION)) {
			return;
		}
		
		if (!(event.getEntity() instanceof Player)){
			return;
		}
		
		Player target = (Player) event.getEntity();		
	}

}
