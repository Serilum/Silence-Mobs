package com.natamus.silencemobs.forge.events;

import com.natamus.silencemobs.cmds.CommandSt;
import com.natamus.silencemobs.events.SilenceEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeEntityEvent {
	public static void registerEventsInBus() {
		BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeEntityEvent.class);
	}

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent e) {
    	CommandSt.register(e.getDispatcher());
    }

	@SubscribeEvent
	public static boolean onEntityDamage(LivingAttackEvent e) {
		Entity entity = e.getEntity();
		if (!SilenceEvent.onEntityDamage(entity.level(), entity, e.getSource(), e.getAmount())) {
			return true;
		}
		return false;
	}
}
