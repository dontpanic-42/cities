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

package com.dontpanic.cities;

import org.bukkit.event.block.BlockListener;
import org.bukkit.plugin.PluginManager;

public class CitiesBlockListener extends BlockListener {

    private CitiesPlugin plugin;

    public CitiesBlockListener(CitiesPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerEvents() {

        PluginManager pm = plugin.getServer().getPluginManager();
        
        
    }
    
//    protected WorldConfiguration getWorldConfig(World world) {
//        return plugin.getGlobalConfiguration().get(world);
//    }
//    
//    protected WorldConfiguration getWorldConfig(Player player) {
//        return plugin.getGlobalConfiguration().get(player.getWorld());
//    }
}
