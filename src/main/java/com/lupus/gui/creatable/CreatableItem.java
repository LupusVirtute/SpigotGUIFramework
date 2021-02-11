package com.lupus.gui.creatable;

import com.lupus.gui.SelectableItem;
import com.lupus.utils.ColorUtil;
import com.lupus.utils.Skulls;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CreatableItem extends SelectableItem {
	private String actionName;
	private CreatableItem(String actionName){
		super(true,null);
		if (actionName.equals(""))
			return;
		this.actionName = actionName;


	}
	public static CreatableItem start(String action){
		if (action.isEmpty()) {
			action = "";
		}
		return new CreatableItem(action);
	}
	public String[] getArgs(String[] args, int from){
		String[] argsNew = new String[args.length-from];
		if (args.length - from >= 0) System.arraycopy(args, from, argsNew, 0, args.length - from);
		return argsNew;
	}
	public String combineArgs(String[] args){
		StringBuilder bestArg = new StringBuilder();
		for (String arg : args)
			bestArg.append(arg).append("|");
		return bestArg.toString();
	}
	public CreatableItem setItem(ConfigurationSection section){
		String materialString = section.getString("material","STONE");
		Material mat = Material.valueOf(materialString);

		String name = ColorUtil.text2Color(section.getString("name","ERROR"));

		List<String> lore = section.getStringList("lore");

		if (lore == null)
			lore = new ArrayList<>();

		String skullTexture = section.getString("skull-texture","");
		if (lore.size() > 0) {
			List<String> stringList = new ArrayList<>();
			for (String s : lore) {
				stringList.add(ColorUtil.text2Color(s));
			}
			lore = stringList;
		}
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);

		meta.setDisplayName(name);


		item.setItemMeta(meta);
		if (mat == Material.PLAYER_HEAD){
			item = Skulls.setSkullTexture(item,skullTexture);
		}
		setItem(item);

		return this;
	}

	@Override
	protected void execute(Object... args){
		ArgumentList argumentList = new ArgumentList(args);
		Player caller = null;
		try {
			caller = argumentList.getFirstAnyArgType(Player.class);
		} catch (Exception ignored) {}
		if (caller == null)
			return;
		caller.performCommand(actionName);

	}
}
