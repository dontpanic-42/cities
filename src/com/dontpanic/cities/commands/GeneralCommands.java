package com.dontpanic.cities.commands;

import org.bukkit.command.CommandSender;

import com.dontpanic.cities.CitiesPlugin;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.NestedCommand;

public class GeneralCommands {

	@Command(aliases = {"city"},
            desc = "City management commands")
    @NestedCommand({CityCommands.class})
    public static void city(CommandContext args, CitiesPlugin plugin,
            				CommandSender sender) throws CommandException {
    }
	
	@Command(aliases = {"plot"},
            desc = "Plot management commands")
    @NestedCommand({PlotCommands.class})
    public static void plot(CommandContext args, CitiesPlugin plugin,
            				CommandSender sender) throws CommandException {
    }
}
