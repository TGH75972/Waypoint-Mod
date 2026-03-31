package com.nav.waynav.block;
import com.mojang.serialization.MapCodec;
import com.nav.waynav.Waynav;
import com.nav.waynav.block.entity.WaypointBlockEntity;
import com.nav.waynav.client.screen.IconSelectionScreen;
import com.nav.waynav.client.screen.SetNameScreen;
import com.nav.waynav.util.WaypointTracker;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
public class WaypointBlock extends BlockWithEntity {
public static final MapCodec<WaypointBlock> CODEC = createCodec(WaypointBlock::new);
public WaypointBlock(Settings settings){
super(settings); 
}
@Override
protected MapCodec<? extends BlockWithEntity> getCodec(){
return CODEC; 
}
@Override
public BlockRenderType getRenderType(BlockState state){
return BlockRenderType.MODEL; 
}

@Nullable
@Override
public BlockEntity createBlockEntity(BlockPos pos, BlockState state){ 
return new WaypointBlockEntity(pos, state); 
}
@Override
public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type){
return validateTicker(type, Waynav.WAYPOINT_BE, WaypointBlockEntity::tick);
}
@Override
protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
if(world.isClient){
if(player.isSneaking()){
MinecraftClient.getInstance().setScreen(new IconSelectionScreen(pos));
return ActionResult.SUCCESS;
}
  }
return ActionResult.PASS;
}
@Override
public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack){
if(world.isClient){
WaypointTracker.add(pos);
MinecraftClient.getInstance().setScreen(new SetNameScreen(pos));
 }
}
@Override
public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved){
if(!state.isOf(newState.getBlock())){
if(world.isClient){
WaypointTracker.remove(pos);
 }
super.onStateReplaced(state, world, pos, newState, moved);
 }
  }
}