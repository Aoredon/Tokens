package com.aoredon.tokens.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aoredon.tokens.Tokens;
import com.aoredon.tokens.Utils;

/**
 * Listens to chest inventory clicks. This listener will handle any
 * inventory click events to allow the player to purchase items.
 * 
 * @author Alex Guest
 */
public class ListenerChest implements Listener {
	// An instance of the main plugin.
	private Tokens plugin;

	/**
	 * Takes in an instance of the main plugin.
	 * 
	 * @param plugin	the instance of the main plugin
	 */
	public ListenerChest(Tokens plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles any inventory click events. If the player is using the
	 * token shop, any item they click will be considered a shop item.
	 * If the player has the required amount of tokens, the item is
	 * created and given to the player after the tokens have been
	 * deducted.
	 * 
	 * @param inventoryClickEvent	the event fired when a player clicks an inventory slot
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
		// Doesn't continue if the the inventory is not the token shop
		// inventory.
		if (!(inventoryClickEvent.getInventory().getTitle().equals(
				this.plugin.getTShop().getInventory().getTitle()))) {
			return;
		}
		
		// Ensures that the player only buys an item if they are left clicking.
		if (!inventoryClickEvent.isLeftClick()) {
			return;
		}
		
		// Makes sure the inventory slot that the player clicked on has an
		// item in it.
		if (inventoryClickEvent.getCurrentItem() != null) {
			return;
		}
		
		// Casts the clicking human entity to a player.
		Player player = (Player) inventoryClickEvent.getWhoClicked();
		// Gets the item stack from the inventory click event.
		ItemStack itemStack = inventoryClickEvent.getCurrentItem();
		// Gets the item meta from the clicked item stack.
		ItemMeta itemMeta = itemStack.getItemMeta();
		// Gets the price from the item.
		int price = Integer.parseInt(itemMeta.getLore().get(0).
				replace("Costs: ", "").replace(" Tokens", ""));
		// Gets the player's current token balance.
		int balance = this.plugin.getTPlayers().get(
				player.getUniqueId()).getData().getTokens();
		
		// Checks to see if the player has enough tokens.
		if (balance < price) {
			// Informs the player that they do not have a sufficient
			// quantity of tokens to make a purchase.
			Utils.sendMessage(player,
					"You do not have enough tokens to buy this.");
			return;
		}
		
		// First empty gets the first empty slot in the player's
		// inventory. If this is equal to -1 it means that the player
		// has no free slots and as such cannot purchase the item.
		if (player.getInventory().firstEmpty() == -1) {
			Utils.sendMessage(player,
					"Your inventory is too full to buy this.");
			return;
		}
		
		
		// Sets the new display name of the item.
		itemMeta.setDisplayName(itemMeta.getDisplayName());
		// Cleans up the item's lore.
		itemMeta.getLore().set(2, itemMeta.getLore().get(2).replace(
					"Special Ability: ", ""));
		
		// Creates a new item based on what the player wants to
		// purchase.
		ItemStack newItem = new ItemStack(itemStack.getType(), 1);
		// Sets the lore of the item to the appropriate lore.
		newItem.setItemMeta(itemMeta);
		
		// Decerements the player's balance based on the item
		// price.
		this.plugin.getTPlayers().get(player.getName()).getData().
			decrementTokens(price);
		
		// Gives the player the item.
		player.getInventory().addItem(newItem);
			
		// Cancels the inventory click event so that the player cannot
		// take the item.
		inventoryClickEvent.setCancelled(true);
	}
}
