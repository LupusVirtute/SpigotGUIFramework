package com.lupus.gui.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigUtility {
	public static FileConfiguration getConfig(JavaPlugin plugin, String configName) {
		File dataFolder = plugin.getDataFolder();
		File file = new File(dataFolder, configName);
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
