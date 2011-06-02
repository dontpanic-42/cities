package com.dontpanic.cities.data;

import java.util.HashMap;
import java.util.Set;


public class City {

	HashMap<String, Plot> plots;
	
	// the main data interacted with
	private String id;
	private int x = 0;
	private int z = 0;
	private int radius = 0;
	private boolean round = false;

	// some extra data kept handy for faster border checks
	private int maxX;
	private int minX;
	private int maxZ;
	private int minZ;
	private int radiusSquared;
	private double DefiniteSquare;

	public City(String id, int x, int z, int radius, boolean round)
	{
		plots = new HashMap<String, Plot>();
		//set data
		this.id = id;
		this.x = x;
		this.z = z;
		this.radius = radius;
		this.maxX = x + radius;
		this.minX = x - radius;
		this.maxZ = z + radius;
		this.minZ = z - radius;
		this.radiusSquared = radius * radius;
		this.round = round;
		this.DefiniteSquare = Math.sqrt(.5 * this.radiusSquared);
	}

	public String getID() { return id; }
	public boolean getShape() { return round; }
	public int getX() { return x; }
	public int getZ() { return z; }
	public int getRadius() { return radius; }
	
	public void setRadius(int radius)
	{
		this.radius = radius;
		this.maxX = x + radius;
		this.minX = x - radius;
		this.maxZ = z + radius;
		this.minZ = z - radius;
		this.radiusSquared = radius * radius;
		this.DefiniteSquare = Math.sqrt(.5 * this.radiusSquared);
	}

	public boolean insideCity(int xLoc, int zLoc) {
		// square border
		if (!round) {
			return !(xLoc < minX || xLoc > maxX || zLoc < minZ || zLoc > maxZ);
		} else { // round border
			// elegant round border checking algorithm is from rBorder by Reil with almost no changes, all credit to him for it
			double X = Math.abs(x - xLoc);
			double Z = Math.abs(z - zLoc);

			if (X < DefiniteSquare && Z < DefiniteSquare)
				return true;	// Definitely inside
			else if (X >= radius || Z >= radius)
				return false;	// Definitely outside
			else if (X * X + Z * Z < radiusSquared)
				return true;	// After further calculation, inside
			else
				return false;	// Apparently outside, then
		}
	}

	public String[] getInformation() {
		String[] information = new String[1];
		information[0] = "City: "+id+" "+x+" "+z+" "+radius+" "+(round?"circle":"square");
		return information;
	}

	public void addPlot(String id, Plot plot) {
		plots.put(id, plot);
	}

	public Plot getPlot(String id) {
		return plots.get(id);
	}

	public boolean removePlot(String id) {
		Plot removed = plots.remove(id);
		if(removed != null) {
			return true;
		}
		return false;
	}

	public String getPlotList() {
		String list = "";
		Set<String> set = plots.keySet();
		for(String s : set) {
			list += s+"  ";
		}
		return list;
	}
}
