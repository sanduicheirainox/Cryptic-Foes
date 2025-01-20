package com.min01.crypticfoes.util;

import java.lang.reflect.Method;
import java.util.UUID;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class CrypticUtil 
{
	public static boolean isMoving(Entity entity) 
	{
		return entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> T getEntityByUUID(Level level, UUID uuid)
	{
		Method m = ObfuscationReflectionHelper.findMethod(Level.class, "m_142646_");
		try 
		{
			LevelEntityGetter<Entity> entities = (LevelEntityGetter<Entity>) m.invoke(level);
			return (T) entities.get(uuid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
