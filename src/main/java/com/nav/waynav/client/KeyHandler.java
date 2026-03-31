package com.nav.waynav.client;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
public class KeyHandler{
public static KeyBinding mKey;
public static boolean minimapVisible = false;
public static void register(){
mKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.waynav.minimap",InputUtil.Type.KEYSYM,GLFW.GLFW_KEY_M,"category.waynav"));
ClientTickEvents.END_CLIENT_TICK.register(client->{
while(mKey.wasPressed()){
minimapVisible = !minimapVisible;
  }
});
 }
}
