package com.aoredon.tokens.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import com.aoredon.tokens.Tokens;
import com.aoredon.tokens.Utils;
import com.aoredon.tokens.extra.ArmourEnum;
import com.aoredon.tokens.shop.TItem;

/**
 * Handles awarding players with tokens for killing things and allows for
 * custom item abilities to work.
 * 
 * @author Alex Guest
 */
public class ListenerEntity implements Listener {
	// An instance of the main plugin.
	private Tokens plugin;

	/**
	 * Takes in an instance of the main plugin.
	 * 
	 * @param plugin	the instance of the main plugin
	 */
	public ListenerEntity(Tokens plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles damage to entities. If the entity is damaged using an item
	 * which has a unique ability on it, then it will handle that
	 * appropriately.
	 * @param entityDamageByEntityEvent	the event fired when an entity is damaged
	 */
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent entityDamageByEntityEvent) {
		// Makes sure that the entity that damaged the ither entity was a
		// player before we continue.
		if (!(entityDamageByEntityEvent.getDamager() instanceof Player)) {
			return;
		}
		
		// Makes sure that the entity that was damaged is a living entity.
		if (!(entityDamageByEntityEvent.getDamager() instanceof LivingEntity)) {
			return;
		}
		
		// Gets the attacking player from the event.
		Player attacker = (Player) entityDamageByEntityEvent.getDamager();
		// Gets the attacked entity from the event.
		LivingEntity victim = (LivingEntity) entityDamageByEntityEvent.getEntity();
		
		// Make sure that the damage dealt is larger than zero.
		if (entityDamageByEntityEvent.getDamage() <= 0) {
			return;
		}
		
		// The item the attacker attacked with.
		ItemStack currentItem = attacker.getInventory().getItemInMainHand();
		
		// Loops through each item available in the shop.
		for (TItem tokenItem : this.plugin.getTShop().getItems()) {
			// Check to see if the item they attacked with has an ability.
			if (TItem.isSimilar(tokenItem, currentItem)) {
				// Activates the item's ability.
				tokenItem.activateAbility(attacker, victim);
				
				return;
			}
		}
	}

	/**
	 * This handles entity death and has a random chance of awarding the
	 * killing player with a random number of tokens.
	 * 
	 * @param entityDeathEvent	the event fired when an entity dies
	 */
	@EventHandler
	public void onEntityDeath(EntityDeathEvent entityDeathEvent) {
		// Makes sure that there is a killer.
		if (entityDeathEvent.getEntity().getKiller() != null) {
			return;
		}
		
		// Gets the killer.
		Player player = entityDeathEvent.getEntity().getKiller();
		
		// Calculates the chnace the player has of recieving tokens from
		// this kill.
		if (Utils.randomNumber(1, 5) != 1) {
			return;
		}
		
		// Generates a random number of tokens to award to the player.
		int amount = Utils.randomNumber(1, 3);
		// Gets the equipment from the victim.
		EntityEquipment equipment = entityDeathEvent.getEntity().getEquipment();
		
		// Loops through each item in the victim's equipment.
		for (ItemStack itemStack : equipment.getArmorContents()) {
			// Increases the amount of tokens awarded based on how well the
			// victim was equipped.
			amount += ArmourEnum.getValueOfArmour(itemStack.getType().name());
		}
		
		// Awards the player the set amount of tokens.
		this.plugin.getTPlayers().get(player.getName()).getData().incrementTokens(amount);
		// Informs the player of their reward.
		Utils.sendMessage(player, "You have earned " + amount + " token(s).");
	}
}
