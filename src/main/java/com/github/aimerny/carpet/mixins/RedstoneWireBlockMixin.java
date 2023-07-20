package com.github.aimerny.carpet.mixins;

import com.github.aimerny.carpet.AcaSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RedStoneWireBlock.class)
public abstract class RedstoneWireBlockMixin {

    @Shadow
    protected abstract boolean canSurviveOn(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState);

    /**
     * @author aim
     * @reason fix
     */
    @Overwrite
    private RedstoneSide getConnectingSide(BlockGetter blockGetter, BlockPos blockPos, Direction direction, boolean bl) {
        BlockPos blockPos2 = blockPos.relative(direction);
        BlockState blockState = blockGetter.getBlockState(blockPos2);
        if (bl) {
            boolean bl2;
            if (!AcaSetting.trapDoorUpdateRedStoneWireDirection) {
                bl2 = blockState.getBlock() instanceof TrapDoorBlock || canSurviveOn(blockGetter, blockPos2, blockState);
            }
            else{
                bl2 = canSurviveOn(blockGetter, blockPos2, blockState);
            }

            if (bl2 && shouldConnectTo(blockGetter.getBlockState(blockPos2.above()))) {
                if (blockState.isFaceSturdy(blockGetter, blockPos2, direction.getOpposite())) {
                    return RedstoneSide.UP;
                }
                return RedstoneSide.SIDE;
            }
        }

        return !shouldConnectTo(blockState, direction) && (blockState.isRedstoneConductor(blockGetter, blockPos2) || !shouldConnectTo(blockGetter.getBlockState(blockPos2.below()))) ? RedstoneSide.NONE : RedstoneSide.SIDE;
    }


    @Shadow
    protected static boolean shouldConnectTo(BlockState blockState) {
        return shouldConnectTo(blockState, (Direction)null);
    }

    @Shadow
    protected static boolean shouldConnectTo(BlockState blockState, @Nullable Direction direction) {
        if (blockState.is(Blocks.REDSTONE_WIRE)) {
            return true;
        } else if (blockState.is(Blocks.REPEATER)) {
            Direction direction2 = (Direction)blockState.getValue(RepeaterBlock.FACING);
            return direction2 == direction || direction2.getOpposite() == direction;
        } else if (blockState.is(Blocks.OBSERVER)) {
            return direction == blockState.getValue(ObserverBlock.FACING);
        } else {
            return blockState.isSignalSource() && direction != null;
        }
    }
}
