package com.lupus.gui;

import org.bukkit.inventory.ItemStack;

public class NonSelectableItem extends SelectableItem{

	public NonSelectableItem(ItemStack item) {
		super(false, item);
	}

	@Override
	protected void execute(Object... args) {

	}
}
