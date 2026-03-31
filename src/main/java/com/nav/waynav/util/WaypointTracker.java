package com.nav.waynav.util;
import net.minecraft.util.math.BlockPos;
import java.util.HashSet;
import java.util.Set;
public class WaypointTracker{
public static final Set<BlockPos> POSITIONS = new HashSet<>();
public static void add(BlockPos pos){
POSITIONS.add(pos);
 }
public static void remove(BlockPos pos){
POSITIONS.remove(pos);
  }
}