package com.lupus.listeners;

import com.lupus.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public class GUIListener implements Listener {
	@EventHandler
	public void onInventoryCancelEvent(InventoryCloseEvent e) {
		InventoryHolder holder = e.getInventory().getHolder();
		if (holder != null)
			if (holder instanceof GUI)
				((GUI) holder).onClose((Player) e.getPlayer());
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		InventoryHolder holder = e.getInventory().getHolder();
		if (holder != null)
			if (holder instanceof GUI) {
				e.setCancelled(true);
				if (e.getWhoClicked() instanceof Player)
					((GUI) holder).click(
							(Player) e.getWhoClicked(),
							e
					);
			}
	}
}
