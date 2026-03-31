package com.nav.waynav.client.screen;
import com.nav.waynav.block.entity.WaypointBlockEntity;
import com.nav.waynav.client.util.MapMathUtil;
import com.nav.waynav.util.WaypointTracker;
import net.minecraft.block.MapColor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.Heightmap;
import java.util.ArrayList;
import java.util.List;
public class MinimapScreen extends Screen{
private static final Identifier CUSTOM_ARROW = Identifier.of("waynav", "textures/gui/player_arrow.png");
public MinimapScreen(){
super(Text.of("Minimap"));
}
@Override
public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta){}
@Override
public void render(DrawContext context, int mouseX, int mouseY, float delta){
context.fill(0, 0, this.width, this.height, 0x60000000);
if(client == null || client.player == null || client.world == null)
return;
int centerX = this.width / 2;
int centerY = this.height / 2;
int pX = client.player.getBlockX();
int pZ = client.player.getBlockZ();
for(int x = -64; x < 64; x++){
for(int z = -64; z < 64; z++){
BlockPos topPos = client.world.getTopPosition(Heightmap.Type.WORLD_SURFACE, new BlockPos(pX + x, 0, pZ + z)).down();
MapColor color = client.world.getBlockState(topPos).getMapColor(client.world, topPos);
context.fill(centerX + x, centerY + z, centerX + x + 1, centerY + z + 1, color.color | 0xFF000000);
}
}
drawRotatingIcon(context, centerX, centerY, MapMathUtil.getPlayerRotation(client));
List<BlockPos> ghostWaypoints = new ArrayList<>();
for(BlockPos pos : WaypointTracker.POSITIONS){
if(!(client.world.getBlockEntity(pos) instanceof WaypointBlockEntity be)){
ghostWaypoints.add(pos);
continue; 
}
String name = be.getWaypointName();
String iconType = be.getIconType();
int[] coords = MapMathUtil.getMapPos(pos, client.player.getX(), client.player.getZ());
int drawX = centerX + coords[0];
int drawY = centerY + coords[1];
Identifier iconPath = Identifier.of("waynav", "textures/gui/icons/" + iconType + ".png");
drawWaypointIcon(context, drawX, drawY, iconPath);
if(mouseX >= drawX - 4 && mouseX <= drawX + 4 && mouseY >= drawY - 4 && mouseY <= drawY + 4){
context.drawTooltip(this.textRenderer, Text.of("§b" + name), mouseX, mouseY);
}
 }
if(!ghostWaypoints.isEmpty()){
ghostWaypoints.forEach(WaypointTracker::remove);
 }
  }
private void drawWaypointIcon(DrawContext context, int x, int y, Identifier texture){
MatrixStack matrices = context.getMatrices();
matrices.push();
matrices.translate(0, 0, 10.0f);
context.drawTexture(texture, x - 4, y - 4, 0, 0, 8, 8, 8, 8);
matrices.pop();
}
private void drawRotatingIcon(DrawContext context, int x, int y, float rotation){
MatrixStack matrices = context.getMatrices();
matrices.push();
matrices.translate(x, y, 15.0f);
matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotation));
context.drawTexture(CUSTOM_ARROW, -4, -4, 0, 0, 8, 8, 8, 8);
matrices.pop();
  }
@Override
public boolean shouldPause(){
return false; 
 }
}