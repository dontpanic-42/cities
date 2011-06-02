package com.dontpanic.cities.data;

import org.bukkit.entity.Player;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Plot {

	// the main data interacted with
	private String id;
	private int x = 0;
	private int z = 0;

	// some extra data kept handy for faster border checks
	private Vector minPoint;
	private Vector maxPoint;

	public Plot(String id, Selection sel, int pricelevel)
	{
		//set data
		this.id = id;
		this.minPoint = sel.getNativeMinimumPoint();
		this.maxPoint = sel.getNativeMaximumPoint();
	}

	public boolean insidePlot(int xLoc, int zLoc) {
		return !(xLoc < minPoint.getBlockX() || xLoc > maxPoint.getBlockX() || zLoc < minPoint.getBlockZ() || zLoc > maxPoint.getBlockZ());
	}

	public String[] getInformation() {
		String[] information = new String[1];
		information[0] = "Plot: "+id;
		return information;
	}
	
	public static final int BUY_OK = 101;
	public static final int BUY_FAIL = 102;
	public static final int SELL_OK = 201;
	public static final int SELL_FAIL = 202;
	

	public int buy(Player player) {
		// TODO Auto-generated method stub
		return BUY_FAIL;
	}

	public int sell(Player player) {
		// TODO Auto-generated method stub
		return SELL_FAIL;
	}

	public Vector getMinimumPoint() {
		return minPoint;
	}
	
	public Vector getMaximumPoint() {
		return maxPoint;
	}
}
