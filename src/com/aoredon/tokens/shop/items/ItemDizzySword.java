package com.aoredon.tokens.shop.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.aoredon.tokens.Utils;
import com.aoredon.tokens.shop.TItem;

/**
 * This is an item which has a chance to make players struck with it
 * dizzy and confused.
 * 
 * @author Alex Guest
 */
public class ItemDizzySword extends TItem {
	/**
	 * Initialises the dizzy sword.
	 */
	public ItemDizzySword() {
		super(Material.DIAMOND_SWORD,ChatColor.DARK_PURPLE + "Dizzy Sword",
				"Will make you sick and dizzy!", "Dizziness", 1500);
	}

	/**
	 * Activates the dizziness ability on the victim.
	 */
	@Override
	public void activateAbility(Player attacker, LivingEntity victim) {
		// Has a 50% chance to inflict confusion upon the victim.
		if (Utils.randomNumber(1, 2) == 1) {
			// Apply the confusion potion effect to the victim.
			(new PotionEffect(PotionEffectType.CONFUSION, 100, 4)).apply(attacker);
		}
	}
	
	/**
	 * Makes the dizzy sword into an item stack.
	 */
	@Override
	public ItemStack makeItem() {
		ItemStack itemStack = new ItemStack(this.getItemMaterial(), 1);
		return itemStack;
	}
}