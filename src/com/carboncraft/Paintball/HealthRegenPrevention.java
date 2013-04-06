package com.carboncraft.Paintball;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class HealthRegenPrevention implements Listener{
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void PreventRegen (EntityRegainHealthEvent event) {
	
		event.setCancelled(true);
	}

}
