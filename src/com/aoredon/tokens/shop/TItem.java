package com.aoredon.tokens.shop;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * An abstract class which should be inherited by all items which are
 * avaiable in the store. This allows the items to have a custom name,
 * description and ability. The price of the item is also specified.
 * 
 * @author Alex Guest
 */
public abstract class TItem {
	// The material of the item.
	private final Material itemMaterial;
	// The name of the item.
	private final String itemName;
	// The description of the item.
	private final String itemDescription;
	// The name for the item's ability.
	private final String itemAbility;
	// The price (in tokens) of the item.
	private final int itemPrice;
	
	/**
	 * 
	 * @param itemMaterial		the material for the item
	 * @param itemName			the name of the item
	 * @param itemDescription	the description of the item
	 * @param itemAbility		the name for the item's ability
	 * @param itemPrice			the price of the item
	 */
	public TItem(Material itemMaterial,
			String itemName, String itemDescription,
			String itemAbility, int itemPrice) {
		this.itemMaterial = itemMaterial;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemAbility = itemAbility;
		this.itemPrice = itemPrice;
	}
	
	/**
	 * An abstract method which is called whenever the item should
	 * activate its special ability.
	 * 
	 * @param attacker	the player attacking with the item
	 * @param victim	the entity being attacked by the item
	 */
	public abstract void activateAbility(Player attacker, LivingEntity victim);
	
	/**
	 * An abstract method where the item stack is created, adding any
	 * modifications such as enchantments to it before it is returned.
	 * 
	 * @return	the item stack for the item
	 */
	public abstract ItemStack makeItem();
	
	/**
	 * Returns the item stack which includes the full information about the
	 * item.
	 * @return	the final item stack
	 */
	public ItemStack getItem() {
		// Creates the item stack from the enhanced item.
		ItemStack itemStack = this.makeItem();
		// Gets the item's meta from the item.
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		// Sets the item's display name.
		itemMeta.setDisplayName(ChatColor.RESET + this.itemName);
		// Sets the item's lore which includes the price, the description
		// and the item's ability.
		itemMeta.setLore(new ArrayList<String>() {{
			add("Costs: " + itemPrice + " Tokens");
			add(itemDescription);
			add("Special Ability: " + itemAbility);
		}});
		// Set's the final item's meta to the modiifed meta.
		itemStack.setItemMeta(itemMeta);
		
		// Returns the final item.
		return itemStack;
	}
	
	/**
	 * Compares a token item with a normal item to see if they are
	 * the same.
	 * 
	 * @param tokenItem		the token item to compare
	 * @param normalItem	the normal item to compare
	 * @return				whether or not the two items are the same
	 */
	public static boolean isSimilar(TItem tokenItem, ItemStack normalItem) {
		// Gets the normal item's meta.
		ItemMeta itemMeta = normalItem.getItemMeta();
		
		// Makes sure the normal item's lore is not null before proceeding.
		if (itemMeta.getLore() == null) {
			// Returns false as the items will not be similar.
			return false;
		}
		
		// Makes sure the size of the normal item's lore is greater than
		// zero before proceeding.
		if (itemMeta.getLore().size() <= 0) {
			// Returns false as the items will not be similar.
			return false;
		}
		
		// Compares the token item's name to the normal item's name.
		if (tokenItem.getItemName() != itemMeta.getDisplayName()) {
			// Returns false as the items are not similar.
			return false;
		}
		
		// The items are the same if their descriptions are the same, otherwise
		// they are not the same.
		return itemMeta.getLore().get(2) == tokenItem.getItemDescription();
	}
	
	/**
	 * Gets the item's material
	 * 
	 * @return	the item's material
	 */
	public Material getItemMaterial() {
		return this.itemMaterial;
	}
	
	/**
	 * Gets the item's name.
	 * 
	 * @return	the item's name
	 */
	public String getItemName() {
		return this.itemName;
	}
	
	/**
	 * Gets the item's description.
	 * 
	 * @return	the item's description
	 */
	public String getItemDescription() {
		return this.itemDescription;
	}
	
	/**
	 * Gets the item's ability.
	 * 
	 * @return	the item's ability
	 */
	public String getItemAbility() {
		return this.itemAbility;
	}
	
	/**
	 * Gets the item's price.
	 * 
	 * @return	the item's price
	 */
	public int getItemPrice() {
		return this.itemPrice;
	}
}