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
		this.location = location.clone().subtract(0.42857142857, 0.85714285714, 0.71428571428);
		
		initializeLocations();
		
		HammerShower shower = new HammerShower();
		
		shower.runTaskTimer(plugin, 20L, 1L);
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
		
		this.location = points[0][2][1].getLocation();
	}
	
	
	private Point addP(DustColor color) {
		return new Point(color);
	}
	
	public void rotateXD(double degrees) {
		rotateXR(Math.toRadians(degrees));
	}
	
//	public void rotateXD(double degrees, Location location, double radius) {
//		rotateXR(Math.toRadians(degrees), location, radius);
//	}
	
	public void rotateXR(double radians) {
		// Fill out later
	}
	
	public void rotate(double angle, Location location) {
		
		
		for (int x = 0; x < xLength; x++) {
			for (int z = 0; z < zLength; z++) {
				for (int y = 0; y < yLength; y++) {
		
					Point point = points[y][z][x];
					
					if (point == null) {
						continue;
					}
				
					
					Location pLoc = point.getLocation();
					
					Location cLoc = location;
					
					Bukkit.getLogger().info("cLocY : " + cLoc.getY());
					Bukkit.getLogger().info("cLocZ : " + cLoc.getZ());
					
					Bukkit.getLogger().info("pLocY : " + pLoc.getY());
					Bukkit.getLogger().info("pLocZ : " + pLoc.getZ());
					
					Bukkit.getLogger().info("Before : " + pLoc.toString());
					
					pLoc.subtract(0, cLoc.getY(), cLoc.getZ());
					
					Bukkit.getLogger().info("After : " + pLoc.toString());
					Bukkit.getLogger().info("After Z: " + pLoc.getZ());
					Bukkit.getLogger().info("After Y: " + pLoc.getY());
					
					angle += Math.atan2(pLoc.getY(), pLoc.getZ());
					
					double distance = Math.sqrt(Math.pow(pLoc.getZ(), 2) + Math.pow(pLoc.getY(), 2));
					
					
					
					double targetZ = distance * Math.cos(angle);
					double targetY = distance * Math.sin(angle);
					
					targetZ += cLoc.getZ();
					targetY += cLoc.getY();
					
					
					Location modified = pLoc.clone();
					modified.setZ(targetZ);
					modified.setY(targetY);
					
					point.updateLocation(modified);
					
					point.display();
		
				}
			}
		}
		
		
		
		
		
	}
	
	
	
	public Location getLocation() {
		return this.location.clone();
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
						
						
						point.display();
						
					}
				}
			}
		}
		
	}
}