package com.aoredon.tokens.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.aoredon.tokens.Tokens;
import com.aoredon.tokens.Utils;
import com.aoredon.tokens.player.TPlayer;

/**
 * A listener which handles player join and leave events.
 * This is so the plugin can register and unregister them to
 * keep track of the players on the server.
 * 
 * @author Alex Guest
 */
public class ListenerPlayer implements Listener {
	// An instance of the main plugin.
	private Tokens plugin;

	/**
	 * Takes in an instance of the main plugin.
	 * 
	 * @param plugin	the instance of the main plugin
	 */
	public ListenerPlayer(Tokens plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Registers the player in the player registry so that the plugin
	 * can keep track of them. This also informs the player of their
	 * current token balance.
	 * 
	 * @param playerJoinEvent	the event fired when a player joins
	 */
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
		// Gets the player from the event.
		Player player = playerJoinEvent.getPlayer();
		
		// Puts the player in the player registry.
		this.plugin.getTPlayers().put(player.getUniqueId().toString(),
				new TPlayer(this.plugin, player));
		
		// Informs the player about their balance.
		Utils.sendMessage(player, "You currently have: " +
				this.plugin.getTPlayers().get(
						player.getName()).getData().getTokens() +
							" tokens.");
	}
	
	/**
	 * Unregisters the player so that the plugin doesn't try to
	 * keep track of them anymore.
	 * 
	 * @param playerQuitEvent	the event fired when a player disconnects
	 */
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
		// Gets the player from the event.
		Player player = playerQuitEvent.getPlayer();
		
		// Saves the player to ensure they don't lose their data.
		this.plugin.getTPlayers().get(player.getName()).getData().saveConfig();
		
		// Unregisters the player by removing them from the hash map.
		this.plugin.getTPlayers().remove(player.getName());
	}
}
