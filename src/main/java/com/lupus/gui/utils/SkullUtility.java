package com.lupus.gui.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class SkullUtility {
	public static ItemStack getSkullFromPlayer(UUID player){
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);
		
		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
		skullMeta.setOwningPlayer(offlinePlayer);
		itemStack.setItemMeta(skullMeta);
		return itemStack;
	}
	public static ItemStack getFromTextureB64(String textureValue){
		return getFromTextureB64(new ItemStack(Material.PLAYER_HEAD),textureValue);
	}
	public static String getSkullStringFromProfile(PlayerProfile profile){
		for (ProfileProperty property : profile.getProperties()) {
			if (property.getName().equals("textures")) {
				return property.getValue();
			}
		}
		return null;
	}
	public static ItemStack getFromTextureB64(ItemStack itemStack,String textureValue){
		if (itemStack.getType() != Material.PLAYER_HEAD)
			return itemStack;
		SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
		PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());

		profile.setProperty(new ProfileProperty("textures",textureValue));
		skullMeta.setPlayerProfile(profile);

		itemStack.setItemMeta(skullMeta);
		return itemStack;
	}
}
