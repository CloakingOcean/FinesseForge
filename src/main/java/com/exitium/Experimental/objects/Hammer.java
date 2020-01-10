package com.exitium.Experimental.objects;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Hammer {

	private double xRotation;
	private double yRotation;
	private double zRotation;
	
	private Location location;
	
	private final int xLength = 3;
	private final int zLength = 5;
	private final int yLength = 6;
	
	private final double divisor = 3.5;
	
	
	//y, z, x
	private Point[][][] points = {
		
		{
			{null, null, null},
			{null, null, null},
			{null, addP(DustColor.BROWN), null},
			{null, null, null},
			{null, null, null}
		},
		{
			{null, null, null},
			{null, null, null},
			{null, addP(DustColor.BROWN), null},
			{null, null, null},
			{null, null, null}
		},
		{
			{null, null, null},
			{null, null, null},
			{null, addP(DustColor.BROWN), null},
			{null, null, null},
			{null, null, null}
		},
		{
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
		},
		{
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
		},
		{
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
			{addP(DustColor.BLACK), addP(DustColor.BLACK), addP(DustColor.BLACK)},
		},
		
	};
	
	public Hammer(Location location, Plugin plugin) {
		this.location = location;
		
		initializeLocations();
		
		HammerShower shower = new HammerShower();
		
		shower.runTaskTimer(plugin, 20L, 20L);
	}
	
	private void initializeLocations() {
		for (int x = 0; x < xLength; x++) {
			for (int z = 0; z < zLength; z++) {
				for (int y = 0; y < yLength; y++) {
					
					Point point = points[y][z][x];
					
					if (point == null) {
						continue;
					}
					
					point.updateLocation(location.clone().add(x/divisor, y/divisor, z/divisor));
					
				}
			}
		}
	}
	
	
	private Point addP(DustColor color) {
		return new Point(color);
	}
	
	public void rotateXD(double degrees) {
		rotateXR(Math.toRadians(degrees));
	}
	
	public void rotateXD(double degrees, Location location, double radius) {
		rotateXR(Math.toRadians(degrees), location, radius);
	}
	
	public void rotateXR(double radians) {
		// Fill out later
	}
	
	public void rotateXR(double radians, Location location, double radius) {
		for (int x = 0; x < xLength; x++) {
			for (int z = 0; z < zLength; z++) {
				for (int y = 0; y < yLength; y++) {
					
					Point point = points[y][z][x];
					
					if (point == null) {
						continue;
					}
					
					Location pLoc = point.getLocation();
					Location cLoc = location;
					
					double pX = pLoc.getX();
					double pY = pLoc.getY();
					
//					double cX = cLoc.getX();
//					double cY = cLoc.getY();
					
					double cX = 0;
					double cY = 0;
//					
//					double pZT = pZ - cZ;
//					double pYT = pY - cY;
//					
//					
//					radians += Math.atan2(pY, pZ);
//					
//					
//					double targetZ = Math.sin(radians);
//					double targetY = Math.cos(radians);
					
					
					double newX = cX + (pX-cX) * Math.cos(radians) - (pY-cY) * Math.sin(radians);
					double newY = cY + (pX-cX)*Math.sin(radians) + (pY-cY) * Math.cos(x);
				
					
//					double targetY = radius * Math.cos(radians);
//					double targetZ = radius * Math.sin(radius);
//					
//					double targetX = 0;
//					
//					targetY += point.getLocation().getY();
//					targetZ += point.getLocation().getZ();
//					
//					targetX += point.getLocation().getX();
//					
					
					Bukkit.getLogger().info("Z: " + newX);
					Bukkit.getLogger().info("Y: " + newY);
//					Bukkit.getLogger().info("Z: " + targetY);
					Location newLocation = new Location(pLoc.getWorld(), newX, newY, pLoc.getZ());
					
					point.updateLocation(newLocation);
				}
			}
		}
	}
	
	/*
	 *	Internal class to wrap point information 
	 */
	
	class Point {
		
		private Location location;

		private float r = 0, g = 0, b = 0;
		
		public Point(DustColor color) {
			updateRGB(color);
		}
		
		public Point(Location location, DustColor color) {
			
			this.location = centerDirection(location);
			
			updateRGB(color);
		}
		
		public void updateLocation(Location location) {
			
			this.location = centerDirection(location);
		}
		
		public Location getLocation() {
			return this.location.clone();
		}
		
		private Location centerDirection(Location location) {
			Location modified = location.clone();
			
			modified.setYaw(0);
			modified.setPitch(0);
			
			return modified;
		}
		
		private void updateRGB(DustColor color) {
			switch (color) {
				case BROWN:
					r = (float) 146/255;
					g = (float) 115/255;
					b = (float) 15/255;
					break;
				case BLACK:
					r = (float) 2/255;
					g = (float) 2/255;
					b = (float) 2/255;
					break;
			}
		}
		
		public void display() {
			location.getWorld().spigot().playEffect(location, Effect.COLOURED_DUST, 0, 0, r, g, b, 1, 0, 150);
		}
		
	}
	
	enum DustColor {
		BROWN, BLACK;
	}
	
	class HammerShower extends BukkitRunnable {
		
		@Override
		public void run() {
			for (int x = 0; x < xLength; x++) {
				for (int z = 0; z < zLength; z++) {
					for (int y = 0; y < yLength; y++) {
						
						Point point = points[y][z][x];
						
						if (point == null) {
							continue;
						}
						
						Location pLoc = point.getLocation();
						
//						Location origin = Bukkit.getWorlds().get(0).getPlayers().get(0).getLocation().clone().add(-5, 0, 0);
//						
//						rotateXR(Math.PI/8, origin, 3);
						
						double angle = Math.atan2(pLoc.getY(), pLoc.getX());
						
						angle += Math.PI/90;
						
						double targetX = pLoc.getX() *Math.cos(angle) - pLoc.getY()- Math.sin(angle);
						double targetY = pLoc.getY() * Math.acos(angle) + x * Math.sin(angle);
						
						Location modified = pLoc.clone();
						modified.setX(targetX);
						modified.setY(targetY);
						
						Bukkit.getLogger().info("X: " + targetX);
						Bukkit.getLogger().info("Y: " + targetY);
						
						point.updateLocation(modified);
						
						point.display();
						
					}
				}
			}
		}
		
	}
}