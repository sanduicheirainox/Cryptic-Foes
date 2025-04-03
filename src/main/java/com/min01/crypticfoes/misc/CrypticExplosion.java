package com.min01.crypticfoes.misc;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class CrypticExplosion extends Explosion
{
	private final Level level;
	private final double x;
	private final double y;
	private final double z;
	private final ParticleOptions particle;
	   
	public CrypticExplosion(Level p_46032_, Entity p_46033_, double p_46034_, double p_46035_, double p_46036_, float p_46037_, ParticleOptions particle) 
	{
		super(p_46032_, p_46033_, p_46034_, p_46035_, p_46036_, p_46037_, false, BlockInteraction.KEEP);
		this.level = p_46032_;
		this.x = p_46034_;
		this.y = p_46035_;
		this.z = p_46036_;
		this.particle = particle;
	}
	
	@Override
	public void finalizeExplosion(boolean p_46076_) 
	{
		super.finalizeExplosion(p_46076_);
		if(this.level instanceof ServerLevel serverLevel)
		{
			serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, this.x, this.y, this.z, 1, 1, 0, 0, 1);
			serverLevel.sendParticles(this.particle, this.x, this.y, this.z, 1, 1, 0, 0, 1);
		}
	}
}
