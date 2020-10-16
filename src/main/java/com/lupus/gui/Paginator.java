package com.lupus.gui;


import com.lupus.utils.InventoryUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Paginator extends GUI {
	ItemStack previous;
	ItemStack next;
	ItemStack exit;
	protected int pageCounter = 0;
	public Paginator(String invName) {
		super(invName, 54);

		previous = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)14);
		ItemMeta meta = previous.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED+"Poprzednia Strona");
		previous.setItemMeta(meta);

		next = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)13);
		meta = next.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Następna Strona");
		next.setItemMeta(meta);

		exit = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)11);
		meta = exit.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_BLUE + "Wyjdź");
		exit.setItemMeta(meta);
		/*
		 * Representation of Panel
		 * | - Previous Next
		 * / - Leave
		 * - - Items
		 * Y
		 * 4 - - - - - - - - -
		 * 5 | | | / / / | | |
		 * 6 | | | / / / | | |
		 *   0 1 2 3 4 5 6 7 8 X
		 */
		InventoryUtility.fillSquare(
				previous,
				inv,
				0,
				4,
				3,
				6);
		InventoryUtility.fillSquare(
				next,
				inv,
				6,
				4,
				9,
				6);
		InventoryUtility.fillSquare(
				exit,
				inv,
				3,
				4,
				6,
				6);
	}

	@Override
	public void click(Player player, InventoryClickEvent e) {
		ItemStack clickedItem = e.getCurrentItem();
		if (clickedItem == null)
			return;
		if (clickedItem.isSimilar(previous)){
			if (pageCounter >= 0)
				previousPage();
		}
		else if (clickedItem.isSimilar(next)){
			nextPage();
		}
		else if (clickedItem.isSimilar(exit)){
			player.closeInventory();
		}
		else {
			onSlotInteraction(player,e);
		}
	}
	public void onSlotInteraction(Player player,InventoryClickEvent e){
		ItemStack item = e.getCurrentItem();
		SelectableItem selItem = null;
		if (item != null)
			 selItem = items.stream().filter(o->o.isSimilar(item)).findFirst().orElse(null);
		if (selItem != null)
			selItem.run();
	}
	public void nextPage(){
		setPage(pageCounter+1);
	}
	public void previousPage(){
		if (pageCounter > 0)
			setPage(pageCounter-1);
		else
			setPage(0);
	}
	public void setPage(int page){
		pageCounter = page;
		for (int i=0;i<=35;i++){
			inv.setItem(i,new ItemStack(Material.AIR));
		}
		for (int i=page*36,j=i+36;i<j;i++){
			if (items.size() <= i) {
				break;
			}
			inv.setItem(i-j+36,items.get(i));
		}
	}
}
