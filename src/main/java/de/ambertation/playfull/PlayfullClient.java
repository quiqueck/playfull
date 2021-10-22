package de.ambertation.playfull;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class PlayfullClient implements ClientModInitializer {	
	
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(Playfull.THE_FIRST_ENTITY, TheFirstRenderer::new);
	}
}
