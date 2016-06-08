package com.aoredon.tokens.extra;

/**
 * An enum which stores the different armour types
 * and how much extra the player can be awarded for
 * each.
 * 
 * @author Alex
 */
public enum ArmourEnum {
	DIAMOND(5),
	IRON(4),
	CHAIN(3),
	GOLD(2),
	LEATHER(1),
	CHESTPLATE(3),
	LEGGINGS(2),
	HELMET(1),
	BOOTS(1);
	
	// The extra amount the player can be awarded.
	private int value;
	
	/**
	 * Takes in the extra amount the player can be rewarded.
	 * 
	 * @param value	the extra amount
	 */
	private ArmourEnum(int value) {
		this.value = value;
	}
	
	/**
	 * Returns the extra value for the specified piece of armour.
	 * 
	 * @param string	the armour to get the value of
	 * @return			the value of the armour
	 */
	public static int getValueOfArmour(String string) {
		// Declare and initalise the default value of the armour
		// to zero.
		int amount = 0;
		
		// Loops through each type of armour.
		for (ArmourEnum armourEnum : ArmourEnum.values()) {
			// Checks to see if the armour is the same type.
			if (string.toLowerCase().contains(armourEnum.name().
					toLowerCase())) {
				// Increase the value of the armour.
				amount += armourEnum.getValue();
			}
		}
		
		// Returns the value of the armour.
		return amount;
	}
	
	/**
	 * Returns the value of the armour type.
	 * 
	 * @return	the value of the armour type.
	 */
	public int getValue() {
		return this.value;
	}
}
