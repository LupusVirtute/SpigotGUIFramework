package com.lupus.creatable;

import com.lupus.gui.GUI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CreatableGUI extends GUI {
	Map<Integer,CreatableItem> itemMap;

	private CreatableGUI(String invName, int invSlots,Map<Integer,CreatableItem> map) {
		super(invName, invSlots);
		itemMap = map;


		Inventory inv = getInventory();
		if (itemMap.containsKey(-1)) {
			for (int i=0;i<invSlots;i++){
				inv.setItem(i,itemMap.get(-1).);
			}
		}
	}

	@Override
	public void open(Player p) {
		super.open(p);
	}

	public static CreatableGUI createGUI(File file){
		if (file == null)
			return null;
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		String name = configuration.getString("name");
		int rowAmount = configuration.getInt("row-amount");
		Map<Integer,CreatableItem> map = new HashMap<>();
		for (String key : configuration.getKeys(false)) {
			Integer b = Integer.valueOf(key);
			CreatableItem item =
					CreatableItem.
					start(configuration.getString(key+".action","")).
					addItem(configuration.getConfigurationSection(key+".item"));
			map.put(b,item);
		}
		return new CreatableGUI(name,rowAmount,map);
	}
	@Override
	public void onClose(Player player) {

	}
}
