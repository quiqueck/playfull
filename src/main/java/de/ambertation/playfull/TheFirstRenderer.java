package de.ambertation.playfull;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TheFirstRenderer extends GeoEntityRenderer<TheFirstEntity>
{
	public TheFirstRenderer(EntityRendererFactory.Context ctx)
	{
		super(ctx, new TheFirstModel());
		this.shadowRadius = 0.7F; //change 0.7 to the desired shadow size.
	}
}
