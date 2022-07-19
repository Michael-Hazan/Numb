package com.mikeyypants.numb.block;

import com.mikeyypants.numb.Main;
import com.mikeyypants.numb.NumbCreativeModTab;
import com.mikeyypants.numb.block.custom.GrindingCauldron;
import com.mikeyypants.numb.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks{

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);

    public static RegistryObject<Block> VOID_ORE = registerBlock("void_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(40f).requiresCorrectToolForDrops()));

    public static RegistryObject<Block> DENSE_BLUE_ICE = registerBlock("dense_blue_ice",
            () -> new Block(BlockBehaviour.Properties.of(Material.ICE_SOLID)
                    .strength(10f).sound(SoundType.GLASS).requiresCorrectToolForDrops()));

    public static RegistryObject<FallingBlock> VOID_CRYSTAL_SAND = registerBlock("void_crystal_sand",
            () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND)
                    .strength(10f).sound(SoundType.SAND).requiresCorrectToolForDrops()));




    public static RegistryObject<CauldronBlock> GRINDING_CAULDRON = registerBlock("grinding_cauldron",
            () -> new GrindingCauldron(BlockBehaviour.Properties.of(Material.METAL)));


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, NumbCreativeModTab.NUMB_TAB);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }



    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);

    }


}
