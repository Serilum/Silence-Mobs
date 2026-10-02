package com.serilum.silencemobs;

import com.natamus.collective.translations.ServerTranslationPack;
import com.serilum.silencemobs.config.ConfigHandler;
import com.serilum.silencemobs.util.Reference;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();
		load();
	}

	private static void load() {
		ServerTranslationPack.requireClientTranslations(Reference.NAME);
	}
}
