package com.carboncraft.Paintball;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class MyFirstPluginPlayerJoinListener implements Listener {
	
	private PaintballPlayerController playerController;
	private Paintball plugin;
	
	public MyFirstPluginPlayerJoinListener(Paintball plugin, PaintballPlayerController pc) {
		this.plugin = plugin;
		this.playerController = pc;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.LIGHT_PURPLE+"This server runs the Paintball Plugin!");
		playerController.newPlayer(player);
		
		player.getInventory().clear();			//start inventory reset
		ItemStack snowballs = new ItemStack(Material.SNOW_BALL);
		snowballs.setAmount(16);
		for (int x=0;x<=5;x++){
			player.getInventory().setItem(x, snowballs);
		}
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestmeta = (LeatherArmorMeta)chestplate.getItemMeta();
		chestmeta.setColor(playerController.getPaintballPlayer(player).getTeam().getArmorColor());
		chestplate.setItemMeta(chestmeta);
		player.getInventory().setChestplate(chestplate);			//end inventory reset
		
		try {
			player.teleport(plugin.getPbSpawn());
		}
		catch (NullPointerException e) { }	
	}
	
}
