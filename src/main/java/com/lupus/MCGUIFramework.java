package com.lupus;

import com.lupus.listeners.GUIListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name="MCGUIFramework", version="1.0-SNAPSHOT")
@Description(desc = "Simple GUI Manager")
@Author(name = "LupusVirtute")
@Website(url="github.com/PuccyDestroyerxXx")

public class MCGUIFramework extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("Initializing MCGUIFramework");
		getServer().getPluginManager().registerEvents(new GUIListener(),this);
		getLogger().info("Loaded successfully MCGUIFramework");
	}
}
