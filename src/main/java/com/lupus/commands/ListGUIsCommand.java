package com.lupus.commands;

import com.lupus.MCGUIFramework;
import com.lupus.command.framework.commands.LupusCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ListGUIsCommand extends LupusCommand {
	public ListGUIsCommand() {
		super("listgui",
				usage("/listgui","[guiPage]"),
				"lists",
				Arrays.asList(),
				Arrays.asList("MCGUIFramework.list"),
				1);	}

	@Override
	public void run(CommandSender commandSender, String[] strings) {
		commandSender.sendMessage("--- GUI LIST ---");
		for (String guiName : MCGUIFramework.getManager().getGUINames()) {
			commandSender.sendMessage("- "+guiName);
		}
		commandSender.sendMessage("--- GUI END  ---");
	}
}
