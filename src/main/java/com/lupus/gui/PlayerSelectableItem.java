package com.lupus.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class PlayerSelectableItem extends SelectableItem {
	public PlayerSelectableItem(boolean runnable, ItemStack item) {
		super(runnable, item);
	}

	@Override
	protected void execute(Object... args) {
		if (args.length < 1) return;
		if (!(args[0] instanceof Player)) return;
		Player player = (Player) args[0];
		execute(player,getArgs(args,1));
	}
	protected abstract void execute(Player player, Object... args);
}
