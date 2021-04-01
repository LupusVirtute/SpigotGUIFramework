package com.lupus.commands;

import com.lupus.MCGUIFramework;
import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ReloadCommand extends LupusCommand {
	public ReloadCommand() {
		super("guireload",
				usage("/guireload"),
				"reloads gui",
				Arrays.asList("mcguireload", "mcgr"),
				Arrays.asList("MCGUIFramework.reload"),
				0);
	}

	@Override
	public void run(CommandSender commandSender, ArgumentList args) {
		commandSender.sendMessage("Reloading...");
		long time = System.currentTimeMillis();
		MCGUIFramework.getManager().load();
		commandSender.sendMessage("Reloaded time: " + (System.currentTimeMillis() - time) + "ms");

	}
}
