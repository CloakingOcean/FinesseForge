package com.exitium.FinesseForge;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.exitium.FinesseForge.runnable.Animation;

public class Main extends JavaPlugin implements Listener {

	private Animation animation;

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
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

		Animation animation = new Animation(event.getClickedBlock().getLocation(), this);
		animation.runTaskTimer(this, 0L, 1L);
	}
}
