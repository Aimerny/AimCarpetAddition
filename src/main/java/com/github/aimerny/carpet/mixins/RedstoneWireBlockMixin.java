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
        System.out.println("进入mixin");
        BlockPos blockPos2 = blockPos.relative(direction);
        BlockState blockState = blockGetter.getBlockState(blockPos2);
        if (bl) {
            boolean bl2;
            if (!AcaSetting.trapDoorUpdateRedStoneWireDirection) {
                System.out.println("未打开活板门补丁,检测是否是活板门");
                bl2 = blockState.getBlock() instanceof TrapDoorBlock || canSurviveOn(blockGetter, blockPos2, blockState);
                System.out.println("结果:" + bl2);
            }
            else{
                System.out.println("已打开活板门补丁,跳过");
                bl2 = canSurviveOn(blockGetter, blockPos2, blockState);
                System.out.println("结果:" + bl2);
            }

            if (bl2 && shouldConnectTo(blockGetter.getBlockState(blockPos2.above()))) {
                if (blockState.isFaceSturdy(blockGetter, blockPos2, direction.getOpposite())) {
                    System.out.println("朝向更新为UP");
                    return RedstoneSide.UP;
                }
                System.out.println("朝向更新为SIDE");
                return RedstoneSide.SIDE;
            }
        }

        return !shouldConnectTo(blockState, direction) && (blockState.isRedstoneConductor(blockGetter, blockPos2) || !shouldConnectTo(blockGetter.getBlockState(blockPos2.below()))) ? RedstoneSide.NONE : RedstoneSide.SIDE;
    }


    protected static boolean shouldConnectTo(BlockState blockState) {
        return shouldConnectTo(blockState, (Direction)null);
    }

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
