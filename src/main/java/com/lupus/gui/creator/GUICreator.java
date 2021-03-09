package com.lupus.gui.creator;

import com.lupus.gui.GUI;
import com.lupus.gui.utils.FileUtility;
import com.lupus.gui.utils.SkullUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.IOException;

public class GUICreator extends GUI {
	public GUICreator(String invName, int invSlots) {
		super(invName, invSlots);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void click(Player player, InventoryClickEvent e) {
		e.setCancelled(false);
	}

	@Override
	public void onClose(Player player) {
		try {
			FileUtility.saveGUIToConfig(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
