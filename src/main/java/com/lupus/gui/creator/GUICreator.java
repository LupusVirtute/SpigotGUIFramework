package com.lupus.gui.creator;

import com.lupus.gui.GUI;
import org.bukkit.entity.Player;

public class GUICreator extends GUI {
	public GUICreator(String invName, int invSlots) {
		super(invName, invSlots);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void onClose(Player player) {
	}
}
