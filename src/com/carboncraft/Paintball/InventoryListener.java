package com.carboncraft.Paintball;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class InventoryListener implements Listener{
	
	private PaintballPlayerController playerController;
	private Paintball plugin;
	
	public InventoryListener(Paintball plugin, PaintballPlayerController pc) {
		this.playerController = pc;
		this.plugin = plugin;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void PreventMeddling (InventoryClickEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void RemovePlayerItemDrops (PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void RemoveItemDrops (ItemSpawnEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void ResetInventoryOnDeath (PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		ItemStack snowballs = new ItemStack(Material.SNOW_BALL);
		snowballs.setAmount(16);
		for (int x=0;x<=5;x++){
			player.getInventory().setItem(x, snowballs);
		}
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestmeta = (LeatherArmorMeta)chestplate.getItemMeta();
		chestmeta.setColor(playerController.getPaintballPlayer(player).getTeam().getArmorColor());
		chestplate.setItemMeta(chestmeta);
		player.getInventory().setChestplate(chestplate);
		
		if (plugin.getPbSpawn() != null) {
			event.setRespawnLocation(plugin.getPbSpawn());
		}
		
	}
	
}