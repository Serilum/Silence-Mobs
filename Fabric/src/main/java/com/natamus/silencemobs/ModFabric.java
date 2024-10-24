package com.natamus.silencemobs;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.natamus.collective.fabric.callbacks.CollectiveEntityEvents;
import com.natamus.silencemobs.cmds.CommandSt;
import com.natamus.silencemobs.events.SilenceEvent;
import com.natamus.silencemobs.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
			CommandSt.register(dispatcher);
		});

		CollectiveEntityEvents.ON_LIVING_ATTACK.register((Level world, Entity entity, DamageSource damageSource, float damageAmount) -> {
			return SilenceEvent.onEntityDamage(world, entity, damageSource, damageAmount);
		});
	}

	private static void setGlobalConstants() {

	}
}
