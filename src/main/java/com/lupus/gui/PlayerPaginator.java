package com.lupus.gui;


import com.lupus.utils.ItemStackUtil;
import com.lupus.utils.Skulls;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class PlayerPaginator extends Paginator {
	UUID[] players;
	List<String> specialLore;
	public PlayerPaginator(String invName,UUID[] players){
		super(invName);
		this.players = players;
		specialLore = new ArrayList<>();
	}
	@Override
	public void onSlotInteraction(Player player, InventoryClickEvent e) {
		int slot = e.getRawSlot();
		if (slot > 35 || slot > players.length){
			return;
		}
		onClickPlayer(player,Bukkit.getOfflinePlayer(players[slot]));
	}
	public abstract void onClickPlayer(Player player, OfflinePlayer clickedOn);

	public void addToSpecialLore(String specialLore){
		if (specialLore == null)
			return;
		this.specialLore.add(specialLore);
	}
	public void addToSpecialLore(List<String> specialLore){
		if (specialLore == null)
			return;
		this.specialLore.addAll(specialLore);
	}
	public void addToSpecialLore(String[] specialLore){
		if (specialLore == null)
			return;
		addToSpecialLore(Arrays.asList(specialLore));
	}

	@Override
	public void setPage(int page) {
		page = page*35+35;
		for (int i=page-35,j=0;j<35 && i < players.length;i++,j++){
			inv.setItem(
				j,
				ItemStackUtil.setItemTitleAndLore(
					Skulls.getSkull(players[i]),
					"&9"+Bukkit.getOfflinePlayer(players[i]).getName(),
					(String[])specialLore.toArray()
				)
			);
		}
	}
}


