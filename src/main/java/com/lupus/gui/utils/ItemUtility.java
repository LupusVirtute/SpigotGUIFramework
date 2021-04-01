package com.lupus.gui.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemUtility {
	public static String getItemName(ItemStack item) {
		String finalName;
		ItemMeta meta = item.getItemMeta();
		var name = meta.displayName();
		if (name != null)
			finalName = name.insertion();
		else
			finalName = item.getType().name().replace('_', ' ');
		return finalName;
	}

	public static ItemStack setItemTitle(ItemStack stack, String title) {
		var meta = stack.getItemMeta();
		meta.displayName(
				Component.text(TextUtility.color(title))
		);
		stack.setItemMeta(meta);
		return stack;
	}

	public static ItemStack setItemLore(ItemStack stack, String[] lore) {
		return setItemLore(stack, Arrays.asList(lore));
	}

	public static ItemStack setItemLore(ItemStack stack, List<String> lore) {
		ItemMeta meta = stack.getItemMeta();
		List<Component> componentList = new ArrayList<>();
		for (String s : lore) componentList.add(Component.text(TextUtility.color(s)));
		meta.lore(componentList);
		stack.setItemMeta(meta);
		return stack;
	}

	public static ItemStack setItemTitleAndLore(ItemStack itemStack, String title, List<String> lore) {
		setItemTitle(itemStack, title);
		setItemLore(itemStack, lore);
		return itemStack;
	}
}
