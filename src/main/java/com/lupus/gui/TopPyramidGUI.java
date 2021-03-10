package com.lupus.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TopPyramidGUI extends GUI {
	public TopPyramidGUI(String invName, int invSlots,ItemStack filler, ItemStack... top) {
		super(invName, invSlots);
		for (ItemStack itemStack : top) {
			addItemStack(new NonSelectableItem(itemStack));
		}
		if (filler.getType() != Material.AIR)
			for (int i = 0; i < inv.getSize(); i++) inv.setItem(i, filler);

		setup(top);
	}
	public TopPyramidGUI(String invName, int invSlots, ItemStack... top) {
		this(invName,invSlots,new ItemStack(Material.AIR),top);
	}
	public void setup(ItemStack... top){
		int itemIndex = 0;
		int centerForY = 4;
		for (int n = 0; n < 5; n++){
			if (itemIndex >= top.length)
				break;
			centerForY -= n;

			for (int i=0,j=2*n+1;i<j;i++){
				inv.setItem(centerForY,top[itemIndex]);
				centerForY++;
			}
			centerForY -= n;
			centerForY += 9;
			itemIndex++;
		}
	}
}
