package com.aoredon.tokens.command;

import java.math.BigDecimal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aoredon.tokens.Tokens;
import com.aoredon.tokens.Utils;
import com.earth2me.essentials.api.Economy;

/**
 * A command which allows the player to buy tokens with their money.
 * This command can only be run a player and not the console.
 * 
 * @author Alex Guest
 */
public class CommandTBuy implements CommandExecutor {
	// An instance of the main plugin.
	private Tokens plugin;

	/**
	 * Takes in an instance of the main plugin.
	 * 
	 * @param plugin	the instance of the main plugin
	 */
	public CommandTBuy(Tokens plugin) {
		this.plugin = plugin;
	}

	/**
	 * Executes the command, allowing the player to buy tokens with their money.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] commandArguments) {
		// Ensures that the command is only ran by players.
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage("You must be a player to use this command.");
			return true;
		}
		
		// Casts the sender to a player.
		Player player = (Player) commandSender;
		
		// Checks to see if the player has permission to buy tokens.
		if (player.hasPermission("tokens.buy")) {
			// Checks to see if the command has the correct number of
			// arguments.
			if (commandArguments != null && commandArguments.length == 1) {
				try {
					// Gets the desired amount of tokens the player wants to
					// purchase.
					int tokens = Integer.parseInt(commandArguments[0]);
					// Calculates the cost of the tokens.
					int cost = Tokens.TOKEN_WORTH * tokens;
					
					// Makes sure the player has enough money.
					if (Economy.getMoney(player.getName()) >= cost) {
						// Subtracts the cost from the player's balance.
						Economy.substract(player.getName(),
								new BigDecimal(cost));
						// Increments the player's tokens.
						this.plugin.getTPlayers().get(player.getUniqueId()).
							getData().incrementTokens(tokens);
						// Informs the player of their purchase.
						Utils.sendMessage(player, "You have bought " + tokens +
								" tokens for" + cost + ".");
					} else {
						// Informs the player that they don't have the required amount of money.
						Utils.sendMessage(player, "Insufficient money.");
					}
				} catch (Exception exception) {
					Utils.reportError(exception);
					return false;
				}
			} else {
				return false;
			}
		}
		
		return true;                      
	}
}
