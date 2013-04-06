package com.carboncraft.Paintball;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Paintball extends JavaPlugin implements Listener {
	
	private Location pbSpawn;
	private Logger log; //declares instance variable log
	private PaintballPlayerController playerController;
	
	@Override
	public void onEnable() {
		log = getLogger();//puts getLogger in "log"
		log.info("Initializing Paintball Plugin");
		playerController = new PaintballPlayerController(this);
		Server server = getServer();
		server.getPluginManager().registerEvents(new MyFirstPluginPlayerJoinListener(this, playerController), this);
		server.getPluginManager().registerEvents(new SnowballHitListener(playerController, log), this);
		server.getPluginManager().registerEvents(new InventoryListener(this, playerController), this);
		server.getPluginManager().registerEvents(new BuildListener(), this);
		server.getPluginManager().registerEvents(new WeaponUsage(playerController), this);
		server.getPluginManager().registerEvents(new HealthRegenPrevention(), this);
		server.getPluginManager().registerEvents(new EggHitListener(playerController), this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Deinitializing Paintball Plugin");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		
		if (cmd.getName().equalsIgnoreCase("armor")) {
			Player player = (Player)sender;
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chestmeta = (LeatherArmorMeta)chestplate.getItemMeta();
			chestmeta.setColor(playerController.getPaintballPlayer(player).getTeam().getArmorColor());
			chestplate.setItemMeta(chestmeta);
			player.getInventory().setChestplate(chestplate);
		}
		
		else if (cmd.getName().equalsIgnoreCase("score")) {
			Player player = (Player)sender;
			int pts = playerController.getPaintballPlayer(player).getPoints();
			if (pts <= 0){
				player.sendMessage(ChatColor.YELLOW+"You have 0 points.");
		}
			else player.sendMessage(ChatColor.YELLOW+"You have "+Integer.toString(pts)+" points.");
		}
		
		else if (cmd.getName().equalsIgnoreCase("setpbspawn")) {
			Player player = (Player)sender;

			pbSpawn = player.getLocation();
			double xcoord = pbSpawn.getX();
			double ycoord = pbSpawn.getY();
			double zcoord = pbSpawn.getZ();
			player.sendMessage(ChatColor.YELLOW+"Spawnpoint set at");
			player.sendMessage(ChatColor.YELLOW+"x: "+Double.toString(xcoord));
			player.sendMessage(ChatColor.YELLOW+"y: "+Double.toString(ycoord));
			player.sendMessage(ChatColor.YELLOW+"z: "+Double.toString(zcoord));
			logToFile(Double.toString(xcoord));
			logToFile(Double.toString(ycoord));
			logToFile(Double.toString(zcoord));
			
		}
		
		else if (cmd.getName().equalsIgnoreCase("broadcast")){
			org.bukkit.Bukkit.broadcastMessage("Troll");
			Player target=(Player)sender;
			target.damage(5);
		}
		
		else if (cmd.getName().equalsIgnoreCase("hits")){
			Player target=(Player)sender;
			int hits = playerController.getPaintballPlayer(target).getHits();
			target.sendMessage(Integer.toString(hits));
		}
		
		else if (cmd.getName().equalsIgnoreCase("countdown")){
			org.bukkit.Bukkit.broadcastMessage(ChatColor.GREEN+"3");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			org.bukkit.Bukkit.broadcastMessage(ChatColor.YELLOW+"2");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			org.bukkit.Bukkit.broadcastMessage(ChatColor.GOLD+"1");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			org.bukkit.Bukkit.broadcastMessage(ChatColor.RED+"GO");
		}
		return true;
	}
	
	public Location getPbSpawn() {
		return pbSpawn;
	}

	public void logToFile(String message)
	
	{

		try
		{
			File dataFolder = getDataFolder();
			if(!dataFolder.exists())
			{
				dataFolder.mkdir();
			}

			File saveTo = new File(getDataFolder(), "data.txt");
			if (!saveTo.exists())
			{
				saveTo.createNewFile();
			}


			FileWriter fw = new FileWriter(saveTo, true);

			PrintWriter pw = new PrintWriter(fw);

			pw.println(message);

			pw.flush();

			pw.close();

		} catch (IOException e)
		{

			e.printStackTrace();

		}
	}
	
	
}