package com.lupus.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IGUI {
	void addItemStack(SelectableItem item);

	ItemStack[] getItemStacks();

	Inventory getInventory();
	boolean isMatch(String invName);
	String getName();
	String getInventoryName();
	void click(Player player, InventoryClickEvent e);
	void onClose(Player player);
}