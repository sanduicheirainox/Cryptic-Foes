package com.min01.crypticfoes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.min01.crypticfoes.effect.CrypticEffects;
import com.min01.crypticfoes.util.CrypticClientUtil;

import net.minecraft.client.KeyMapping;

@Mixin(KeyMapping.class)
public class MixinKeyMapping
{
	@Inject(at = @At("TAIL"), method = "isDown", cancellable = true)
	private void isDown(CallbackInfoReturnable<Boolean> cir)
	{
		if(CrypticClientUtil.MC.player != null)
		{
			KeyMapping mapping = KeyMapping.class.cast(this);
			if(mapping == CrypticClientUtil.MC.options.keyJump || mapping == CrypticClientUtil.MC.options.keyRight || mapping == CrypticClientUtil.MC.options.keyLeft || mapping == CrypticClientUtil.MC.options.keyUp || mapping == CrypticClientUtil.MC.options.keyDown)
			{
				if(CrypticClientUtil.MC.player.hasEffect(CrypticEffects.STUNNED.get()) && !CrypticClientUtil.MC.player.isSpectator())
				{
					cir.setReturnValue(false);
				}
			}
		}
	}
}
