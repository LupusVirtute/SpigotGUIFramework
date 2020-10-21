package com.lupus.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class SelectableItem extends ItemStack {
	public SelectableItem(boolean runnable, ItemStack item){
		this.setAmount(item.getAmount());
		this.setType(item.getType());
		this.setDurability(item.getDurability());
		if (item.getItemMeta() != null)
			this.setItemMeta(item.getItemMeta());
		this.setData(item.getData());
		this.runnable = runnable;
	}
	boolean runnable;
	public void run(Object... args){
		if (runnable)
			execute(args);
	}
	protected abstract void execute(Object... args);
}
