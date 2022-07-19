package com.mikeyypants.numb.item;

import com.mikeyypants.numb.Main;
import com.mikeyypants.numb.NumbCreativeModTab;
import com.mikeyypants.numb.block.custom.GrindingCauldron;
import com.mikeyypants.numb.item.custom.HammerItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<Item> VOID_CRYSTAL = ITEMS.register( "void_crystal",
            () -> new Item(new Item.Properties().tab(NumbCreativeModTab.NUMB_TAB)));
    public static final RegistryObject<Item> UNIVERSE_AMETHYST = ITEMS.register("universe_amethyst",
            () -> new Item(new Item.Properties().tab(NumbCreativeModTab.NUMB_TAB).fireResistant()));

    public static final RegistryObject<Item> RAW_ELEMENT_ZERO = ITEMS.register("raw_element_zero",
            () -> new Item(new Item.Properties().tab(NumbCreativeModTab.NUMB_TAB).fireResistant()));
    public static final RegistryObject<Item> REFINED_ELEMENT_ZERO = ITEMS.register("refined_element_zero",
            () -> new Item(new Item.Properties().tab(NumbCreativeModTab.NUMB_TAB).stacksTo(1)));

    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer",
            () -> new HammerItem(new Item.Properties().tab(NumbCreativeModTab.NUMB_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}
