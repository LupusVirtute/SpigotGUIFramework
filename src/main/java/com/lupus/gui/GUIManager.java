package com.lupus.gui;

import com.lupus.MCGUIFramework;
import com.lupus.gui.creatable.CreatableGUI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class GUIManager {
	private final HashMap<String,IGUI> createdGUIs = new HashMap<>();
	public void addGUI(IGUI gui){
		createdGUIs.put(gui.getName(),gui);
	}
	public int getSize(){
		return createdGUIs.values().size();
	}
	public IGUI getGUI(String name){
		return createdGUIs.get(name);
	}
	private void clear(){
		createdGUIs.clear();
	}
	public void load(){
		clear();
		File dataFolder = MCGUIFramework.getInstance().getDataFolder();
		dataFolder = new File(dataFolder+"/GUI/");
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
			MCGUIFramework.getInstance().saveResource("examplegui.yml",false);
			Path from = Paths.get(MCGUIFramework.getInstance().getDataFolder()+"/examplegui.yml");
			Path to = Paths.get(dataFolder+"/examplegui.yml");
			try {
				Files.move(from,to);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (File file : Objects.requireNonNull(dataFolder.listFiles())) {
			CreatableGUI gui = CreatableGUI.loadGUIFromFile(file);
			MCGUIFramework.getManager().addGUI(gui);
		}
	}
}
