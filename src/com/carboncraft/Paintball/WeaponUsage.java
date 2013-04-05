package com.carboncraft.Paintball;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class WeaponUsage implements Listener{

//private PaintballPlayerController playerController;
	
	public WeaponUsage(PaintballPlayerController pc) {
//		this.playerController = pc;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void SnowballOnUsage (EntityInteractEvent event) {
		if(!(event.getEntity() instanceof Player)){
			return;
		}
/*		if (!((event.getBlock().getType()) == (Material.AIR))){
			return;
		} */
		
	}

}
