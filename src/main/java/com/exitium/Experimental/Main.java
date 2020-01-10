package com.exitium.Experimental;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
			
			Hammer hammer = new Hammer(playerLocation.clone().add(5, 0, 0), this);
		}
		
		
		return true;
	}
	
	private void notPlayer(CommandSender sender) {
		sender.sendMessage("You must be a player to preform this command!");
	}
}
