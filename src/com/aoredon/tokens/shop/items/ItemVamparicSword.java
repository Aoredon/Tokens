package com.aoredon.tokens.shop.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.aoredon.tokens.Utils;
import com.aoredon.tokens.shop.TItem;

/**
 * An item which steals life from the victim.
 * 
 * @author Alex Guest
 */
public class ItemVamparicSword extends TItem {
	/**
	 * Initialises the vampiric sword.
	 */
	public ItemVamparicSword() {
		super(Material.DIAMOND_SWORD,
				ChatColor.RED + "Vamparic Sword",
				"Chance to steal life.", "Lifesteal", 500);
	}

	/**
	 * Activates the life steal ability, stealing health from the vicitm
	 * and giving it to the attacker.
	 */
	@Override
	public void activateAbility(Player attacker, LivingEntity victim) {
		// Has a 50% chance to steal life from the victim.
		if (Utils.randomNumber(1, 2) == 1) {
			// If the attacker isn't at their full health, steal life.
			if (attacker.getHealth() <= 19) {
				// Steals health from the victim.
				victim.damage(1);
				// Gives the health to the attacker, ensuring it doesn't
				// exceed their maximum health.
				attacker.setHealth(Math.min(attacker.getHealth() + 1, attacker.getMaxHealth()));
			}
		}
	}
	
	/**
	 * Makes the vamparic sword into an item stack.
	 */
	@Override
	public ItemStack makeItem() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD, 1);
		
		itemStack.addEnchantment(Enchantment.DURABILITY, 3);
		
		return itemStack;
	}
}