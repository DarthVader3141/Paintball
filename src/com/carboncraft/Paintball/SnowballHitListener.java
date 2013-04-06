package com.carboncraft.Paintball;

import java.util.logging.Logger;

import net.minecraft.server.v1_5_R2.Packet70Bed;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_5_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

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
		
		if (event.getEntity() instanceof ItemFrame) {
				event.setCancelled(true);
		}
			
		if (!((event.getEntity()) instanceof Player)){
			
		}
		
		if (!(damager instanceof Snowball)) {
			return;
		}
		
		Projectile snowball = (Projectile)event.getDamager();
		if (!((Entity)snowball.getShooter() instanceof Player)){
			return;
		}
		
		Player shooter = (Player)snowball.getShooter();
		String shootername = (String)shooter.getName();
		Player target = (Player)event.getEntity();
		String targetname = (String)target.getName();
		
		//Location p = shooter.getLocation();
		//Packet62NamedSoundEffect soundPacket = new Packet62NamedSoundEffect("random.successful_hit", p.getX(), p.getY(), p.getZ(), 0.18f, 0.45f);
		Packet70Bed hitEventPacket = new Packet70Bed(6, 0); //Notify the client that it hit a player - to play sound effect without naming the sound in the server code
		((CraftPlayer) shooter).getHandle().playerConnection.sendPacket(hitEventPacket);
		
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
		
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta helmetmeta = (LeatherArmorMeta)helmet.getItemMeta();
		int hits = (playerController.getPaintballPlayer(target).getHits());
		if (hits == 0) {
			
			helmetmeta.setColor(Color.GREEN);
		}
		
		else if (hits == 1) {
			
			helmetmeta.setColor(Color.YELLOW);
		}
		
		else if (hits == 2) {
			
			helmetmeta.setColor(Color.ORANGE);
		}
		
		else if (hits == 3) {
			
			helmetmeta.setColor(Color.RED);
		}
		
		helmet.setItemMeta(helmetmeta);
		target.getInventory().setHelmet(helmet);

		int health = 20 - (playerController.getPaintballPlayer(target).getHits()*5);
		health = health>0 ? health : 0;
		target.setHealth(health);
		
	}
}


