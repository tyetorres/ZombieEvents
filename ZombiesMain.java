package me.masterplayert.zombieevents;

import org.bukkit.plugin.java.JavaPlugin;

public class ZombiesMain extends JavaPlugin{
    // Fired when plugin is first enabled
	private static ZombiesMain plugin;
    @Override
    public void onEnable() {
    	plugin = this;
    	getServer().getPluginManager().registerEvents(new ZombieEventsListener(), plugin);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
    
    public static JavaPlugin getInstance() {
    	return plugin;
    }
}
