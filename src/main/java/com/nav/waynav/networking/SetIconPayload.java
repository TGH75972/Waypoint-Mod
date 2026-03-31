package com.nav.waynav.networking;
import com.nav.waynav.Waynav;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
public record SetIconPayload(BlockPos pos, String iconType) implements CustomPayload{
public static final Id<SetIconPayload> ID = new Id<>(Identifier.of(Waynav.MOD_ID, "set_icon"));
public static final PacketCodec<RegistryByteBuf, SetIconPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, SetIconPayload::pos,PacketCodecs.STRING, SetIconPayload::iconType,SetIconPayload::new);
@Override
public Id<? extends CustomPayload> getId(){
return ID;
  }
}