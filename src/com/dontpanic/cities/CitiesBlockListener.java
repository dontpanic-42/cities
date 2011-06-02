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
