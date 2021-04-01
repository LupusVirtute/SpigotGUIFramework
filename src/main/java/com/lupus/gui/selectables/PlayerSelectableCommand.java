package com.lupus.gui.selectables;

import com.lupus.gui.PlayerSelectableItem;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectableCommand extends PlayerSelectableItem {
	Command commandToRun;

	public PlayerSelectableCommand(ItemStack item, Command command) {
		super(true, item);
		commandToRun = command;
	}

	@Override
	protected void execute(Player player, Object... args) {
		List<String> arguments = new ArrayList<>();
		for (int i = 0; i < args.length; i++)
			if (args[i] instanceof String)
				arguments.add((String) args[i]);
		commandToRun.execute(player, "", arguments.toArray(new String[0]));
	}
}
