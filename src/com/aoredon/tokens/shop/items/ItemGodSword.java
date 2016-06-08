package com.aoredon.tokens.shop.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.aoredon.tokens.shop.TItem;

/**
 * This is an item which does not have any special ability,
 * but has very powerful enchantments imbued upon it.
 * 
 * @author Alex Guest
 */
public class ItemGodSword extends TItem {
	/**
	 * Initialises the god sword.
	 */
	public ItemGodSword() {
		super(Material.DIAMOND_SWORD,ChatColor.YELLOW + "God Sword",
				"Has powerful enchantments.", "None", 500);
	}

	/**
	 * Activates the god sword's ability, however it does not have one.
	 */
	@Override
	public void activateAbility(Player attacker, LivingEntity victim) {
		return;
	}
	
	/**
	 * Makes the god sword into an item stack, adding all the neccessary
	 * enchantments.
	 */
	@Override
	public ItemStack makeItem() {
		ItemStack itemStack = new ItemStack(this.getItemMaterial(), 1);
		
		itemStack.addEnchantment(Enchantment.DAMAGE_ALL, 5);
		itemStack.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		itemStack.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
		itemStack.addEnchantment(Enchantment.DURABILITY, 3);
		
		return itemStack;
	}
}
