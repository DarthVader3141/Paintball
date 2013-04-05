package com.carboncraft.Paintball;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener{
	
	@EventHandler (priority = EventPriority.NORMAL)
		public void PreventBuilding (BlockBreakEvent event) {
			event.setCancelled(true);
		}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void PreventPlacing (BlockPlaceEvent event) {
		event.setCancelled(true);
		}
}
