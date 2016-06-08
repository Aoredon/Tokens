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
 * This is an item which gives your attack a chance to smite down
 * your enemies.
 * 
 * @author Alex Guest
 */
public class ItemLightningSword extends TItem {
	/**
	 * Initialises the lightning sword.
	 */
	public ItemLightningSword() {
		super(Material.DIAMOND_SWORD,ChatColor.AQUA + "Lightning Sword",
				"Chance to strike lightning.", "Lightning Strike", 500);
	}

	/**
	 * Activates the lightning ability on the sword.
	 */
	@Override
	public void activateAbility(Player attacker, LivingEntity victim) {
		// Gives a 20% chance of striking your victim with a lightning bolt.
		if (Utils.randomNumber(1, 5) == 1) {
			// Creates a lightning effect at the victim's location.
			victim.getWorld().strikeLightningEffect(victim.getLocation());
			// Sets the victim on fire.
			victim.setFireTicks(2);
			// Damages the player.
			victim.damage(1.5D);
		}
	}
	
	/**
	 * Makes the lightning sword into an item stack.
	 */
	@Override
	public ItemStack makeItem() {
		ItemStack itemStack = new ItemStack(this.getItemMaterial(), 1);
		
		itemStack.addEnchantment(Enchantment.DURABILITY, 3);
		
		return itemStack;
	}
}