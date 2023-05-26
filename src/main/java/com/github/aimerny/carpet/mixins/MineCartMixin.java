package com.github.aimerny.carpet.mixins;

import com.github.aimerny.carpet.AcaSetting;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.entity.vehicle.MinecartHopper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AbstractMinecart.class)
public abstract class MineCartMixin {

    AbstractMinecart minecart = (AbstractMinecart) (Object) this;


    /**
     *  overwrite destroy logic
     * @author
     * @reason
     */
    @Overwrite
    public void destroy(DamageSource damageSource) {
        minecart.remove(Entity.RemovalReason.KILLED);
        if (minecart.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            ItemStack itemStack = null;
            if(AcaSetting.minecartsDropSelf) {

                if (minecart instanceof MinecartChest) {
                    itemStack = new ItemStack(Items.CHEST_MINECART);
                } else if (minecart instanceof MinecartHopper) {
                    itemStack = new ItemStack(Items.HOPPER_MINECART);
                } else {
                    itemStack = new ItemStack(Items.MINECART);
                }
            }else{
                itemStack = new ItemStack(Items.MINECART);
            }
            if (minecart.hasCustomName()) {
                itemStack.setHoverName(minecart.getCustomName());
            }
            minecart.spawnAtLocation(itemStack);
        }
    }



}
