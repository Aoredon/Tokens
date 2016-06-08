package com.aoredon.tokens.shop;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import com.aoredon.tokens.Tokens;
import com.aoredon.tokens.shop.items.ItemDizzySword;
import com.aoredon.tokens.shop.items.ItemGodSword;
import com.aoredon.tokens.shop.items.ItemLightningSword;
import com.aoredon.tokens.shop.items.ItemSharpSword;
import com.aoredon.tokens.shop.items.ItemVamparicSword;

/**
 * The main class for the token shop. This class manages
 * the shop's inventory as well as the items that are available
 * for use and purchase.
 * 
 * @author Alex Guest
 */
public class TShop {
	// An instance of the main plugin.
	private Tokens plugin;
	// The shop's inventory.
	private Inventory inventory;
	// The items that are avaiable for use and purchase.
	private ArrayList<TItem> items;
	
	/**
	 * Takes in an instance of the main plugin.
	 * 
	 * @param plugin	the instance of the main plugin
	 */
	public TShop(Tokens plugin) {
		this.inventory = Bukkit.createInventory(null, 18, ChatColor.RED + "Token Shop");
		this.plugin = plugin;
		this.items = new ArrayList<TItem>();
		this.initItems();
		
		// Loops through each item and adds it to the shop's inventory.
		for (TItem item : this.items) {
			this.inventory.addItem(item.getItem());
		}
	}
	
	/**
	 * Initialises the items for the shop.
	 */
	public void initItems() {
		this.items.add(new ItemDizzySword());
		this.items.add(new ItemGodSword());
		this.items.add(new ItemLightningSword());
		this.items.add(new ItemSharpSword());
		this.items.add(new ItemVamparicSword());
	}
	
	/**
	 * Gets the plugin.
	 * 
	 * @return	the plugin
	 */
	public Tokens getPlugin() {
		return this.plugin;
	}
	
	/**
	 * Gets the shop's inventory.
	 * 
	 * @return	the shop's inventory
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * Gets the shop's items that can be used and bought
	 * 
	 * @return	the shop's items
	 */
	public ArrayList<TItem> getItems() {
		return this.items;
	}
}
