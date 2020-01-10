package com.exitium.Experimental.objects;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;

public class Hammer {

	private double xRotation;
	private double yRotation;
	private double zRotation;
	
	private Location location;
	
	public Hammer(Location location) {
		this.location = location;
	}
	
	public void rotateD(double degrees) {
		rotateR(Math.toRadians(degrees));
	}
	
	public void rotateD(double degrees, Location location) {
		rotateR(Math.toRadians(degrees), location);
	}
	
	public void rotateR(double radians) {
		// Fill out later
	}
	
	public void rotateR(double radians, Location location) {
		
	}
	
	/*
	 *	Internal class to wrap point information 
	 */
	
	class Point {
		
		private Location location;
		
		private Effect effect;
		
		public Point(Location location, Effect effect) {
			
			Location modified = location.clone();
			
			modified.setYaw(0);
			modified.setPitch(0);
			
			this.location = modified;
		}
		
		public void display() {
//			
		}
		
		
		
	}
	
}