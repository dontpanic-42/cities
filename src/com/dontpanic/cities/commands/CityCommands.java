package com.dontpanic.cities.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dontpanic.cities.CitiesPlugin;
import com.dontpanic.cities.CityManager;
import com.dontpanic.cities.data.City;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;

public class CityCommands {

    @Command(aliases = {"create"},
            usage = "<id> <circle|square> <radius>",
            desc = "Create a circular/square city with radius r",
            flags = "", min = 3, max = 3)
    public static void create(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
    	Player player = plugin.checkPlayer(sender);
    	String id = args.getString(0);
    	String shape = args.getString(1);
    	int radius = args.getInteger(2);
    	boolean round = false;
    	if(shape.equals("circle") || shape.equals("c")) {
    		round = true;
    	} else if(shape.equals("square") || shape.equals("s")) {
    		round = false;
    	} else {
    		throw new CommandException("The shape must be either circle or sqare");
    	}
    	int x = player.getLocation().getBlockX();
    	int z = player.getLocation().getBlockZ();
    	
    	City city = new City(id, x, z, radius, round);
    	CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	if(manager.getCity(id) != null) {
    		throw new CommandException("There already exists a city with this id");
    	}
    	manager.addCity(city);
    	sender.sendMessage("City "+id+" has been created");
    }
    
    @Command(aliases = {"remove"},
            usage = "<id>",
            desc = "Removes a city",
            flags = "", min = 1, max = 1)
    public static void remove(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
    	Player player = plugin.checkPlayer(sender);
    	String id = args.getString(0);
    	CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	if(!manager.removeCity(id)) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
    	sender.sendMessage("City "+id+" has been successefully removed");
    }
    
    @Command(aliases = {"info"},
            usage = "<id>",
            desc = "Information of a city",
            flags = "", min = 1, max = 1)
    public static void info(CommandContext args, CitiesPlugin plugin, 
    		CommandSender sender) throws CommandException {
    	Player player = plugin.checkPlayer(sender);
    	String id = args.getString(0);
    	CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
    	String[] information = manager.getInformation(id);
    	if(information == null) {
    		throw new CommandException("There is no city with this id in your world.");
    	}
    	for(String info : information) {
    		sender.sendMessage(info);
    	}
    }
    
    @Command(aliases = {"list"},
            usage = "",
            desc = "Get a list of cities",
            flags = "", min = 0, max = 3)
//    @CommandPermissions({"worldguard.region.list"})
    public static void list(CommandContext args, CitiesPlugin plugin,
            CommandSender sender) throws CommandException {

        Player player = plugin.checkPlayer(sender);
        CityManager manager = plugin.getGlobalCityManager().get(player.getWorld());
        sender.sendMessage(manager.getCityList());
    }
}
