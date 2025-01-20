package com.min01.crypticfoes.entity.renderer;

import com.min01.crypticfoes.CrypticFoes;
import com.min01.crypticfoes.entity.living.EntityPetrified;
import com.min01.crypticfoes.entity.model.ModelPetrified;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PetrifiedRenderer extends MobRenderer<EntityPetrified, ModelPetrified>
{
	public PetrifiedRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelPetrified(p_174304_.bakeLayer(ModelPetrified.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityPetrified p_115812_) 
	{
		return new ResourceLocation(CrypticFoes.MODID, "textures/entity/petrified.png");
	}
}
