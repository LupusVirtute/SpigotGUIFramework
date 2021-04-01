package com.lupus.commands;

import com.lupus.MCGUIFramework;
import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import com.lupus.gui.IGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TestSeeCommand extends PlayerCommand {
	public TestSeeCommand() {
		super("testgui",
				usage("/testgui", "[guiName]"),
				"tests gui",
				Arrays.asList(),
				Arrays.asList("MCGUIFramework.see"),
				1);
	}

	@Override
	protected void run(Player player, ArgumentList args) throws Exception {
		IGUI gui = MCGUIFramework.getManager().getGUI(args.getArg(String.class, 0));
		if (gui == null) {
			player.sendMessage("GUI DOESN'T EXIST");
			return;
		}
		gui.open(player);

	}

	@NotNull
	@Override
	public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
		if (args.length < 1)
			return super.tabComplete(sender, alias, args);
		Set<String> guiStringSet = new HashSet<>(MCGUIFramework.getManager().getGUINames());
		guiStringSet.removeIf(s -> !s.startsWith(args[0]));
		return new ArrayList<>(guiStringSet);
	}
}
