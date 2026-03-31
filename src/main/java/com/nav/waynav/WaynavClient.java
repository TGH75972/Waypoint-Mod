package com.nav.waynav;
import com.nav.waynav.client.screen.MinimapScreen;
import com.nav.waynav.client.screen.IconSelectionScreen;
import com.nav.waynav.client.WaypointRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.gui.screen.Screen; 
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;
public class WaynavClient implements ClientModInitializer{
public static KeyBinding minimapKey;
@Override
public void onInitializeClient(){
BlockEntityRendererFactories.register(Waynav.WAYPOINT_BE, WaypointRenderer::new);
minimapKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.waynav.minimap",InputUtil.Type.KEYSYM,GLFW.GLFW_KEY_M,"category.waynav"));
ClientTickEvents.END_CLIENT_TICK.register(client->{
while(minimapKey.wasPressed()){
if(client.player != null){
if(Screen.hasShiftDown()){
HitResult hit = client.crosshairTarget;
if(hit != null && hit.getType() == HitResult.Type.BLOCK){
client.setScreen(new IconSelectionScreen(((BlockHitResult)hit).getBlockPos()));
  }
}
else{
client.setScreen(new MinimapScreen());
  }
 }
    }
});
 }
}