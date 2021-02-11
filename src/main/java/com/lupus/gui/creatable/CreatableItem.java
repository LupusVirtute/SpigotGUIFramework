package com.lupus.creatable;

import com.lupus.utils.Skulls;
import com.sun.istack.internal.NotNull;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class CreatableItem {
	ItemStack item;
	Action itemAction;
	static Action[] actions = new Action[]{

	};
	public abstract class Action {
		private String action;
		public Action(String action){
			this.action = action;
		}
		public abstract void doAction(String actionArguments);
		public String getActionName(){
			return action;
		}
		public boolean isMatch(String name){
			return action.equals(name);
		}
	}
	private CreatableItem(String action){
		if (action.equals(""))
			return;
		String[] actions = action.split(":");

		for (int i=0;i<actions.length;i++){
			Action act = CreatableItem.actions[i];
			if (act.isMatch(actions[0])){
				act.doAction(
						combineArgs(getArgs(actions,1))
				);
			}
		}
	}
	public static CreatableItem start(String action){
		if (action.isEmpty()) {
			action = "";
		}
		return new CreatableItem(action);
	}
	public String[] getArgs(@NotNull String[] args, int from){
		String[] argsNew = new String[args.length-from];
		if (args.length - from >= 0) System.arraycopy(args, from, argsNew, 0, args.length - from);
		return argsNew;
	}
	public String combineArgs(@NotNull  String[] args){
		StringBuilder bestArg = new StringBuilder();
		for (String arg : args)
			bestArg.append(arg).append("|");
		return bestArg.toString();
	}
	public CreatableItem addItem(ConfigurationSection section){
		String materialString = section.getString("material","STONE");
		Material mat = Material.valueOf(materialString);

		String name = section.getString("name","ERROR");
		List<String> lore = section.getStringList("lore");
		if (lore == null)
			lore = new ArrayList<>();
		String skullTexture = section.getString("skull-texture","");

		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);

		meta.setDisplayName(name);


		item.setItemMeta(meta);
		if (mat == Material.PLAYER_HEAD){
			item = Skulls.setSkullTexture(item,skullTexture);
		}
		this.item = item;
		return this;
	}
}
