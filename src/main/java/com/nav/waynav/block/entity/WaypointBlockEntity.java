package com.nav.waynav.block.entity;
import com.nav.waynav.Waynav;
import com.nav.waynav.util.WaypointTracker;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
public class WaypointBlockEntity extends BlockEntity{
private String waypointName = "Waypoint";
private String iconType = "default";
public WaypointBlockEntity(BlockPos pos, BlockState state){
super(Waynav.WAYPOINT_BE, pos, state);
}
public static void tick(World world, BlockPos pos, BlockState state, WaypointBlockEntity be){
if(world.isClient){
WaypointTracker.add(pos);
  }
 }
public void setWaypointName(String name){
this.waypointName = name;
this.markDirty();
}
public String getWaypointName(){
return this.waypointName; 
 }
public void setIconType(String type){
this.iconType = type;
this.markDirty();
}
public String getIconType(){
return this.iconType; 
}
@Override
protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup){
nbt.putString("waypointName", this.waypointName);
nbt.putString("iconType", this.iconType);
super.writeNbt(nbt, registryLookup);
}
@Override
protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup){
super.readNbt(nbt, registryLookup);
this.waypointName = nbt.getString("waypointName");
this.iconType = nbt.contains("iconType") ? nbt.getString("iconType") : "default";
}
@Nullable
@Override
public Packet<ClientPlayPacketListener> toUpdatePacket(){
return BlockEntityUpdateS2CPacket.create(this);
}
@Override
public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup){
return createNbt(registryLookup);
 }
}