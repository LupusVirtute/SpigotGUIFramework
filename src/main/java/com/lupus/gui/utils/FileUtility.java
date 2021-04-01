package com.lupus.gui.utils;

import com.lupus.MCGUIFramework;
import com.lupus.gui.IGUI;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtility {
	public static void saveGUIToConfig(IGUI gui) throws IOException {
		File file = new File(MCGUIFramework.getInstance().getDataFolder() + "/GUI/", gui.getInventoryName() + ".yml");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set("name", gui.getName());
		configuration.set("inventory-name", gui.getInventoryName());
		Inventory inv = gui.getInventory();
		int size = inv.getSize();
		ConfigurationSection section = configuration.createSection("items");
		for (int i = 0; i < size; i++) {
			ItemStack itemStack = inv.getItem(i);
			if (itemStack != null)
				serializeItemToConfig(
						itemStack,
						section,
						i
				);
		}
		configuration.save(file);
	}

	private static void serializeItemToConfig(ItemStack itemStack, ConfigurationSection configuration, int place) {
		configuration = configuration.createSection(String.valueOf(place));
		configuration.set("action", "");
		configuration = configuration.createSection("item");

		configuration.set("material", itemStack.getType().name());

		ItemMeta meta = itemStack.getItemMeta();
		configuration.set("name", meta.displayName().insertion());
		List<String> lore = new ArrayList<>();
		for (Component component : meta.lore()) {
			lore.add(component.insertion());
		}
		configuration.get("lore", lore);
		if (meta instanceof SkullMeta) {
			SkullMeta skullMeta = (SkullMeta) meta;
			configuration.set("skull-texture", SkullUtility.getSkullStringFromProfile(skullMeta.getPlayerProfile()));
		}
	}
}
