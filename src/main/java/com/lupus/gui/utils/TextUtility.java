package com.lupus.gui.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextUtility {
	/**
	 * Colors input String
	 * @param input string to colorize
	 * @return String colored
	 */
	public static String color(String input) {
		Pattern pattern = Pattern.compile("(\\#([A-F]|[a-f]|[0-9]){6})");
		Matcher m = pattern.matcher(input);
		for (MatchResult matchResult : m.results().collect(Collectors.toList())) {
			String group = matchResult.group(2);
			input = input.replace("#" + group, Color.fromRGB(Integer.parseInt(group, 16)).toString());
		}
		return ChatColor.translateAlternateColorCodes('&', input);
	}
	/**
	 * Colors input array
	 * @param input array to colorize
	 * @return Array of strings colored
	 */
	public static String[] color(String[] input){
		for (int i = 0, inputLength = input.length; i < inputLength; i++) {
			String s = input[i];
			input[i] = color(s);
		}
		return input;
	}

	/**
	 * Colors input collection
	 * @param input collection to colorize
	 * @return Set of strings colored
	 */
	public static Set<String> color(Collection<String> input){

		return new HashSet<>(
				Arrays.asList(
						(
								color(
										input.toArray(new String[0])
								)
						)
				)
		);
	}
	/**
	 * Strip color from input
	 * @param input string that should get stripped
	 * @return stripped input
	 */
	public static String strip(String input){
		return ChatColor.stripColor(input);
	}

	/**
	 * Strip color from input
	 * @param input array that should get stripped
	 * @return stripped input
	 */
	public static String[] strip(String[] input){
		for (int i = 0; i < input.length; i++) {
			String s = input[i];
			input[i] = strip(s);
		}
		return input;
	}
	public static TextComponent getColoredTextComponent(String text) {
		return Component.text(color(text));
	}
	public static TextComponent getTextComponent(String text){
		return Component.text(text);
	}
}
