package com.natamus.silencemobs;

import com.natamus.collective.translations.ServerTranslationPack;
import com.natamus.silencemobs.config.ConfigHandler;
import com.natamus.silencemobs.util.Reference;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		ServerTranslationPack.requireClientTranslations(Reference.NAME);
	}
}
