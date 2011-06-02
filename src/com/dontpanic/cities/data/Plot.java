/*
 * Cities
 * Copyright (C) 2011 dontpanic_42 <hi_manuel@web.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
