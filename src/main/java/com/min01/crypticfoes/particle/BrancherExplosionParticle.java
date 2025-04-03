package com.min01.crypticfoes.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BrancherExplosionParticle extends TextureSheetParticle
{
	protected BrancherExplosionParticle(ClientLevel p_107647_, double p_107648_, double p_107649_, double p_107650_, double p_106535_, SpriteSet p_107651_) 
	{
		super(p_107647_, p_107648_, p_107649_, p_107650_, 0.0D, 0.0D, 0.0D);
		this.lifetime = 50 + this.random.nextInt(4);
		this.quadSize = 2.0F * (1.0F - (float)p_106535_ * 0.5F);
		this.pickSprite(p_107651_);
	}
	
	@Override
	public float getQuadSize(float p_107681_) 
	{
		return super.getQuadSize(p_107681_) * 0.1F;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		this.yd -= 0.01D;
		if(this.age <= 20)
		{
			this.alpha -= 0.05D;
		}
	}
	
	@Override
	public ParticleRenderType getRenderType()
	{
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet sprites;

		public Provider(SpriteSet p_106555_) 
		{
			this.sprites = p_106555_;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType p_107421_, ClientLevel p_107422_, double p_107423_, double p_107424_, double p_107425_, double p_107426_, double p_107427_, double p_107428_) 
		{
			return new BrancherExplosionParticle(p_107422_, p_107423_, p_107424_, p_107425_, p_107426_, this.sprites);
		}
	}
}
