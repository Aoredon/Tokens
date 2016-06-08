package com.aoredon.tokens.command;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aoredon.tokens.Tokens;
import com.aoredon.tokens.Utils;
import com.aoredon.tokens.player.TPlayer;

/**
 * A command which allows a player (most likely an admin) to create
 * tokens. This command can be run either by the player or the console.
 * 
 * @author Alex Guest
 */
public class CommandTGive implements CommandExecutor {
	// An instance of the main plugin.
	private Tokens plugin;

	/**
	 * Takes in an instance of the main plugin.
	 * 
	 * @param plugin	the instance of the main plugin
	 */
	public CommandTGive(Tokens plugin) {
		this.plugin = plugin;
	}

	/**
	 * Executes the command, allowing the sender to give the specified player
	 * an arbitrary amount of tokens.
	 */
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] commandArguments) {
		// Ensures that the command has the correct number of arguments.
		if (commandArguments.length != 2) {
			return false;
		}
		
		// Retrieve the specified player's UUID.
		UUID uniqueId = Utils.getUniqueIdFromName(commandArguments[0]);
		
		// If the UUID is null, it's because the player is not currently online.
		if (uniqueId == null) {
			Utils.sendMessage(commandSender, "Player is not currently online.");
		}
		
		// Gets the player from the UUID.
		TPlayer player = this.plugin.getTPlayers().get(uniqueId);
		
		// Declares and initialises the tokens to a default value.
		int tokens = 0;
		
		try {
			// Attempts to load the tokens from the command arguments.
			// This fails if the argument isn't an integer and so it is
			// important to catch the exception to avoid crashes.
			tokens = Integer.parseInt(commandArguments[1]);
		} catch (Exception exception) {
			return false;
		}
		
		// Checks to see if the sender is a player.
		if (commandSender instanceof Player) {
			// If the player doesn't have the required permission,
			// don't allow them to give tokens.
			if (!(player.getPlayer().hasPermission("tokens.give"))) {
				return true;
			}
		}
		
		// Give the player the tokens.
		player.getData().incrementTokens((Integer.parseInt(commandArguments[0])));
		
		// Inform them of this action.
		Utils.sendMessage(player.getPlayer(), "You have been given " +
				tokens + " tokens.");
		
		return true;
	}
}
