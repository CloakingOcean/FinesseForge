package com.exitium.Experimental.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import com.exitium.Experimental.objects.Hammer;

public class Animation extends BukkitRunnable{
	
	Location location;
	Plugin plugin;
	
	int iterations = 0;
	
	private ArmorStand armorStand;
	private Hammer hammer;
	
	private Location armLocation;
	
	final double angle = Math.PI/90;
	
	
	final double armOffsetX = -0.36500000011176326;
	final double armOffsetY = 1.4650000112876285;
	final double armOffsetZ = 0;
	
	int stage = 0;
	
	public Animation(Location location, Plugin plugin) {
		this.location = location;
		this.plugin = plugin;
	}
	
	/**
	 * Down, up, down, up, down. (Lightning)
	 */
	
	@Override
	public void run() {
		if (iterations == 0) {
			
			Location armorStandLocation = location.clone();
			armorStandLocation.setYaw(0);
			armorStandLocation.setPitch(0);
			
			armorStand = location.getWorld().spawn(armorStandLocation, ArmorStand.class);
//			armorStand.setVisible(false);
			armorStand.setArms(true);
			armorStand.setBasePlate(false);
			armorStand.setRightArmPose(new EulerAngle(Math.PI, 0, 0));
			hammer = new Hammer(location.clone().add(-.28, 3, .2), plugin);
			
			armLocation = armorStandLocation.clone().add(armOffsetX, armOffsetY, armOffsetZ);
			
		}
		
		double angleInDegs = Math.toDegrees(armorStand.getRightArmPose().getX());
		
		if (iterations == 20) {
			hammer.startRotating(angle * -1, armLocation);
			
			EulerAngle previousPose = armorStand.getRightArmPose();
			
			armorStand.setRightArmPose(new EulerAngle(previousPose.getX() + angle, previousPose.getY(), previousPose.getZ()));
		} else if (iterations > 20 && angleInDegs < 270) {
			hammer.startRotating(angle, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();
			
			armorStand.setRightArmPose(new EulerAngle(previousPose.getX() + angle, previousPose.getY(), previousPose.getZ()));
		} else if (iterations > 40 && angleInDegs >= 180){
			hammer.startRotating(angle * -1, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();
			armorStand.setRightArmPose(new EulerAngle(previousPose.getX() - angle, previousPose.getY(), previousPose.getZ()));
		} else if (iterations > 60 && angleInDegs < 270) {
			hammer.startRotating(angle, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();
			
			armorStand.setRightArmPose(new EulerAngle(previousPose.getX() + angle, previousPose.getY(), previousPose.getZ()));
		} else if (iterations > 80 && angleInDegs >= 180) {
			hammer.startRotating(angle * -1, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();
			armorStand.setRightArmPose(new EulerAngle(previousPose.getX() - angle, previousPose.getY(), previousPose.getZ()));
		} else if (iterations > 100 && angleInDegs < 270) {
			hammer.startRotating(angle, armLocation);
			EulerAngle previousPose = armorStand.getRightArmPose();
			
			armorStand.setRightArmPose(new EulerAngle(previousPose.getX() + angle, previousPose.getY(), previousPose.getZ()));
		} else if (iterations > 120 && angleInDegs >= 270) {
			armorStand.getWorld().strikeLightningEffect(armorStand.getLocation());
		}
		iterations++;
	}
	
	private double calculateDistance(Location point, Location center) {
		double pY = point.getY();
		double pZ = point.getZ();
		
		double cY = center.getY();
		double cZ = center.getZ();
		
		return (pY - cY) + (pZ - cZ);
	}
	
}