package de.ambertation.playfull;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

public class Playfull implements ModInitializer {
	public static final String MOD_ID = "playfull";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
    public static final EntityType<TheFirstEntity> THE_FIRST_ENTITY = buildEntity(TheFirstEntity::new,
		TheFirstEntity.class, "the_first", .7F, 1.0F, SpawnGroup.CREATURE);
	
	public static DefaultAttributeContainer.Builder createGenericEntityAttributes() {
		return PathAwareEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
							  .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D);
	}
	
	public static <T extends Entity> EntityType<T> buildEntity(EntityType.EntityFactory<T> entity, Class<T> entityClass,
															   String name, float width, float height, SpawnGroup group) {
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			return EntityRegistryBuilder.<T>createBuilder(new Identifier(MOD_ID, name))
										.entity(entity)
										.category(group)
										.dimensions(EntityDimensions.changing(width, height))
										.tracker(10, 10, false)
										.egg(0xFFFF00FF, 0x00FF00FF)
										.hasEgg(true)
										.build();
		}
		return null;
	}

	@Override
	public void onInitialize() {
		GeckoLibMod.DISABLE_IN_DEV = true;
	    GeckoLib.initialize();

        FabricDefaultAttributeRegistry.register(THE_FIRST_ENTITY,
					createGenericEntityAttributes());
	}
}
