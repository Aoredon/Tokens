package com.aoredon.tokens.extra;

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
	
	private int value;
	
	private ArmourEnum(int value) {
		this.value = value;
	}
	
	/**
	 * Checks if 
	 * 
	 * @param string
	 * @return
	 */
	public static int getValueOfArmour(String string) {
		int amount = 0;
		
		for (int i = 0; i < ArmourEnum.values().length; i++) {
			if (string.toLowerCase().contains(ArmourEnum.values()[i].name().toLowerCase())) {
				amount += ArmourEnum.values()[i].getValue();
			}
		}
		
		return amount;
	}
	
	/*
	 * Returns the value of the armor piece.
	 */
	public int getValue() {
		return this.value;
	}
}