package com.exitium.Experimental;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.exitium.Experimental.runnable.Animation;

public class Main extends JavaPlugin implements Listener{

	private Animation animation;
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
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
			
//			Location particleLocation = playerLocation.clone().add(playerLocation.getDirection().clone().normalize());
//			
//			float r = (float) 146/255, g = (float) 115/255, b = (float) 14/255;
//			
//			particleLocation.getWorld().spigot().playEffect(particleLocation, Effect.COLOURED_DUST, 0, 0, r, g, b, 1, 0, 150);
//			particleLocation.getWorld().playEffect(playerLocation.clone().add(playerLocation.getDirection().clone().normalize().multiply(-1)), Effect.LAVA_POP, 0);
			
//			Hammer hammer = new Hammer(playerLocation.clone().add(0, 0, 0), this);
		}
		
		
		return true;
	}
	
	@EventHandler
	public void onLootBoxOpen(PlayerInteractEvent event) {
		if (event.getClickedBlock() == null) {
			return;
		}

		Block block = event.getClickedBlock();

		if (block.getType() != Material.ENDER_CHEST || event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		event.setCancelled(true);
		
		if (animation != null) {
			animation.cancel();
		}
		
		Animation animation = new Animation(event.getClickedBlock().getLocation().clone().add(0, 0, -2), this);
		animation.runTaskTimer(this, 0L, 1L);
	}
	
	private void notPlayer(CommandSender sender) {
		sender.sendMessage("You must be a player to preform this command!");
	}
}
