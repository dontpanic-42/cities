package com.dontpanic.cities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.dontpanic.cities.commands.CityCommands;
import com.dontpanic.cities.commands.GeneralCommands;
import com.dontpanic.cities.commands.PlotCommands;
import com.dontpanic.cities.database.DatabaseInterface;
import com.sk89q.bukkit.migration.*;
import com.sk89q.minecraft.util.commands.*;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import java.util.logging.Logger;

/**
 * The main class for Cities as a Bukkit plugin.
 * 
 * @author dontpanic_42
 */
public class CitiesPlugin extends JavaPlugin {
	
    protected static final Logger logger = Logger.getLogger("Minecraft.Cities");
    protected final CommandsManager<CommandSender> commands;
    protected final GlobalCityManager globalCityManager;
 
    
    /**
     * Processes queries for permissions information. The permissions manager
     * is from WorldEdit and it automatically handles looking up permissions
     * systems and picking the right one. Cities just needs to call
     * the permission methods.
     */
    protected PermissionsResolverManager perms;

    /**
     * Construct objects. Actual loading occurs when the plugin is enabled, so
     * this merely instantiates the objects.
     */
    public CitiesPlugin() {
    	globalCityManager = new GlobalCityManager(this);
    	
    	final CitiesPlugin plugin = this;
        commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender player, String perm) {
                return plugin.hasPermission(player, perm);
            }
        };
        
        // Register command classes
        commands.register(GeneralCommands.class);
        commands.register(CityCommands.class);
        commands.register(PlotCommands.class);
    }
    
    /**
     * Called on plugin enable.
     */
    public void onEnable() {
        // Need to create the plugins/Cities folder
        getDataFolder().mkdirs();

        // Set up permissions
        perms = new PermissionsResolverManager(
                getConfiguration(), getServer(), "Cities", logger);
        perms.load();
        
        // Load the configuration
        //configuration.load();
        DatabaseInterface.load();

        // Load permissions
        (new PermissionsResolverServerListener(perms)).register(this);

        // Register events
        (new CitiesBlockListener(this)).registerEvents();
        
        logger.info("Cities " + this.getDescription().getVersion() + " enabled");
    }

    /**
     * Called on plugin disable.
     */
    public void onDisable() {
    	DatabaseInterface.save();
        logger.info("Cities " + this.getDescription().getVersion() + " disabled");
    }
    
    /**
     * Handle a command.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {
        try {
            commands.execute(cmd.getName(), args, sender, this, sender);
        } catch (CommandPermissionsException e) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.WHITE + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.WHITE + e.getMessage());
            sender.sendMessage(ChatColor.WHITE + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                sender.sendMessage(ChatColor.WHITE + "Number expected, string received instead.");
            } else {
                sender.sendMessage(ChatColor.RED + "An error has occurred. See console.");
                e.printStackTrace();
            }
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }
        
        return true;
    }
    
    /**
     * Checks permissions. (Invokes the permissions resolver)
     */
    public boolean hasPermission(CommandSender sender, String perm) {
        if (sender instanceof Player) {
            return perms.hasPermission(((Player) sender).getName(), perm);
        }
        return false;
    }
    
    /**
     * Checks permissions and throws an exception if permission is not met.
     */
    public void checkPermission(CommandSender sender, String perm)
            throws CommandPermissionsException {
        if (!hasPermission(sender, perm)) {
            throw new CommandPermissionsException();
        }
    }
    
    /**
     * Checks to see if the sender is a player, otherwise throw an exception.
     */
    public Player checkPlayer(CommandSender sender)
            throws CommandException {
        if (sender instanceof Player) {
            return (Player) sender;
        } else {
            throw new CommandException("A player is expected.");
        }
    }

    /**
     * Gets a copy of the WorldEdit plugin.
     */
    public WorldEditPlugin getWorldEdit() throws CommandException {
        Plugin worldEdit = getServer().getPluginManager().getPlugin("WorldEdit");
        if (worldEdit == null) {
            throw new CommandException("WorldEdit does not appear to be installed.");
        }
        
        if (worldEdit instanceof WorldEditPlugin) {
            return (WorldEditPlugin) worldEdit;
        } else {
            throw new CommandException("WorldEdit detection failed (report error).");
        }
    }
    
    public GlobalCityManager getGlobalCityManager() {
    	return globalCityManager;
    }
}