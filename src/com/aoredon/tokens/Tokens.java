package com.aoredon.tokens;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.aoredon.tokens.command.CommandTBalance;
import com.aoredon.tokens.command.CommandTBuy;
import com.aoredon.tokens.command.CommandTGive;
import com.aoredon.tokens.command.CommandTShop;
import com.aoredon.tokens.listener.ListenerChest;
import com.aoredon.tokens.listener.ListenerEntity;
import com.aoredon.tokens.listener.ListenerPlayer;
import com.aoredon.tokens.player.TPlayer;
import com.aoredon.tokens.shop.TShop;

/**
 * The main plugin class. Tokens is a plugin which awards players upon killing
 * things with a varying amount of tokens. The tokens can then be spent in the
 * shop to purchase powerful items.
 * 
 * @author Alex Guest
 */
public class Tokens extends JavaPlugin {
	// The logo for the plugin.
	public static final String LOGO = ChatColor.WHITE + "[" + 
						ChatColor.RED + "Tokens" +
							ChatColor.WHITE + "]     ";
	// How much a single token is worth in the server's currency.
	public static final int TOKEN_WORTH = 100;
	
	// A hash map which is essentially a registry to keep track of all the
	// players on the server.
	private HashMap<String, TPlayer> tPlayers;
	// An instance of the shop for the plugin.
	private TShop tShop;

	/**
	 * Called when the plugin is enabled. It randomises the random generator
	 * seed, then initialises the player registry and shop. Next it registers
	 * all event listeners and sets executors for the plugin's commands.
	 * Finally it loops through each player on the server and adds them to the
	 * player registry.
	 */
	public void onEnable() {
		try {
			// Ensure that the random generator has a random seed.
			Utils.RANDOM_GENERATOR.setSeed(System.nanoTime());
			
			// Initialise the player registry and the token shop.
			this.tPlayers = new HashMap<String, TPlayer>();
			this.tShop = new TShop(this);
			
			// Register event listeners.
			Bukkit.getPluginManager().registerEvents(new ListenerPlayer(this), this);
			Bukkit.getPluginManager().registerEvents(new ListenerChest(this), this);
			Bukkit.getPluginManager().registerEvents(new ListenerEntity(this), this);
			
			// Set executors for the commands.
			this.getCommand("tbalance").setExecutor(new CommandTBalance(this));
			this.getCommand("tbuy").setExecutor(new CommandTBuy(this));
			this.getCommand("tgive").setExecutor(new CommandTGive(this));
			this.getCommand("tshop").setExecutor(new CommandTShop(this));
			
			// Add all online players to the player registry.
			this.addAllPlayers();
		} catch (Exception exception) {
			// Inform operators of the error.
			Utils.reportError(exception);
		}
	}

	/**
	 * Called when the plugin is disabled. This method just saves and then
	 * wipes the player data.
	 */
	public void onDisable() {
		// Loop through each player in the registry.
		for (Entry<String, TPlayer> entry : this.tPlayers.entrySet()) {
			// Save their data.
			entry.getValue().getData().saveConfig();
		}
		// Clear the registry of players.
		this.tPlayers.clear();
	}

	/**
	 * Loops through every player that is currently online and adds them to
	 * the player registry.
	 */
	public void addAllPlayers() {
		// Clears the registry of players.
		this.getTPlayers().clear();
		
		// Loops through each online player.
		for (Player player : Bukkit.getOnlinePlayers()) {
			// Adds the player to the player registry.
			this.getTPlayers().put(player.getUniqueId().toString(), new TPlayer(this, player));
		}
	}
	
	/**
	 * Gets the player registry.
	 * 
	 * @return	the player registry
	 */
	public HashMap<String, TPlayer> getTPlayers() {
		return this.tPlayers;
	}

	/**
	 * Gets the token shop.
	 * 
	 * @return	the token shop
	 */
	public TShop getTShop() {
		return this.tShop;
	}
}
