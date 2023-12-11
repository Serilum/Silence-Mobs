package com.natamus.silencemobs.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.silencemobs.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean onlyAllowCommandWhenCheatsEnabled = true;
	@Entry public static boolean mustHoldStick = true;
	@Entry public static boolean renameSilencedMobs = true;

	public static void initConfig() {
		configMetaData.put("onlyAllowCommandWhenCheatsEnabled", Arrays.asList(
			"If enabled, only allows the /silencestick command with cheats enabled."
		));
		configMetaData.put("mustHoldStick", Arrays.asList(
			"If disabled, a stick will be generated via the /silencestick command instead of having to hold a stick while using the command."
		));
		configMetaData.put("renameSilencedMobs", Arrays.asList(
			"If enabled, whenever a player hits a non-silenced mob with The Silence Stick it will set their name to 'Silenced Entity'."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}