package com.nav.waynav.client;
import com.nav.waynav.block.entity.WaypointBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class WaypointRenderer implements BlockEntityRenderer<WaypointBlockEntity>{
private static final Identifier BEAM_TEXTURE = Identifier.of("minecraft", "textures/entity/beacon_beam.png");
public WaypointRenderer(BlockEntityRendererFactory.Context ctx){
 }
@Override
public void render(WaypointBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay){
matrices.push();
matrices.translate(0.5, 1.0, 0.5);
renderBeam(matrices, vertexConsumers, tickDelta, entity.getWorld().getTime(), 0, 256);
matrices.pop();
 }
private void renderBeam(MatrixStack matrices, VertexConsumerProvider vertexConsumers, float tickDelta, long worldTime, int bottomY, int height){
float f = (float)Math.floorMod(worldTime, 40L) + tickDelta;
VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(BEAM_TEXTURE, true));
matrices.push();
matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f * 2.25F));
float x1 = 0.2f;
float z1 = 0.2f;
float x2 = -0.2f;
float z2 = -0.2f;
renderBeamSide(matrices, vertexConsumer, 0, 255, 255, 160, bottomY, height, x1, z1, x2, z1, x2, z2, x1, z2);
matrices.pop();
}

private void renderBeamSide(MatrixStack matrices, VertexConsumer vertices, int r, int g, int b, int a, int bottomY, int height, float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4){
MatrixStack.Entry entry = matrices.peek();
drawVertex(entry, vertices, r, g, b, a, height, x1, z1, 1, 0);
drawVertex(entry, vertices, r, g, b, a, bottomY, x1, z1, 1, 1);
drawVertex(entry, vertices, r, g, b, a, bottomY, x2, z2, 0, 1);
drawVertex(entry, vertices, r, g, b, a, height, x2, z2, 0, 0);
drawVertex(entry, vertices, r, g, b, a, height, x4, z4, 1, 0);
drawVertex(entry, vertices, r, g, b, a, bottomY, x4, z4, 1, 1);
drawVertex(entry, vertices, r, g, b, a, bottomY, x3, z3, 0, 1);
drawVertex(entry, vertices, r, g, b, a, height, x3, z3, 0, 0);
drawVertex(entry, vertices, r, g, b, a, height, x2, z2, 1, 0);
drawVertex(entry, vertices, r, g, b, a, bottomY, x2, z2, 1, 1);
drawVertex(entry, vertices, r, g, b, a, bottomY, x4, z4, 0, 1);
drawVertex(entry, vertices, r, g, b, a, height, x4, z4, 0, 0);
drawVertex(entry, vertices, r, g, b, a, height, x3, z3, 1, 0);
drawVertex(entry, vertices, r, g, b, a, bottomY, x3, z3, 1, 1);
drawVertex(entry, vertices, r, g, b, a, bottomY, x1, z1, 0, 1);
drawVertex(entry, vertices, r, g, b, a, height, x1, z1, 0, 0);
 }
private void drawVertex(MatrixStack.Entry entry, VertexConsumer vertices, int r, int g, int b, int a, float y, float x, float z, float u, float v){
vertices.vertex(entry.getPositionMatrix(), x, y, z).color(r, g, b, a).texture(u, v).overlay(0).light(15728880).normal(entry, 0.0f, 1.0f, 0.0f);
   }
}