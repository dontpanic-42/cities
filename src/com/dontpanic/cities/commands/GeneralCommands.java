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
