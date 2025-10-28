package com.min01.crypticfoes.blockentity.renderer;

import java.util.Map;

import javax.annotation.Nullable;

import com.min01.crypticfoes.block.model.CrypticSkullModelBase;
import com.min01.crypticfoes.blockentity.CrypticSkullBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;

public class CrypticSkullRenderer extends SkullBlockRenderer
{
	private final Map<SkullBlock.Type, SkullModelBase> modelByType;
	   
	public CrypticSkullRenderer(Context p_173660_) 
	{
		super(p_173660_);
		this.modelByType = createSkullRenderers(p_173660_.getModelSet());
	}

	@Override
	public void render(SkullBlockEntity p_112534_, float p_112535_, PoseStack p_112536_, MultiBufferSource p_112537_, int p_112538_, int p_112539_)
	{
		float f = p_112534_.getAnimation(p_112535_);
		BlockState blockstate = p_112534_.getBlockState();
		boolean flag = blockstate.getBlock() instanceof WallSkullBlock;
		Direction direction = flag ? blockstate.getValue(WallSkullBlock.FACING) : null;
		int i = flag ? RotationSegment.convertToSegment(direction.getOpposite()) : blockstate.getValue(SkullBlock.ROTATION);
		float f1 = RotationSegment.convertToDegrees(i);
		SkullBlock.Type skullblock$type = ((AbstractSkullBlock) blockstate.getBlock()).getType();
		SkullModelBase skullmodelbase = this.modelByType.get(skullblock$type);
		RenderType rendertype = getRenderType(skullblock$type, p_112534_.getOwnerProfile());
		if(skullmodelbase instanceof CrypticSkullModelBase base && p_112534_ instanceof CrypticSkullBlockEntity blockEntity)
		{
			renderSkull(blockEntity, p_112535_, direction, f1, f, p_112536_, p_112537_, p_112538_, base, rendertype);
		}
	}

	public static void renderSkull(CrypticSkullBlockEntity blockEntity, float partialTicks, @Nullable Direction p_173664_, float p_173665_, float p_173666_, PoseStack p_173667_, MultiBufferSource p_173668_, int p_173669_, CrypticSkullModelBase p_173670_, RenderType p_173671_) 
	{
		p_173667_.pushPose();
		if(p_173664_ == null)
		{
			p_173667_.translate(0.5F, 0.0F, 0.5F);
		} 
		else
		{
			p_173667_.translate(0.5F - (float) p_173664_.getStepX() * 0.25F, 0.25F,	0.5F - (float) p_173664_.getStepZ() * 0.25F);
		}
		p_173667_.scale(-1.0F, -1.0F, 1.0F);
		VertexConsumer vertexconsumer = p_173668_.getBuffer(p_173671_);
		p_173670_.setupAnim(blockEntity, partialTicks + blockEntity.tickCount);
		p_173670_.setupAnim(p_173666_, p_173665_, 0.0F);
		p_173670_.renderToBuffer(p_173667_, vertexconsumer, p_173669_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		p_173667_.popPose();
	}
}
