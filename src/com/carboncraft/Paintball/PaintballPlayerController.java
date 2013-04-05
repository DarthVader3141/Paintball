package com.carboncraft.Paintball;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class PaintballPlayerController {

	public HashMap<Player, PaintballPlayer> data;
	
	public PaintballPlayerController(Paintball plugin) {
		this.data = new HashMap<Player, PaintballPlayer>();
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.LIGHT_PURPLE+"This server runs the Paintball Plugin!");
			this.newPlayer(player);
			
			player.getInventory().clear();			//start inventory reset
			ItemStack snowballs = new ItemStack(Material.SNOW_BALL);
			snowballs.setAmount(16);
			for (int x=0;x<=5;x++){
				player.getInventory().setItem(x, snowballs);
			}
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chestmeta = (LeatherArmorMeta)chestplate.getItemMeta();
			chestmeta.setColor(this.getPaintballPlayer(player).getTeam().getArmorColor());
			chestplate.setItemMeta(chestmeta);
			player.getInventory().setChestplate(chestplate);			//end inventory reset
			
			try {
				player.teleport(plugin.getPbSpawn());
			}
			catch (NullPointerException e) { }	
		}
	}
	
	public void newPlayer(Player p) {
		data.put(p, new PaintballPlayer(p));
	}
	
	PaintballPlayer getPaintballPlayer(Player p) {
		return data.get(p);
	}
}
