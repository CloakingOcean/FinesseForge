package com.exitium.Experimental;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.exitium.Experimental.objects.Hammer;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("ex")) {
			if (!(sender instanceof Player)) {
				notPlayer(sender);
				return true;
			}
			
			Player player = (Player) sender;
			Location playerLocation = player.getLocation();
			
			Location particleLocation = playerLocation.clone().add(playerLocation.getDirection().clone().normalize());
			
			float r = (float) 146/255, g = (float) 115/255, b = (float) 14/255;
			
			particleLocation.getWorld().spigot().playEffect(particleLocation, Effect.COLOURED_DUST, 0, 0, r, g, b, 1, 0, 150);
			particleLocation.getWorld().playEffect(playerLocation.clone().add(playerLocation.getDirection().clone().normalize().multiply(-1)), Effect.LAVA_POP, 0);
			
			Hammer hammer = new Hammer(playerLocation.clone().add(0, 0, 0), this);
			
			ArmorStand armorStand = playerLocation.getWorld().spawn(playerLocation, ArmorStand.class);
			
			Bukkit.broadcastMessage("ArmorStand location: " + armorStand.getLocation());
			Bukkit.broadcastMessage("Before location: " + armorStand.getLocation());
			
			new BukkitRunnable() {
				final double armOffsetX = -0.36500000011176326;
				final double armOffsetY = 1.4650000112876285;
				final double armOffsetZ = 0;
				
				final Location standLocation = armorStand.getLocation().clone().add(armOffsetX, armOffsetY, armOffsetZ);
				
				@Override
				public void run() {
					
					Bukkit.broadcastMessage("After location: " + standLocation);
					
					Location modified = armorStand.getLocation().clone();
					
					modified.setPitch(0);
					modified.setYaw(0);
					
					armorStand.teleport(modified);
					
					armorStand.setGravity(false);
					armorStand.setArms(true);
					standLocation.getWorld().spigot().playEffect(standLocation, Effect.COLOURED_DUST, 0, 0, 255, 255, 255, 1, 0, 150);
				}
			}.runTaskTimer(this, 0L, 1L);
		}
		
		
		return true;
	}
	
	private void notPlayer(CommandSender sender) {
		sender.sendMessage("You must be a player to preform this command!");
	}
}
