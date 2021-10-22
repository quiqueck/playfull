package de.ambertation.playfull;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TheFirstModel extends AnimatedGeoModel<TheFirstEntity>
{
	@Override
	public Identifier getModelLocation(TheFirstEntity object)
	{
		return new Identifier(Playfull.MOD_ID, "geo/the_first.geo.json");
	}
	
	@Override
	public Identifier getTextureLocation(TheFirstEntity object)
	{
		return new Identifier(Playfull.MOD_ID, "textures/model/entity/the_first.png");
	}
	
	@Override
	public Identifier getAnimationFileLocation(TheFirstEntity object)
	{
		return new Identifier(Playfull.MOD_ID, "animations/the_first.animation.json");
	}
}
