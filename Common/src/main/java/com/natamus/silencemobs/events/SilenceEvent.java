package com.natamus.silencemobs.events;

import com.natamus.collective.functions.EntityFunctions;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.silencemobs.config.ConfigHandler;
import com.natamus.silencemobs.util.Reference;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

public class SilenceEvent {
	public static boolean onEntityDamage(Level world, Entity entity, DamageSource damageSource, float damageAmount) {
		if (world.isClientSide()) {
			return true;
		}
		
		Entity source = damageSource.getEntity();
		if (!(source instanceof Player)) {
			return true;
		}
		
		if (entity instanceof Player) {
			return true;
		}
		
		Player player = (Player)source;
		ItemStack mainhand = player.getItemInHand(InteractionHand.MAIN_HAND);
		if (!mainhand.getItem().equals(Items.STICK)) {
			return true;
		}

		CompoundTag marker = new CompoundTag();
		marker.putBoolean("silencestick", true);

		CustomData customData = mainhand.get(DataComponents.CUSTOM_DATA);
		if (customData == null || !customData.matchedBy(marker)) {
			return true;
		}
		
		String defaultname = EntityFunctions.getEntityString(entity).split("\\[")[0].replace("Entity", "");
		String entityname = "entity";
		
		
		if (entity.hasCustomName()) {
			entityname = entity.getCustomName().getString() + " ";
		}
		else if (!defaultname.equals("")) {
			entityname = defaultname;
		}
			
		if (entity.isSilent()) {
			entity.setSilent(false);
			if (entity.getTags().contains(Reference.MOD_ID + ".silenced")) {
				String originalName = null;
				for (String tag : entity.getTags()) {
					if (tag.startsWith(Reference.MOD_ID + ".name.")) {
						originalName = tag.substring((Reference.MOD_ID + ".name.").length());
						break;
					}
				}
				entity.getTags().removeIf(tag -> tag.equals(Reference.MOD_ID + ".silenced") || tag.startsWith(Reference.MOD_ID + ".name."));
				entity.setCustomName(originalName == null ? null : Component.literal(originalName));
			}
			if (!ConfigHandler.renameSilencedMobs) {
				MessageFunctions.sendTranslatableMessage(player, "collective.silencemobs.message.unsilenced", ChatFormatting.DARK_GREEN, entityname.toLowerCase());
			}
		}
		else {
			entity.setSilent(true);
			if (ConfigHandler.renameSilencedMobs) {
				if (entity.hasCustomName()) {
					entity.addTag(Reference.MOD_ID + ".name." + entity.getCustomName().getString());
				}
				entity.addTag(Reference.MOD_ID + ".silenced");
				entity.setCustomName(MessageFunctions.getTranslatableComponent("collective.silencemobs.gui.silenced", entityname));
			}
			else {
				MessageFunctions.sendTranslatableMessage(player, "collective.silencemobs.message.silenced", ChatFormatting.DARK_GREEN, entityname.toLowerCase());
			}
		}
		
		return false;
	}
}
