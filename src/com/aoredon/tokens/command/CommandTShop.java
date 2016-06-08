package com.aoredon.tokens.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aoredon.tokens.Tokens;

/**
 * A command which opens the shop to allow the player to spend
 * their tokens.
 * 
 * @author Alex Guest
 */
public class CommandTShop implements CommandExecutor {
	// An instance of the main plugin.
	private Tokens plugin;

	/**
	 * Takes in an instance of the main plugin.
	 * 
	 * @param plugin	the instance of the main plugin
	 */
	public CommandTShop(Tokens plugin) {
		this.plugin = plugin;
	}

	/**
	 * Executes the command, displaying the shop to the player.
	 */
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] commandArguments) {
		// Ensures that the command is only ran by players.
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage("You must be a player to use this command.");
			return true;
		}
		
		// Casts the sender to a player.
		Player player = (Player) commandSender;
		
		// Make sure the player has the required permission.
		if (player.hasPermission("tokens.shop")) {
			// Display the shop to the player.
			player.openInventory(this.plugin.getTShop().getInventory());
		}
		
		return true;
	}
}
