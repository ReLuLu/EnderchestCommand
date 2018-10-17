package de.web.relulu.EnderchestCommand;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import de.web.relulu.EnderchestCommand.commands.CommandEnderchest;


/**
 * Hauptklasse für das EnderchestCommand Plugin.
 * 
 * @author ReLuLu
 *
 */
public class EnderchestMain extends JavaPlugin {

	private FileConfiguration cfg = getConfig();
	private PluginDescriptionFile pdf = getDescription();
	
	/**
	 * Called after a plugin is loaded but before it has been enabled.
	 * When mulitple plugins are loaded, the onLoad() for all plugins is called before any onEnable() is called.
	 */
	@Override
	public void onLoad() {
		
	}
	
	/**
	 * Called when this plugin is enabled.
	 */
	@Override
	public void onEnable() {
		createConfig();
		if(cfg == null) {
	    	cfg = getConfig();
	    }
		// Listener registrieren
		//getServer().getPluginManager().registerEvents(new EnderchestListener(this), this));
		
		// Befehle registrieren
		// https://pastebin.com/6NsN6f6X sehr cooles Beispiel für TAB-Vervollständigung
		this.getCommand("enderchest").setExecutor(new CommandEnderchest(this));
		
		// abschließen
    	getLogger().info(pdf.getName() + " version " + pdf.getVersion() + " by " + pdf.getAuthors().get(0) + " enabled! :)");
	}
	
	/**
	 * Called when this plugin is disabled.
	 */
	@Override
	public void onDisable() {
		
	}
	
    /**
     * Erstellt die Standardkonfig config.yml im Plugin-Verzeichnis 
     * sofern diese noch nicht existiert.
     */
    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("config.yml not found :( Creating one with default values...");
                saveDefaultConfig();
            } else {
                getLogger().info("config.yml found :) Loading...");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
	
}
