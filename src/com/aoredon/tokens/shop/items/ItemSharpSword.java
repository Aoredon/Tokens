package com.aoredon.tokens.shop.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.aoredon.tokens.shop.TItem;

/**
 * This is an item which is very sharp. It has no abilities, but it has
 * an unsafe enchantment.
 * 
 * @author Alex Guest
 */
public class ItemSharpSword extends TItem {
	/**
	 * Initialises the sharp sword.
	 */
	public ItemSharpSword() {
		super(Material.DIAMOND_SWORD,
				ChatColor.BOLD + "Sharp Sword",
				"Very pointy.", "None", 500);
	}

	/**
	 * Activates the sharp sword's ability, however it does not have one.
	 */
	@Override
	public void activateAbility(Player attacker, LivingEntity victim) {
		return;
	}
	
	/**
	 * Makes the sharp sword into an item stack, adding an
	 * unsafe sharpness enchantment.
	 */
	@Override
	public ItemStack makeItem() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD, 1);
		
		itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
		
		return itemStack;
	}
}