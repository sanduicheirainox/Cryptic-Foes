package com.min01.crypticfoes.entity.renderer;

import com.min01.crypticfoes.CrypticFoes;
import com.min01.crypticfoes.entity.projectile.EntityHowlerScream;
import com.min01.crypticfoes.util.CrypticClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

public class HowlerScreamRenderer extends EntityRenderer<EntityHowlerScream>
{
	public HowlerScreamRenderer(Context p_174008_) 
	{
		super(p_174008_);
	}
	
	@Override
	public void render(EntityHowlerScream p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		p_114488_.pushPose();
		float scale = 0.25F + (p_114485_.tickCount * 0.08F);
		scale = Mth.clamp(scale, 0.0F, 3.0F);
		float xRot = Mth.lerp(p_114487_, p_114485_.xRotO, p_114485_.getXRot());
		float yRot = Mth.rotLerp(p_114487_, p_114485_.yRotO, p_114485_.getYRot());
		if(p_114485_.getOwner() instanceof Player)
		{
			xRot = -xRot;
		}
		p_114488_.mulPose(Axis.YP.rotationDegrees(yRot));
		p_114488_.mulPose(Axis.XP.rotationDegrees(xRot));
		p_114488_.scale(scale, scale, scale);
		CrypticClientUtil.drawQuad(p_114488_, p_114489_.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(p_114485_))), 1.0F, p_114490_, p_114485_.alpha);
		p_114488_.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityHowlerScream p_114482_)
	{
		return new ResourceLocation(CrypticFoes.MODID, "textures/entity/howler_scream.png");
	}
}