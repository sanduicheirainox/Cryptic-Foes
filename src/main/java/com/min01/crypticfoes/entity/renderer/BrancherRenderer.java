package com.min01.crypticfoes.entity.renderer;

import com.min01.crypticfoes.CrypticFoes;
import com.min01.crypticfoes.entity.living.EntityBrancher;
import com.min01.crypticfoes.entity.model.ModelBrancher;
import com.min01.crypticfoes.entity.renderer.layer.BrancherLayer;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BrancherRenderer extends MobRenderer<EntityBrancher, ModelBrancher>
{
	public BrancherRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelBrancher(p_174304_.bakeLayer(ModelBrancher.LAYER_LOCATION)), 0.5F);
		this.addLayer(new BrancherLayer(this));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityBrancher p_115812_) 
	{
		return new ResourceLocation(CrypticFoes.MODID, "textures/entity/brancher.png");
	}
}
