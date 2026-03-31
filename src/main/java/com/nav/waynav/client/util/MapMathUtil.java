package com.nav.waynav.client.util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
public class MapMathUtil{
private static final int CLAMP_RADIUS = 60;
public static int[] getMapPos(BlockPos targetPos, double playerX, double playerZ){
double diffX = (targetPos.getX() - playerX);
double diffZ = (targetPos.getZ() - playerZ);
int drawX = (int)MathHelper.clamp(diffX, -CLAMP_RADIUS, CLAMP_RADIUS);
int drawZ = (int)MathHelper.clamp(diffZ, -CLAMP_RADIUS, CLAMP_RADIUS);
return new int[]{drawX, drawZ};
}
public static float getPlayerRotation(MinecraftClient client){
return client.player != null ? client.player.getYaw() + 180 : 0;
 }
}