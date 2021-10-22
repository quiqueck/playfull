package de.ambertation.playfull;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

public class Playfull implements ModInitializer {
	public static final String MOD_ID = "playfull";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	

	@Override
	public void onInitialize() {
	    GeckoLib.initialize();
	}
}
