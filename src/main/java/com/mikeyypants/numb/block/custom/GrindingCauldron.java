package com.mikeyypants.numb.block.custom;

import com.mikeyypants.numb.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.*;

public class GrindingCauldron extends CauldronBlock {



    public int GRINDINGAMOUNT = 0;
    public boolean RECIPEREADY = false;
    public boolean STARTUP = false;
    public HashMap<Item, Integer> INSERTED = new HashMap<Item, Integer>();
    public Map<HashMap<Item, Integer>, Item> ITEMRECIPES = new HashMap<HashMap<Item, Integer>, Item>();

    public GrindingCauldron(Properties pProperties) {
        super(pProperties);

    }
    public void startup(){
        // Items recipes (materials, result) (new HashMap<Item, Integer>{ Item1, Item2 }, )
        HashMap<Item, Integer> VOID_CRYSTAL_SAND_RECIPE = new HashMap<Item, Integer>();
        VOID_CRYSTAL_SAND_RECIPE.put(Items.SAND, 3);
        VOID_CRYSTAL_SAND_RECIPE.put(ModItems.VOID_CRYSTAL.get(), 5);
        ITEMRECIPES.put(VOID_CRYSTAL_SAND_RECIPE, ModItems.VOID_CRYSTAL.get());

    }

    private HashMap CheckRecipes(Item item){
        for (HashMap<Item, Integer> key : ITEMRECIPES.keySet()){
            if(key.containsKey(item)) return key;
        }
        return null;
    }
    private void InsertEmptyRecipe(HashMap<Item, Integer> map, Item item){
        INSERTED.putAll(map);
        for (Item key : INSERTED.keySet()){
            INSERTED.replace(key, 0);
        }
        INSERTED.replace(item, 1);
    }
    private void AddToCauldron(ItemStack item){
        if(INSERTED.get(item.getItem()) != CheckRecipes(item.getItem()).get(item.getItem())) {
            item.setCount(item.getCount() - 1);
            INSERTED.replace(item.getItem(), INSERTED.get(item.getItem()) + 1);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!STARTUP) {startup(); STARTUP = true;}
        if(pLevel.isClientSide()) {

            ItemStack itemsUsed = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
            LOGGER.info(itemsUsed.getItem() +" "+ ModItems.VOID_CRYSTAL.get() + " " + CheckRecipes(itemsUsed.getItem()));
            if(!RECIPEREADY) {
                if (CheckRecipes(itemsUsed.getItem()) != null && !(INSERTED.containsKey(itemsUsed.getItem())))
                    InsertEmptyRecipe(CheckRecipes(itemsUsed.getItem()), itemsUsed.getItem());

                if (INSERTED.containsKey(itemsUsed.getItem())) AddToCauldron(itemsUsed);

                if (INSERTED == CheckRecipes(itemsUsed.getItem())) RECIPEREADY = true;

            } else {
                if(itemsUsed.getItem() == ModItems.HAMMER.get()){
                    if(GRINDINGAMOUNT == 20) {
                        pPlayer.drop(new ItemStack(ITEMRECIPES.get(INSERTED)), false);
                        INSERTED.clear();
                        GRINDINGAMOUNT = 0;

                        RECIPEREADY = false;
                    }
                    else GRINDINGAMOUNT++;
                }
            }
        }


        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

}
