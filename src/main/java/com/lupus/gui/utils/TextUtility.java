package com.lupus.gui.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtility {
	public static String color(String text) {
		Pattern pattern = Pattern.compile("(\\#([A-F]|[a-f]|[0-9]){6})");
		Matcher m = pattern.matcher(text);
		m.results().forEach(o -> {
			String group = o.group(2);
			text.replace("#"+group,Color.fromRGB(Integer.parseInt(group,16)).toString());
		});
		return ChatColor.translateAlternateColorCodes('&',text);
	}
	public static TextComponent getColoredTextComponent(String text){
		return Component.text(color(text));
	}
}
