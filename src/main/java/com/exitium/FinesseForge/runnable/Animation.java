package com.exitium.FinesseForge.runnable;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import com.exitium.FinesseForge.objects.Hammer;

import net.md_5.bungee.api.ChatColor;

public class Animation extends BukkitRunnable {

	private Location location;
	private Plugin plugin;

	private Location offsetLocation;

	private int iterations = 0;

	private ArmorStand armorStand;
	private ArmorStand finalArmorStand;
	private Hammer hammer;

	private Location armLocation;

	private final double angle = Math.PI / 30;

	private int countdownIterations = -1;

	private final double armOffsetX = -0.36500000011176326;
	private final double armOffsetY = 1.4650000112876285;
	private final double armOffsetZ = 0;

	private int stage = 0;

	public Animation(Location location, Plugin plugin) {
		this.location = location;
		this.plugin = plugin;

		this.offsetLocation = location.clone().add(.9, 0, -1.5);
	}

	/**
	 * Down, up, down, up, down. (Lightning)
	 */

	@Override
	public void run() {
		if (iterations == 0) {

			Location armorStandLocation = offsetLocation.clone();
			armorStandLocation.setYaw(0);
			armorStandLocation.setPitch(0);

			armorStand = location.getWorld().spawn(armorStandLocation, ArmorStand.class);
//			armorStand.setVisible(false);
			armorStand.setArms(true);
			armorStand.setBasePlate(false);
			armorStand.setRightArmPose(new EulerAngle(Math.PI, 0, 0));
			hammer = new Hammer(offsetLocation.clone().add(-.28, 3, .2), plugin);

			armLocation = armorStandLocation.clone().add(armOffsetX, armOffsetY, armOffsetZ);

		}

		double angleInDegs = Math.toDegrees(armorStand.getRightArmPose().getX());

		if (stage == 0) {
			hammer.startRotating(angle * -1, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();

			armorStand.setRightArmPose(
					new EulerAngle(previousPose.getX() + angle, previousPose.getY(), previousPose.getZ()));

			if (angleInDegs >= 260) {
				stage++;
				location.getWorld().playEffect(location, Effect.ZOMBIE_CHEW_IRON_DOOR, 0);
				spawnParticles(location, 50);

			}
		} else if (stage == 1) {
			hammer.startRotating(angle, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();
			armorStand.setRightArmPose(
					new EulerAngle(previousPose.getX() - angle, previousPose.getY(), previousPose.getZ()));

			if (angleInDegs <= 180) {
				stage++;
			}

		} else if (stage == 2) {
			hammer.startRotating(angle * -1, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();

			armorStand.setRightArmPose(
					new EulerAngle(previousPose.getX() + angle, previousPose.getY(), previousPose.getZ()));

			if (angleInDegs >= 260) {
				stage++;
				location.getWorld().playEffect(location, Effect.ZOMBIE_CHEW_IRON_DOOR, 0);
				spawnParticles(location, 50);
			}
		} else if (stage == 3) {
			hammer.startRotating(angle, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();
			armorStand.setRightArmPose(
					new EulerAngle(previousPose.getX() - angle, previousPose.getY(), previousPose.getZ()));

			if (angleInDegs <= 180) {
				stage++;
			}
		} else if (stage == 4) {
			hammer.startRotating(angle * -1, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();

			armorStand.setRightArmPose(
					new EulerAngle(previousPose.getX() + angle, previousPose.getY(), previousPose.getZ()));

			if (angleInDegs >= 260) {
				stage++;
				location.getWorld().playEffect(location, Effect.ZOMBIE_CHEW_IRON_DOOR, 0);
				spawnParticles(location, 50);
			}

		} else if (stage == 5) {
			armorStand.getWorld().strikeLightningEffect(location);
			hammer.stopRotating();
			armorStand.remove();
			hammer.destroy();

			countdownIterations = iterations + 50;

			finalArmorStand = location.getWorld().spawn(location.clone().add(.9, .2, 0.2), ArmorStand.class);
			finalArmorStand.setRightArmPose(new EulerAngle(0, 0, 0));

			finalArmorStand.setVisible(false);
			finalArmorStand.setGravity(false);
			finalArmorStand.setArms(true);
			finalArmorStand.setItemInHand(new ItemStack(Material.NETHER_STAR));

			finalArmorStand.setCustomName(ChatColor.LIGHT_PURPLE + "Nether Star " + ChatColor.DARK_PURPLE + "(RARE)");
			finalArmorStand.setCustomNameVisible(true);

			stage++;
		}

		if (iterations >= countdownIterations && countdownIterations != -1) {
			finalArmorStand.remove();
			this.cancel();
		}

		iterations++;
	}

	private void spawnParticles(Location location, int amount) {
		for (int i = 0; i < amount; i++) {
			location.getWorld().playEffect(location, Effect.LAVA_POP, 0);
		}
	}

}