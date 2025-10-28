package com.min01.crypticfoes.misc;

import com.min01.crypticfoes.CrypticFoes;
import com.min01.crypticfoes.item.CrypticItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CrypticCreativeTabs 
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CrypticFoes.MODID);

    public static final RegistryObject<CreativeModeTab> CRYPTIC_FOES = CREATIVE_MODE_TAB.register("crypticfoes", () -> CreativeModeTab.builder()
    		.title(Component.translatable("itemGroup.crypticfoes"))
    		.icon(() -> new ItemStack(CrypticItems.HOWLER_MEMBRANE.get()))
    		.displayItems((enabledFeatures, output) -> 
    		{
    			output.accept(CrypticItems.HOWLER_SPAWN_EGG.get());
    			output.accept(CrypticItems.HOWLER_MEMBRANE.get());
    			output.accept(CrypticItems.SILENCING_BLEND.get());
    			output.accept(CrypticItems.CAVE_SALAD.get());
    			output.accept(CrypticItems.MONSTROUS_HORN.get());
    			output.accept(CrypticItems.SCREAMER.get());
    			output.accept(CrypticItems.HOWLER_HEAD.get());
    		}).build());
}
