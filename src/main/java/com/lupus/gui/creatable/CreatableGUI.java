package com.lupus.gui.creatable;

import com.lupus.gui.GUI;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreatableGUI extends GUI {
	private Map<Integer, CreatableItem> itemMap;
	private String name;
	private CreatableGUI(String name,String invName, int invSlots,Map<Integer,CreatableItem> map) {
		super(TextUtility.color(invName), invSlots*9);
		itemMap = map;
		this.name = name;
		Inventory inv = getInventory();

		ItemStack filler = itemMap.get(-1).getItem();

		for (int i = 0; i < inv.getSize(); i++) inv.setItem(i, filler);

		for (int i = 0; i < inv.getSize(); i++)
			if (itemMap.containsKey(i))
				inv.setItem(i, itemMap.get(i).getItem());
		for (Map.Entry<Integer, CreatableItem> integerCreatableItemEntry : itemMap.entrySet()) {
			addItemStack(integerCreatableItemEntry.getValue());
		}
	}

	@Override
	public void open(Player p) {
		super.open(p);
	}

	public static CreatableGUI loadGUIFromFile(File file){
		if (file == null)
			return null;
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

		String name = configuration.getString("name","");
		String inventoryName = configuration.getString("inventory-name","");
		int rowAmount = configuration.getInt("row-amount");
		if (rowAmount > 6 || rowAmount < 1)
			rowAmount = 6;
		Map<Integer,CreatableItem> map = new HashMap<>();
		ConfigurationSection configSection = configuration.getConfigurationSection("items");
		if (configSection != null)
		for (String key : configSection.getKeys(false)) {
			Integer b = Integer.valueOf(key);
			CreatableItem item =
					CreatableItem.
					start(
							Objects.requireNonNull(
									configSection.getString(key + ".action", "")
							)
					).
					setItem(
							Objects.requireNonNull(
									configSection.getConfigurationSection(key + ".item")
							)
					);
			map.put(b,item);
		}
		return new CreatableGUI(name,inventoryName,rowAmount,map);
	}
	@Override
	public void onClose(Player player) {

	}

	@Override
	public String getName() {
		return name;
	}
}
