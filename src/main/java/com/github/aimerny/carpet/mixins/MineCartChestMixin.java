package com.github.aimerny.carpet.mixins;

import com.github.aimerny.carpet.AcaSetting;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecartChest.class)
public abstract class MineCartChestMixin {

    /**
     * @author
     * @reason
     */
    @Redirect(method = "destroy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    public boolean clearOtherDrop(GameRules instance, GameRules.Key<GameRules.BooleanValue> key) {
        if(AcaSetting.minecartsDropSelf) {
            return false;
        }
        return instance.getBoolean(key);
    }
}
