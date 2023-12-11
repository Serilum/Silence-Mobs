package com.natamus.silencemobs.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.silencemobs.config.ConfigHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CommandSt {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("st")
			.requires((iCommandSender) -> iCommandSender.getEntity() instanceof Player && (!ConfigHandler.onlyAllowCommandWhenCheatsEnabled || iCommandSender.hasPermission(2)))
			.executes((command) -> {
				processSilencestick(command);
				return 1;
			})
		);
		dispatcher.register(Commands.literal("silencestick")
			.requires((iCommandSender) -> iCommandSender.getEntity() instanceof Player && (!ConfigHandler.onlyAllowCommandWhenCheatsEnabled || iCommandSender.hasPermission(2)))
			.executes((command) -> {
				processSilencestick(command);
				return 1;
			})
		);
	}
	
	public static void processSilencestick(CommandContext<CommandSourceStack> command) {
		CommandSourceStack source = command.getSource();
		Player player = source.getPlayer();
		
		if (ConfigHandler.mustHoldStick) {
			ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);
			if (!held.getItem().equals(Items.STICK)) {
				StringFunctions.sendMessage(player, "You must hold a stick in your main hand to transform it into a silence-stick.", ChatFormatting.DARK_RED);
				return;
			}
			player.getMainHandItem().shrink(1);
		}
		
		ItemStack silencestick = new ItemStack(Items.STICK, 1);
		silencestick.setHoverName(Component.literal(ChatFormatting.GOLD + "The Silence Stick"));
		player.addItem(silencestick);
		StringFunctions.sendMessage(player, "You have been given The Silence Stick! Handle with care.", ChatFormatting.DARK_GREEN);
	}
}