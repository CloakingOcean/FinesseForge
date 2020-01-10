package com.exitium.Experimental;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

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
			
			ArmorStand armorStand = playerLocation.getWorld().spawn(playerLocation, ArmorStand.class);
			
			
			armorStand.setArms(true);
			armorStand.setGravity(false);
			armorStand.setVisible(false);
			
			armorStand.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
		}
		
		
		return true;
	}
	
	private void notPlayer(CommandSender sender) {
		sender.sendMessage("You must be a player to preform this command!");
	}
}
