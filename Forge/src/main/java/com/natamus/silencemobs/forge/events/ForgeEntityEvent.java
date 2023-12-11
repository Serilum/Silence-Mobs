package com.natamus.silencemobs.forge.events;

import com.natamus.silencemobs.cmds.CommandSt;
import com.natamus.silencemobs.events.SilenceEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeEntityEvent {
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	CommandSt.register(e.getDispatcher());
    }

	@SubscribeEvent
	public void onEntityDamage(LivingAttackEvent e) {
		Entity entity = e.getEntity();
		if (!SilenceEvent.onEntityDamage(entity.getCommandSenderWorld(), entity, e.getSource(), e.getAmount())) {
			e.setCanceled(true);
		}
	}
}
