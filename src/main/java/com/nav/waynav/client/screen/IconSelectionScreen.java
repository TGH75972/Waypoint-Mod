package com.nav.waynav.client.screen;
import com.nav.waynav.networking.SetIconPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
public class IconSelectionScreen extends Screen{
private final BlockPos pos;
private static final String[] ICONS={"default", "creeper", "skeleton", "zombie", "wither"};
public IconSelectionScreen(BlockPos pos){
super(Text.of("Select Waypoint Icon"));
this.pos = pos;
}
@Override
protected void init(){
int startY = this.height / 2 - 60;
for(int i = 0; i < ICONS.length; i++){
String type = ICONS[i];
this.addDrawableChild(ButtonWidget.builder(Text.of(type.toUpperCase()), button->{
ClientPlayNetworking.send(new SetIconPayload(pos, type));
this.close();
}).dimensions(this.width / 2 - 50, startY + (i * 25), 100, 20).build());
 }
}
@Override
public void render(DrawContext context, int mouseX, int mouseY, float delta){
context.fill(0, 0, this.width, this.height, 0x80000000);
context.drawCenteredTextWithShadow(this.textRenderer, "SELECT WAYPOINT ICON", this.width / 2, this.height / 2 - 80, 0xFFFFFF);
super.render(context, mouseX, mouseY, delta);
 }
}
