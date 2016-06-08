package com.aoredon.tokens;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A class filled with static methods which just allow for certain actions
 * to be completed quickly using a single method rather than reusing multiple
 * lines of code.
 * 
 * @author Alex Guest
 */
public class Utils {
	// Creates a random generator to be used by the plugin.
	public static final Random RANDOM_GENERATOR = new Random();
	
	/**
	 * Generates a random number in the specified range.
	 * 
	 * @param min	the minimum possible number to generate
	 * @param max	the maximum possible number to generate
	 * @return		a random number between the minimum and maximum
	 */
	public static int randomNumber(int min, int max) {
		return Utils.RANDOM_GENERATOR.nextInt(max) + min;
	}
	
	/**
	 * Gets the unique ID from the named player. The player
	 * must be online for this method to return the UUID.
	 * 
	 * @param name	the name of the player to get the unique identifier from
	 * @return		the unique identifier of the player
	 */
	public static UUID getUniqueIdFromName(String name) {
		// Loops through each online player.
		for (Player player : Bukkit.getOnlinePlayers()) {
			// Checks if the player is the one we are looking for.
			if (player.getName() == name) {
				// Returns the player's unique identifier.
				return player.getUniqueId();
			}
		}
		
		// Returns nothing if we don't find the player.
		return null;
	}
	
	/**
	 * Sends a message to the player with the option of including the
	 * plugin's logo.
	 * 
	 * @param player	the player to send the message to
	 * @param message	the message to send
	 * @param logo		whether or not the plugin's logo should be included
	 */
	public static void sendMessage(Player player, String message, boolean logo) {
		if (logo) {
			// Sends the message with the plugin's logo.
			Utils.sendMessage(player, Tokens.LOGO + message);
		} else {
			// Sends the message without the plugin's logo.
			Utils.sendMessage(player, message);
		}
	}
	
	/**
	 * Sends a message to the command sender with the plugin's logo
	 * prefixing it.
	 * 
	 * @param commandSender	the command sender to send the message to
	 * @param message		the message to send
	 */
	public static void sendMessage(CommandSender commandSender, String message) {
		commandSender.sendMessage(Tokens.LOGO + message);
	}
	
	/**
	 * Sends a message to the specified player.
	 * 
	 * @param player	the player to send the message to
	 * @param message	the message to send
	 */
	public static void sendMessage(Player player, String message) {
		player.sendMessage(message);
	}
	
	/**
	 * Reports an exception to any currently online operators.
	 * The stack trace of the exception is also printed to the console.
	 * 
	 * @param exception	the exception to report
	 */
	public static void reportError(Exception exception) {	
		// Loops through each online player.
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			// Checks to see make sure they are online and to see if they
			// are an operator
			if (player.isOnline() && player.isOp()) {
				// Informs the operator that an error has occured with the
				// plugin.
				Utils.sendMessage(player, "Warning. Tokens has encountered an error.");
				// Gives the operator some information about the error.
				Utils.sendMessage(player, exception.getStackTrace().toString());
				
				// Prints the stack trace to the console in case there are no
				// operators online and so that the server has a log of the
				// error.
				exception.printStackTrace();
			}
		}
	}
}
