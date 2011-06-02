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

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.World;

public class GlobalCityManager {

	private static final Logger logger = Logger.getLogger("Minecraft.Cities");
    private HashMap<String, CityManager> managers;
	private CitiesPlugin plugin;
    
    public GlobalCityManager(CitiesPlugin plugin) {
        this.plugin = plugin;
        managers = new HashMap<String, CityManager>();
    }
    
    /**
     * Returns the manager for a particular world.
     */
    public CityManager get(World world) {
    	CityManager manager = managers.get(world.getName());
    	if(manager == null) {
    		manager = createNewCityManager(world);
    	}
        return manager;
    }

	private CityManager createNewCityManager(World world) {
		CityManager manager = new CityManager(plugin);
		managers.put(world.getName(), manager);
		logger.info("Cities: " + "createt new CityManager for " + "'" + world.getName() + "'");
		return manager;
	}
   
}
