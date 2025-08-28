package com.min01.crypticfoes.entity.renderer;

import com.min01.crypticfoes.CrypticFoes;
import com.min01.crypticfoes.entity.living.EntityHowler;
import com.min01.crypticfoes.entity.model.ModelHowler;
import com.min01.crypticfoes.entity.renderer.layer.HowlerLayer;
import com.min01.crypticfoes.network.CrypticNetwork;
import com.min01.crypticfoes.network.UpdatePosArrayPacket;
import com.min01.crypticfoes.util.CrypticClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class HowlerRenderer extends MobRenderer<EntityHowler, ModelHowler>
{
	public HowlerRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelHowler(p_174304_.bakeLayer(ModelHowler.LAYER_LOCATION)), 0.5F);
		this.addLayer(new HowlerLayer(this));
	}
	
	@Override
	public void render(EntityHowler p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_)
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		Vec3 pos1 = CrypticClientUtil.getWorldPosition(p_115455_, this.model.root(), new Vec3(0.0F, p_115455_.yBodyRot, 0.0F), "howlersleeppivot", "howler", "body", "body_no_hands", "head");
		p_115455_.posArray[0] = pos1;
		CrypticNetwork.sendToServer(new UpdatePosArrayPacket(p_115455_, pos1, 0));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityHowler p_115812_) 
	{
		return new ResourceLocation(CrypticFoes.MODID, "textures/entity/howler.png");
	}
}
