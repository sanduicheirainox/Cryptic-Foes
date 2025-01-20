package com.min01.crypticfoes.entity.renderer;

import com.min01.crypticfoes.CrypticFoes;
import com.min01.crypticfoes.entity.living.EntityPetrified;
import com.min01.crypticfoes.entity.model.ModelPetrified;
import com.min01.crypticfoes.network.CrypticNetwork;
import com.min01.crypticfoes.network.UpdatePosArrayPacket;
import com.min01.crypticfoes.util.CrypticClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class PetrifiedRenderer extends MobRenderer<EntityPetrified, ModelPetrified>
{
	public PetrifiedRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelPetrified(p_174304_.bakeLayer(ModelPetrified.LAYER_LOCATION)), 0.5F);
	}
	
	@Override
	public void render(EntityPetrified p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_)
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		Vec3 pos1 = CrypticClientUtil.getWorldPosition(p_115455_, this.model.root(), new Vec3(0.0F, p_115455_.yBodyRot, 0.0F), "body", "arms", "stone");
		p_115455_.posArray[0] = pos1;
		CrypticNetwork.sendToServer(new UpdatePosArrayPacket(p_115455_, pos1, 0));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityPetrified p_115812_) 
	{
		return new ResourceLocation(CrypticFoes.MODID, "textures/entity/petrified.png");
	}
}
