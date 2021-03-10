package com.lupus.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class GUI implements IGUI, InventoryHolder {
	protected Inventory inv;
	protected List<SelectableItem> items = new ArrayList<>();
	@Override
	public void addItemStack(SelectableItem item){
		items.add(item);
	}
	@Override
	public ItemStack[] getItemStacks(){
		ItemStack[] itemStacks = new ItemStack[items.size()];
		for (int i = 0; i < items.size(); i++) {
			SelectableItem item = items.get(i);
			itemStacks[i] = item.getItem();
		}
		return itemStacks;
	}

	@Override
	public String getName() {
		return "CodeBuild_"+getInventoryName();
	}

	@Override
	public Inventory getInventory() {
		return inv;
	}
	private final String invName;
	public GUI(String invName,int invSlots){
		this.invName = invName;
		inv =  createCustomGUI(this,invSlots,invName);
	}
	@Override
	public boolean isMatch(String invName){
		return getInventoryName() != null && getInventoryName().contains(invName);
	}
	@Override
	public String getInventoryName(){
		return invName;
	}
	@Override
	public void open(Player p){
		p.closeInventory();
		p.openInventory(inv);
	}
	public void onClickedItemNull(Player player, InventoryClickEvent e){}

	public void click(Player player, InventoryClickEvent e){
		ItemStack item = e.getCurrentItem();
		SelectableItem selItem;
		if (item != null){
				selItem = items.
						stream().
						filter(o ->
								{
									if (o == null)
										return false;
									return o.getItem().hashCode() == item.hashCode();
								}
						).
						findFirst().
						orElse(null);
				if (selItem != null)
					selItem.run(player);
		}
		else
			onClickedItemNull(player,e);
	}
	private static Inventory createCustomGUI(InventoryHolder holder, int slots, String name){
		if(slots <= 0 || slots > 54 || name == null){
			return null;
		}
		slots /= 9;
		if (slots < 1) {
			slots = 1;
		}
		slots *= 9;
		var textComponent = Component.text(name);
		return Bukkit.createInventory(holder, slots, textComponent);
	}
	@Override
	public void onClose(Player p){

	}
}