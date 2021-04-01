package com.lupus.gui.utils;

import com.lupus.gui.utils.coordinates.Vector2D;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SlotUtility {

	/**
	 * Fills square in inventory
	 *
	 * @param inventoryToFill inventory that is gonna be filled
	 * @param top             top-left of the square
	 * @param bottom          bottom-right of the square
	 * @param item            item that should be put in the fill
	 */
	public static void fillSquare(Inventory inventoryToFill, Vector2D<Integer> top, Vector2D<Integer> bottom, ItemStack item) {
		Integer[] slotsToFill = getSlotsBetween(top, bottom);
		for (Integer integer : slotsToFill) {
			inventoryToFill.setItem(integer, item);
		}
	}

	/**
	 * Fills square in inventory
	 *
	 * @param inventoryToFill inventory that is gonna be filled
	 * @param top             top-left of the square
	 * @param bottom          bottom-right of the square
	 * @param items           items that should be put in the fill remaining items get discarded
	 */
	public static void fillSquare(Inventory inventoryToFill, Vector2D<Integer> top, Vector2D<Integer> bottom, ItemStack... items) {
		Integer[] slotsToFill = getSlotsBetween(top, bottom);
		int idx = 0;
		for (Integer integer : slotsToFill) {
			inventoryToFill.setItem(integer, items[idx]);
			idx++;
		}
	}

	/**
	 * Get slots between 2 vectors
	 *
	 * @param from from vector
	 * @param to   to vector
	 * @return integer list
	 */
	private static Integer[] getSlotsBetween(Vector2D<Integer> from, Vector2D<Integer> to) {
		if (!isGreater(to, from)) {
			Vector2D<Integer> swap = to;
			to = from;
			from = swap;
		}
		List<Integer> slotList = new ArrayList<>();
		for (int y = from.getY(); y <= to.getY(); y++) {
			for (int x = from.getX(); x <= to.getX(); x++) {
				slotList.add(getSlotFromXY(x, y));
			}
		}
		return slotList.toArray(new Integer[0]);
	}

	/**
	 * returns slot from XY Grid
	 *
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return Inventory slot
	 */
	private static int getSlotFromXY(int x, int y) {
		return getSlotFromXY(new Vector2D<>(x, y));
	}

	/**
	 * returns slot from XY Grid
	 *
	 * @param vector vector that needs conversion
	 * @return Inventory slot
	 */
	private static int getSlotFromXY(Vector2D<Integer> vector) {
		return vector.getX() + (vector.getY() * 9);
	}

	/**
	 * Checks which vector is greater
	 *
	 * @param supposedToBeGreater vector that supposed to be greater
	 * @param supposedToBeLower   vector that supposed to be lower
	 * @return true or false
	 */
	private static boolean isGreater(Vector2D<Integer> supposedToBeGreater, Vector2D<Integer> supposedToBeLower) {
		if (supposedToBeGreater.getY() > supposedToBeLower.getY()) {
			return true;
		}
		return supposedToBeGreater.getX() > supposedToBeLower.getY();
	}
}
