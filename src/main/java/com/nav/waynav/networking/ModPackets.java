package com.nav.waynav.networking;
import com.nav.waynav.block.entity.WaypointBlockEntity;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;

public class ModPackets{
public static void registerC2SPackets(){
PayloadTypeRegistry.playC2S().register(SetNamePayload.ID, SetNamePayload.CODEC);
PayloadTypeRegistry.playC2S().register(SetIconPayload.ID, SetIconPayload.CODEC);
ServerPlayNetworking.registerGlobalReceiver(SetNamePayload.ID, (payload, context)->{
context.server().execute(()->{
if(context.player().getWorld().getBlockEntity(payload.pos()) instanceof WaypointBlockEntity be){
be.setWaypointName(payload.name());
BlockState state = context.player().getWorld().getBlockState(payload.pos());
context.player().getWorld().updateListeners(payload.pos(), state, state, 3);
  }
});
});
ServerPlayNetworking.registerGlobalReceiver(SetIconPayload.ID, (payload, context)->{
context.server().execute(()->{
if (context.player().getWorld().getBlockEntity(payload.pos()) instanceof WaypointBlockEntity be){
be.setIconType(payload.iconType());
BlockState state = context.player().getWorld().getBlockState(payload.pos());
context.player().getWorld().updateListeners(payload.pos(), state, state, 3);
}
  });
});
}
}