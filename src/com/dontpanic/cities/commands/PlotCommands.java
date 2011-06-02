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

package com.dontpanic.cities.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dontpanic.cities.CitiesPlugin;
import com.dontpanic.cities.CityManager;
import com.dontpanic.cities.data.City;
import com.dontpanic.cities.data.Plot;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class PlotCommands {

	@Command(aliases = {"define"},
            usage = "<city-id> <id> <pricelevel>",
            desc = "Create a plot out of your current WorldEdit-selection",
            flags = "", min = 3, max = 3)
    public static void define(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
		Player player = plugin.checkPlayer(sender);
        WorldEditPlugin worldEdit = plugin.getWorldEdit();
        String cityid = args.getString(0);
        String id = args.getString(1);
        int pricelevel = args.getInteger(2);
        
        // Attempt to get the player's selection from WorldEdit
        Selection sel = worldEdit.getSelection(player);
        if (sel == null) {
            throw new CommandException("Select a region with WorldEdit first.");
        }
        int x1 = sel.getNativeMinimumPoint().getBlockX();
        int z1 = sel.getNativeMinimumPoint().getBlockZ();
        int x2 = x1 + sel.getWidth();
        int z2 = z1;
        int x3 = x1;
        int z3 = z1 + sel.getLength();
        int x4 = sel.getNativeMinimumPoint().getBlockX();
        int z4 = sel.getNativeMinimumPoint().getBlockZ();
        
        CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
        City city = manager.getCity(cityid);
    	if(city == null) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
    	// TODO Needs Testing!!!
        if(city.insideCity(x1, z1) && city.insideCity(x2, z2)
        		&& city.insideCity(x3, z3) && city.insideCity(x4, z4)) {
        	// TODO Debug [1]
        	sender.sendMessage("[1] inside ;)");
        	
        	// TODO inside Plot
        	if(city.getPlot(id) == null) {
        		Plot plot = new Plot(id, sel, pricelevel);
            	city.addPlot(id, plot);
        	} else {
        		throw new CommandException("There is already a plot with that id in "+cityid+".");
        	}
        } else {
        	throw new CommandException("You must select a region in a city.");
        }
    	
    }
	
	@Command(aliases = {"remove"},
            usage = "<city-id> <id>",
            desc = "Remove a plot",
            flags = "", min = 2, max = 2)
    public static void remove(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
		Player player = plugin.checkPlayer(sender);
		String cityid = args.getString(0);
    	String id = args.getString(1);
    	CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	City city = manager.getCity(cityid);
    	if(city == null) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
    	if(!city.removePlot(id)) {
    		throw new CommandException("There is no plot with this id in "+cityid+".");
    	}
    	sender.sendMessage("Plot "+id+" has been successefully removed");
    }
	
	@Command(aliases = {"buy"},
            usage = "<city-id> <id>",
            desc = "Buy a plot",
            flags = "", min = 2, max = 2)
    public static void buy(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
		Player player = plugin.checkPlayer(sender);
		String cityid = args.getString(0);
    	String id = args.getString(1);
    	CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	City city = manager.getCity(cityid);
    	if(city == null) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
    	Plot plot = city.getPlot(id);
    	if(plot == null) {
    		throw new CommandException("There is no plot with this id in "+cityid+".");
    	}
    	int result = plot.buy(player);
    	if(result == Plot.BUY_OK) {
    		sender.sendMessage("Congratulation, "+id+" is now yours.");
    	} else if (result == Plot.BUY_FAIL) {
    		sender.sendMessage("An error occured.");
    	}
    }
	
	@Command(aliases = {"sell"},
            usage = "<city-id> <id>",
            desc = "Sell a plot",
            flags = "", min = 2, max = 2)
    public static void sell(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
		Player player = plugin.checkPlayer(sender);
		String cityid = args.getString(0);
    	String id = args.getString(1);
    	CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	City city = manager.getCity(cityid);
    	if(city == null) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
    	Plot plot = city.getPlot(id);
    	if(plot == null) {
    		throw new CommandException("There is no plot with this id in "+cityid+".");
    	}
    	int result = plot.sell(player);
    	if(result == Plot.SELL_OK) {
    		sender.sendMessage("You selled "+id+" successefully.");
    	} else if (result == Plot.SELL_FAIL) {
    		sender.sendMessage("An error occured.");
    	}
    }
	
	@Command(aliases = {"info"},
            usage = "<city-id> <id>",
            desc = "Information of a plot",
            flags = "", min = 2, max = 2)
    public static void info(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
		Player player = plugin.checkPlayer(sender);
		String cityid = args.getString(0);
    	String id = args.getString(1);
    	CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	City city = manager.getCity(cityid);
    	if(city == null) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
    	Plot plot = city.getPlot(id);
    	if(plot == null) {
    		throw new CommandException("There is no plot with this id in "+cityid+".");
    	}
    	String[] information = plot.getInformation();
    	for(String info : information) {
    		sender.sendMessage(info);
    	}
    }
	
	@Command(aliases = {"view"},
            usage = "<city-id> <id>",
            desc = "Select a plot in WorldEdit",
            flags = "", min = 2, max = 2)
    public static void view(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
		Player player = plugin.checkPlayer(sender);
		String cityid = args.getString(0);
    	String id = args.getString(1);
    	WorldEditPlugin worldEdit = plugin.getWorldEdit();
    	CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	City city = manager.getCity(cityid);
    	if(city == null) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
    	Plot plot = city.getPlot(id);
    	if(plot == null) {
    		throw new CommandException("There is no plot with this id in "+cityid+".");
    	}
        Vector pt1 = plot.getMinimumPoint();
        Vector pt2 = plot.getMaximumPoint();
        CuboidSelection selection = new CuboidSelection(player.getWorld(), pt1, pt2);
        worldEdit.setSelection(player, selection);
        sender.sendMessage("Plot selected as a cuboid.");
    }
	
	@Command(aliases = {"list"},
            usage = "<city-id>",
            desc = "Get a list of plots",
            flags = "", min = 1, max = 2)
    public static void list(CommandContext args, CitiesPlugin plugin,
            CommandSender sender) throws CommandException {
		
		String cityid = args.getString(0);
        Player player = plugin.checkPlayer(sender);
        CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	City city = manager.getCity(cityid);
    	if(city == null) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
        sender.sendMessage(city.getPlotList());
    }
}
