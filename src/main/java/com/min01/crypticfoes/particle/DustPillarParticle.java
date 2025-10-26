package com.min01.crypticfoes.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TerrainParticle;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DustPillarParticle
{
	@OnlyIn(Dist.CLIENT)
	public static class Provider extends TerrainParticle.Provider
	{
		public Particle createParticle(BlockParticleOption p_108304_, ClientLevel p_108305_, double p_108306_, double p_108307_, double p_108308_, double p_108309_, double p_108310_, double p_108311_)
		{
			Particle particle = super.createParticle(p_108304_, p_108305_, p_108306_, p_108307_, p_108308_, p_108309_, p_108310_, p_108311_);
			if(particle != null)
			{
                particle.setParticleSpeed(p_108305_.random.nextGaussian() / 30.0, p_108310_ + p_108305_.random.nextGaussian() / 2.0, p_108305_.random.nextGaussian() / 30.0);
                particle.setLifetime(p_108305_.random.nextInt(20) + 20);
			}
			return particle;
		}
	}
}
