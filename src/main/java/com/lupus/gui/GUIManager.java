package com.lupus.gui;

import com.lupus.MCGUIFramework;
import com.lupus.gui.creatable.CreatableGUI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GUIManager {
	private final HashMap<String, IGUI> createdGUIs = new HashMap<>();

	public void addGUI(IGUI gui) {
		createdGUIs.put(gui.getName(), gui);
	}

	public int getSize() {
		return createdGUIs.values().size();
	}

	public IGUI getGUI(String name) {
		return createdGUIs.get(name);
	}

	public Set<String> getGUINames() {
		return createdGUIs.keySet();
	}

	private void clear() {
		createdGUIs.clear();
	}

	public void load() {
		clear();
		files = new ArrayList<>();
		File dataFolder = MCGUIFramework.getInstance().getDataFolder();
		dataFolder = new File(dataFolder + "/GUI/");
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
			MCGUIFramework.getInstance().saveResource("examplegui.yml", false);
			Path from = Paths.get(MCGUIFramework.getInstance().getDataFolder() + "/examplegui.yml");
			Path to = Paths.get(dataFolder + "/examplegui.yml");
			try {
				Files.move(from, to);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (File file : fileSearch(dataFolder, ".yml")) {
			CreatableGUI gui = CreatableGUI.loadGUIFromFile(file);
			MCGUIFramework.getManager().addGUI(gui);
		}
	}

	private List<File> files = new ArrayList<>();

	private List<File> fileSearch(File dir, String fileFormat) {
		if (dir.isDirectory()) {
			for (File file : dir.listFiles()) {
				fileSearch(file, fileFormat);
			}
		} else if (dir.getName().endsWith(fileFormat))
			files.add(dir);
		return files;
	}
}
