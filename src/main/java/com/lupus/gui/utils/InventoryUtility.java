package com.lupus.gui.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUtility {
	public static void addItemsToPlayerInventory(Player player, ItemStack... items) {
		for (ItemStack item : items) {
			addItemStackToPlayerInventory(player, item);
		}
	}

	public static void addItemStackToPlayerInventory(Player player, ItemStack item) {
		PlayerInventory inventory = player.getInventory();
		int firstEmpty = inventory.firstEmpty();
		if (firstEmpty == -1) dropItemAtPlayer(player, item);
		inventory.addItem(item);
	}

	public static void dropItemAtPlayer(Player player, ItemStack item) {
		Location location = player.getLocation().add(0, 1, 0);
		World world = location.getWorld();
		world.dropItemNaturally(location, item);
	}
}
