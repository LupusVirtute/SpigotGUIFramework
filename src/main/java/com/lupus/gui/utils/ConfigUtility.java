package com.lupus.gui.utils;

import com.lupus.MCGUIFramework;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtility {
	public static FileConfiguration getConfig(String configName){
		File file = new File(MCGUIFramework.getInstance().getDataFolder(), configName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return YamlConfiguration.loadConfiguration(file);
	}
}
