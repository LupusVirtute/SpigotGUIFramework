package com.lupus.gui.selectables;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerSelectableCommandWithArguments extends PlayerSelectableCommand {
	Object[] args;

	public PlayerSelectableCommandWithArguments(ItemStack item, Command command, Object... args) {
		super(item, command);
		this.args = args;
	}

	@Override
	protected void execute(Player player, Object... args) {
		List<Object> arguments = new ArrayList<>();
		arguments.addAll(Arrays.asList(args));
		arguments.addAll(Arrays.asList(this.args));
		super.execute(player, arguments);
	}
}
