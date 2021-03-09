package com.lupus.gui.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemUtility {
	public static ItemStack setItemTitleAndLore(ItemStack itemStack, String title, List<String> lore) {
		var meta = itemStack.getItemMeta();
		meta.displayName(
				Component.text(TextUtility.color(title))
		);
		List<Component> componentList = new ArrayList<>();
		for (String s : lore) componentList.add(Component.text(TextUtility.color(s)));
		meta.lore(componentList);
		itemStack.setItemMeta(meta);
		return itemStack;
	}
}
