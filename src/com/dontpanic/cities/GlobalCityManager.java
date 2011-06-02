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
