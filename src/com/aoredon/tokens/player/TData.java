package com.aoredon.tokens.player;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.aoredon.tokens.Tokens;
import com.aoredon.tokens.Utils;

/**
 * A class which stores each individual player's data for the plugin.
 * This class also handles saving the data to a flat file.
 * 
 * @author Alex Guest
 */
public class TData {
	// An instance of the main plugin.
	private Tokens plugin;
	// The file that the data will be saved to.
	private File file;
	// The configuration file.
	private FileConfiguration config;
	// The player that the data is stored for.
	private TPlayer player;
	
	/**
	 * Takes in an instance of the main plugin and the player that the
	 * class will store data for. It also initialises the that the player's
	 * data will be stored in.
	 * 
	 * @param plugin	the instance of the main plugin
	 * @param player	the player to store data for
	 */
	public TData(Tokens plugin, TPlayer player) {
		this.plugin = plugin;
		this.player = player;
		this.file = new File(this.plugin.getDataFolder() +
				File.separator + "userdata" + File.separator +
					this.player.getPlayer().getName() + ".yml");
		
		this.initConfig();
	}
	
	/**
	 * This initialises the configuration file. If it needs to be
	 */
	public void initConfig() {
		// Checks to see if the file exists.
		if (!this.file.exists()) {
			try {
				// Creates it if it does not exist.
				this.file.createNewFile();
			} catch (IOException exception) {
				// Reports the input/output error the server operators.
				Utils.reportError(exception);
			}
			
			// Initialises the file configuration.
			this.config = YamlConfiguration.loadConfiguration(this.file);
			// Sets the default token balance to zero.
			this.config.set("currency.tokens", 0);
			
			try {
				// Saves the configuration.
				this.config.save(this.file);
			} catch (IOException exception) {
				// Reports the input/output error the server operators.
				Utils.reportError(exception);
			}
		} else {
			// Loads the configuration from the file.
			this.config = YamlConfiguration.loadConfiguration(this.file);
		}
	}
	
	/**
	 * Saves the configuration.
	 */
	public void saveConfig() {
		try {
			// Saves the configuration.
			this.config.save(this.file);
		} catch (IOException exception) {
			// Reports the input/output error the server operators.
			Utils.reportError(exception);
		}
	}
	
	/**
	 * Increments the tokens stored in the data.
	 * 
	 * @param amount	the amount to increment the tokens by
	 */
	public void incrementTokens(int amount) {
		// Sets the tokens in the configuration file.
		this.config.set("currency.tokens",
				(int) this.config.get("currency.tokens") + amount);
		// Saves the configuration file.
		this.saveConfig();
	}
	
	/**
	 * Decrements the tokens stored in the data.
	 * 
	 * @param amount	the amount to decrement the tokens by
	 */
	public void decrementTokens(int amount) {
		// Sets the tokens in the configuration file.
		this.config.set("currency.tokens", (int) this.config.get("currency.tokens") - amount);
		// Saves the configuration file.
		this.saveConfig();
	}
	
	/**
	 * Gets the player that the data is stored for.
	 * 
	 * @return	the player the data is stored for
	 */
	public TPlayer getPlayer() {
		return this.player;
	}
	
	/**
	 * Gets the current amount of tokens from the data.
	 * 
	 * @return	the current amount of tokens from the data
	 */
	public int getTokens() {
		return (int) this.config.get("currency.tokens");
	}
}
