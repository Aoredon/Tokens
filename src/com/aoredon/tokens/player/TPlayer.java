package com.aoredon.tokens.player;

import org.bukkit.entity.Player;

import com.aoredon.tokens.Tokens;

/**
 * A class which keeps track of the player
 * and their data.
 * 
 * @author Alex Guest
 */
public class TPlayer {
	// An instance of the main plugin.
	private Tokens plugin;
	// The player to link with.
	private Player player;
	// An instance of the data.
	private TData data;
	
	/**
	 * Takes in an instance of the main plugin and the player to link with
	 * data.
	 * 
	 * @param plugin	the instance of the main plugin
	 * @param player	the player to link with
	 */
	public TPlayer(Tokens plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
		this.data = new TData(this.plugin, this);
	}
	
	/**
	 * Gets the Bukkit player.
	 * 
	 * @return	the bukkit player
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Gets the player's data
	 * 
	 * @return	the player's data
	 */
	public TData getData() {
		return this.data;
	}
}
