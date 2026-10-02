package com.serilum.silencemobs.neoforge.events;

import com.serilum.silencemobs.cmds.CommandSt;
import com.serilum.silencemobs.events.SilenceEvent;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class NeoForgeEntityEvent {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent e) {
		CommandSt.register(e.getDispatcher());
	}

	@SubscribeEvent
	public static void onEntityDamage(LivingIncomingDamageEvent e) {
		Entity entity = e.getEntity();
		if (!SilenceEvent.onEntityDamage(entity.level(), entity, e.getSource(), e.getAmount())) {
			e.setCanceled(true);
		}
	}
}
