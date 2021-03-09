package com.lupus.gui.utils;

import com.lupus.gui.utils.nbt.NBTDataType;
import org.bukkit.inventory.ItemStack;

public class NBTUtility {
	public static <E> ItemStack setNBTDataValue(ItemStack itemStack, String key, E value){

		NBTDataType.setNBTTag(itemStack,key,value);
		return itemStack;
	}
	public static boolean hasNBTTag(ItemStack itemStack,String key){
		return NBTDataType.hasTag(itemStack, key);
	}
	public static <E> E getNBTValue(ItemStack itemStack,String key,Class<? extends E> clazz){

		var value = NBTDataType.getNBTTag(itemStack,clazz,key);
		return value;
	}
}
