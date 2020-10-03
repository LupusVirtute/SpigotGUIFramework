package com.lupus.gui;

import com.lupus.utils.InventoryUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class GUI implements IGUI, InventoryHolder {
	protected Inventory inv;
	protected List<ItemStack> items = new ArrayList<>();
	@Override
	public void addItemStack(ItemStack item){
		items.add(item);
	}
	@Override
	public ItemStack[] getItemStacks(){
		ItemStack[] itemStacks = new ItemStack[1];
		itemStacks = items.toArray(itemStacks);
		return itemStacks;
	}
	@Override
	public Inventory getInventory() {
		return inv;
	}
	public GUI(String invName,int invSlots){
		inv =  InventoryUtility.createCustomGUI(this,invSlots,invName);
	}
	@Override
	public boolean isMatch(String invName){
		return getInventoryName() != null && getInventoryName().contains(invName);
	}
	@Override
	public String getInventoryName(){
		return inv.getName();
	}
	public void open(Player p){
		p.closeInventory();
		p.openInventory(inv);
	}
	public void click(Player player, InventoryClickEvent e){
		ItemStack item = e.getCurrentItem();
		if (item != null)
			if (item instanceof SelectableItem)
				((SelectableItem) item).run();
	}
}