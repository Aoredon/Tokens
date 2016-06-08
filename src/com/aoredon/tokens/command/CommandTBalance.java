package com.aoredon.tokens.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aoredon.tokens.Tokens;
import com.aoredon.tokens.Utils;

/**
 * A command which displays the player's current balance to them.
 * This command can only be run a player and not the console.
 * 
 * @author Alex Guest
 */
public class CommandTBalance implements CommandExecutor {
	// An instance of the main plugin.
	private Tokens plugin;

	/**
	 * Takes in an instance of the main plugin.
	 * 
	 * @param plugin	the instance of the main plugin
	 */
	public CommandTBalance(Tokens plugin) {
		this.plugin = plugin;
	}

	/**
	 * Executes the command, displaying the player's balance.
	 */
	@Override
	public boolean onCommand(CommandSender commandSender, Command command,
			String commandLabel, String[] commandArguments) {
		// Ensures that the command is only ran by players.
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage("You must be a player to use this command.");
			return true;
		}
		
		// Casts the sender to a player.
		Player player = (Player) commandSender;
		
		// Checks to see if the player has the required permissions to see
		// their balance and messages them their balance if they do.
		if (player.hasPermission("tokens.balance")) {
			int balance = this.plugin.getTPlayers().get(
					player.getUniqueId()).getData().getTokens();
			Utils.sendMessage(player,
					"Your current balance is: " + balance + " tokens.");
		}
		
		return true;                      
	}
}
