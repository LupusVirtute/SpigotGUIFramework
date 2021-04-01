package com.lupus.gui.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.lupus.MCGUIFramework;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SkullUtility {
	private static final List<ItemStack> numberSkulls = new ArrayList<>();

	static {
	}

	public static void load() {
		FileConfiguration config = ConfigUtility.getConfig(MCGUIFramework.getInstance(), "config.yml");
		ConfigurationSection section = config.getConfigurationSection("numbers");
		if (section != null)
			for (String numbers : section.getKeys(false))
				numberSkulls.add(
						getFromTextureB64(
								section.getString(numbers, "")
						)
				);
	}

	public static ItemStack getSkullFromPlayer(UUID player) {
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);

		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
		skullMeta.setOwningPlayer(offlinePlayer);
		itemStack.setItemMeta(skullMeta);
		return itemStack;
	}

	public static ItemStack getFromTextureB64(String textureValue) {
		return getFromTextureB64(new ItemStack(Material.PLAYER_HEAD), textureValue);
	}

	public static String getSkullStringFromProfile(PlayerProfile profile) {
		for (ProfileProperty property : profile.getProperties()) {
			if (property.getName().equals("textures")) {
				return property.getValue();
			}
		}
		return null;
	}

	public static ItemStack getFromTextureB64(ItemStack itemStack, String textureValue) {
		if (itemStack.getType() != Material.PLAYER_HEAD)
			return itemStack;
		SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
		PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());

		profile.setProperty(new ProfileProperty("textures", textureValue));
		skullMeta.setPlayerProfile(profile);

		itemStack.setItemMeta(skullMeta);
		return itemStack;
	}

	/**
	 * Fills inventory with skulls corresponding to digits taken from number
	 *
	 * @param inv      inventory to fill
	 * @param number   number to set in slots
	 * @param fromSlot from slot
	 * @param toSlot   to slot
	 */
	public static void intToSkullConverter(Inventory inv, int number, int fromSlot, int toSlot) {
		if (number < 0) {
			throw new NotImplementedException();
		}
		if (fromSlot > toSlot) {
			int b = fromSlot;
			fromSlot = toSlot;
			toSlot = b;
		}
		String digits = String.valueOf(number);
		int slotRange = toSlot - fromSlot + 1;
		if (digits.length() > slotRange) {
			return;
		}
		// Fill with zeros
		for (int i = fromSlot; (i + digits.length()) <= toSlot; i++) {
			inv.setItem(i, numberSkulls.get(0));
		}
		for (
				int i = digits.length() - 1, j = toSlot;
				i >= 0 && j >= fromSlot;
				i--, j--
		) {
			inv.setItem(j, numberSkulls.get(digits.charAt(i) - '0'));
		}
	}

	public static boolean isThisItemNumberSkull(ItemStack itemStack) {
		return numberSkulls.contains(itemStack);
	}
}
