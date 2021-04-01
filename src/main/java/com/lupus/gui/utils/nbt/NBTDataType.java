package com.lupus.gui.utils.nbt;


import com.lupus.MCGUIFramework;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

@SuppressWarnings("unchecked")
public enum NBTDataType {
	INTEGER(Integer.class, PersistentDataType.INTEGER),
	LONG(long.class, PersistentDataType.LONG),
	SHORT(short.class, PersistentDataType.SHORT),
	DOUBLE(double.class, PersistentDataType.DOUBLE),
	FLOAT(float.class, PersistentDataType.FLOAT),
	STRING(String.class, PersistentDataType.STRING),
	BYTE_ARRAY(byte[].class, PersistentDataType.BYTE_ARRAY),
	INTEGER_ARRAY(int[].class, PersistentDataType.INTEGER_ARRAY),
	LONG_ARRAY(long[].class, PersistentDataType.LONG_ARRAY),
	;

	<E> NBTDataType(Class<? extends E> primitive, PersistentDataType<E, E> conversionFunction) {
		this.primitive = primitive;
		dataType = conversionFunction;
	}

	private <E> void setNBTTag(ItemStack itemStack, PersistentDataType<E, E> dataType, String key, E value) {
		ItemMeta meta = itemStack.getItemMeta();
		var pdc = meta.getPersistentDataContainer();
		pdc.set(getNamespacedKey(key), dataType, value);
		itemStack.setItemMeta(meta);
	}

	private <E> E nbtFromItemStack(ItemStack itemStack, String key, Class<? extends E> clazz) {
		ItemMeta meta = itemStack.getItemMeta();
		var pdc = meta.getPersistentDataContainer();
		var data = pdc.get(getNamespacedKey(key), dataType);
		if (clazz.isInstance(data)) {
			return (E) data;
		}
		return null;
	}

	private static NamespacedKey getNamespacedKey(String key) {
		return new NamespacedKey(MCGUIFramework.getInstance(), key);
	}

	public boolean complies(Class<?> clazz) {
		return primitive.isAssignableFrom(clazz);
	}

	private final Class<?> primitive;
	private final PersistentDataType dataType;


	public static <E> void setNBTTag(ItemStack itemStack, String key, E value) {

		Class<? extends E> clazz = (Class<? extends E>) value.getClass();
		for (NBTDataType nbtDataType : values()) {
			if (nbtDataType.complies(clazz)) {
				nbtDataType.setNBTTag(itemStack, nbtDataType.dataType, key, value);
			}
		}

	}

	public static <E> E getNBTTag(ItemStack itemStack, Class<? extends E> clazz, String key) {

		for (NBTDataType nbtDataType : values()) {
			if (nbtDataType.complies(clazz)) {
				return nbtDataType.nbtFromItemStack(itemStack, key, clazz);
			}
		}

		return null;
	}

	public static boolean hasTag(ItemStack itemStack, String key) {
		ItemMeta meta = itemStack.getItemMeta();

		var pdc = meta.getPersistentDataContainer();

		NamespacedKey namespacedKey = getNamespacedKey(key);

		for (NamespacedKey pdcKey : pdc.getKeys())
			if (pdcKey.equals(namespacedKey))
				return true;

		return false;
	}


}
