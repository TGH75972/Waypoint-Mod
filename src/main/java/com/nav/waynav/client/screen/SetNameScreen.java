package com.nav.waynav.client.screen;
import com.nav.waynav.networking.SetNamePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
public class SetNameScreen extends Screen{
private final BlockPos pos;
private TextFieldWidget nameField;
public SetNameScreen(BlockPos pos){
super(Text.of("Edit Waypoint Name"));
this.pos = pos;
}
@Override
protected void init(){
int x = this.width / 2 - 100;
int y = this.height / 2;
this.nameField = new TextFieldWidget(this.textRenderer, x, y, 200, 20, Text.of(""));
this.nameField.setMaxLength(20);
this.nameField.setFocused(true);
this.addSelectableChild(this.nameField);
this.setInitialFocus(this.nameField);
}
@Override
public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta){
}
@Override
public void render(DrawContext context, int mouseX, int mouseY, float delta){
context.fill(0, 0, this.width, this.height, 0x80000000);
context.drawCenteredTextWithShadow(this.textRenderer, "§lENTER WAYPOINT NAME", this.width / 2, this.height / 2 - 30, 0xFFFFFF);
this.nameField.render(context, mouseX, mouseY, delta);
super.render(context, mouseX, mouseY, delta);
}
@Override
public boolean keyPressed(int keyCode, int scanCode, int modifiers){
if(keyCode == 257 || keyCode == 335){
String name = this.nameField.getText().trim();
ClientPlayNetworking.send(new SetNamePayload(this.pos, name.isEmpty() ? "Waypoint" : name));
this.close();
return true;
  }
return super.keyPressed(keyCode, scanCode, modifiers);
 }
}
