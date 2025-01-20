package com.min01.crypticfoes.entity.projectile;

import java.util.Optional;
import java.util.UUID;

import com.min01.crypticfoes.util.CrypticUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class EntityPetrifiedStone extends ThrowableProjectile
{
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityPetrifiedStone.class, EntityDataSerializers.OPTIONAL_UUID);
	
	public EntityPetrifiedStone(EntityType<? extends ThrowableProjectile> p_37466_, Level p_37467_)
	{
		super(p_37466_, p_37467_);
	}

	@Override
	protected void defineSynchedData() 
	{
		this.entityData.define(OWNER_UUID, Optional.empty());
	}
	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) 
	{
		Entity entity = p_37259_.getEntity();
		if(this.getOwner() != null)
		{
			if(entity != this.getOwner() && !entity.isAlliedTo(this.getOwner()))
			{
				entity.hurt(entity.damageSources().mobAttack((LivingEntity) this.getOwner()), 8.0F);
			}
		}
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) 
	{
		super.onHitBlock(p_37258_);
		this.playSound(SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR);
		this.discard();
	}
	
	@Override
	public boolean displayFireAnimation() 
	{
		return false;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_) 
	{
		if(this.entityData.get(OWNER_UUID).isPresent())
		{
			p_37265_.putUUID("Owner", this.entityData.get(OWNER_UUID).get());
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		if(p_37262_.hasUUID("Owner")) 
		{
			this.entityData.set(OWNER_UUID, Optional.of(p_37262_.getUUID("Owner")));
		}
	}
	
	@Override
	public void setOwner(Entity owner)
	{
		this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
	}
	
	@Override
	public Entity getOwner()
	{
		if(this.entityData.get(OWNER_UUID).isPresent()) 
		{
			return CrypticUtil.getEntityByUUID(this.level, this.entityData.get(OWNER_UUID).get());
		}
		return null;
	}
}
