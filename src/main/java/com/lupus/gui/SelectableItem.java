package com.lupus.gui;

import org.bukkit.inventory.ItemStack;

public abstract class SelectableItem {
	ItemStack item;
	public SelectableItem(boolean runnable, ItemStack item){
		this.item = item;
		this.runnable = runnable;
	}
	boolean runnable;
	public void run(Object... args){
		if (runnable)
			execute(args);
	}
	public ItemStack getItem(){
		return new ItemStack(item);
	}
	protected void setItem(ItemStack item){
		this.item = item;
	}
	/**
	 * Get first objects in object array from the given index
	 * @param args String array
	 * @param from index to start getting from
	 * @return Object array of objects from given index
	 */
	protected Object[] getArgs(Object[] args,int from){
		Object[] argsNew = new Object[args.length-from];
		if (args.length - from >= 0) System.arraycopy(args, from, argsNew, 0, args.length - from);
		return argsNew;
	}
	protected abstract void execute(Object... args);
}
