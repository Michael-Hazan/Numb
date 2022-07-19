package com.mikeyypants.numb;

import com.mikeyypants.numb.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;


public class NumbCreativeModTab {
    public static final CreativeModeTab NUMB_TAB = new CreativeModeTab("numb") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_ELEMENT_ZERO.get());
        }
    };
}
