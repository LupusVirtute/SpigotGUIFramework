package com.lupus.gui;


import com.lupus.gui.utils.SlotUtility;
import com.lupus.gui.utils.TextUtility;
import com.lupus.gui.utils.coordinates.Vector2D;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Paginator extends GUI {
	static ItemStack previous;
	static ItemStack next;
	static ItemStack exit;

	static {

		var component = TextUtility.getColoredTextComponent("&4Poprzednia Strona");

		previous = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta meta = previous.getItemMeta();
		meta.displayName(component);
		previous.setItemMeta(meta);

		next = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		meta = next.getItemMeta();

		component = TextUtility.getColoredTextComponent("&aNastępna strona");
		meta.displayName(component);
		next.setItemMeta(meta);

		exit = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		meta = exit.getItemMeta();
		component = TextUtility.getColoredTextComponent("&1Wyjdź");
		meta.displayName(component);
		exit.setItemMeta(meta);
	}

	protected int pageCounter = 0;

	public Paginator(String invName) {
		super(invName, 54);
		/*
		 * Representation of Panel
		 * | - Previous Next
		 * / - Leave
		 * - - Items
		 * Y
		 * 3 - - - - - - - - -
		 * 4 | | | / / / | | |
		 * 5 | | | / / / | | |
		 *   0 1 2 3 4 5 6 7 8 X
		 */
		var previousTopLeftVector = new Vector2D<>(0, 4);
		var previousBottomRightVector = new Vector2D<>(2, 5);

		SlotUtility.fillSquare(
				inv,
				previousTopLeftVector,
				previousBottomRightVector,
				previous
		);
		var exitTopLeftVector = new Vector2D<>(3, 4);
		var exitBottomRightVector = new Vector2D<>(5, 5);
		SlotUtility.fillSquare(
				inv,
				exitTopLeftVector,
				exitBottomRightVector,
				exit
		);
		var nextTopLeftVector = new Vector2D<>(6, 4);
		var nextBottomRightVector = new Vector2D<>(8, 5);
		SlotUtility.fillSquare(
				inv,
				nextTopLeftVector,
				nextBottomRightVector,
				next
		);
	}

	@Override
	public void click(Player player, InventoryClickEvent e) {
		ItemStack clickedItem = e.getCurrentItem();
		if (clickedItem == null) {
			onClickedItemNull(player, e);
			return;
		}
		if (clickedItem.isSimilar(previous)) {
			if (pageCounter >= 0)
				previousPage();
		} else if (clickedItem.isSimilar(next)) {
			nextPage();
		} else if (clickedItem.isSimilar(exit)) {
			player.closeInventory();
		} else {
			onSlotInteraction(player, e);
		}
	}

	public void onSlotInteraction(Player player, InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		SelectableItem selItem = null;
		if (item != null)
			selItem = items.stream().filter(o -> o.getItem().isSimilar(item)).findFirst().orElse(null);
		if (selItem != null)
			selItem.run(player);
	}

	public void nextPage() {
		setPage(pageCounter + 1);
	}

	public void previousPage() {
		if (pageCounter > 0)
			setPage(pageCounter - 1);
		else
			setPage(0);
	}

	public void setPage(int page) {
		if (page * 35 > items.size())
			return;
		pageCounter = page;
		for (int i = 0; i <= 35; i++) {
			inv.setItem(i, new ItemStack(Material.AIR));
		}
		for (int i = page * 36, j = i + 36; i < j; i++) {
			if (items.size() <= i) {
				break;
			}
			inv.setItem(i - j + 36, items.get(i).getItem());
		}
	}
}
