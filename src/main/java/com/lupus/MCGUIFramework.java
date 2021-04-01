package com.lupus;

import com.lupus.gui.GUIManager;
import com.lupus.gui.utils.SkullUtility;
import com.lupus.listeners.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.dependency.Dependency;
import org.bukkit.plugin.java.annotation.dependency.DependsOn;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name = "MCGUIFramework", version = "1.0-SNAPSHOT")
@Description(value = "Simple GUI Manager")
@Author(value = "LupusVirtute")
@Website(value = "github.com/PuccyDestroyerxXx")
@ApiVersion(value = ApiVersion.Target.v1_15)
@DependsOn({
		@Dependency("LupusCommandFramework")
})

public class MCGUIFramework extends JavaPlugin {
	private static MCGUIFramework INSTANCE;
	private static final GUIManager manager = new GUIManager();

	public static MCGUIFramework getInstance() {
		return INSTANCE;
	}

	public static GUIManager getManager() {
		return manager;
	}


	@Override
	public void onEnable() {
		INSTANCE = this;
		infoConsole("Initializing MCGUIFramework");
		this.saveDefaultConfig();
		infoConsole("Loading Skull numbers");
		SkullUtility.load();
		infoConsole("Skulls loaded");
		infoConsole("Binding GUI listener to plugin");
		getServer().getPluginManager().registerEvents(new GUIListener(), this);
		infoConsole("Bind completed");
		infoConsole("Loading GUI Files");
		loadGUIFiles();
		infoConsole("Loaded GUIS: " + getManager().getSize());
		getLogger().info("Loaded successfully MCGUIFramework");
	}

	public static void loadGUIFiles() {
		getManager().load();
	}

	private static void infoConsole(String message) {
		Bukkit.getLogger().info("[MCGUIF] " + message);
	}
}
