package com.nav.waynav.networking;
import com.nav.waynav.Waynav;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
public record SetNamePayload(BlockPos pos, String name) implements CustomPayload{
public static final Id<SetNamePayload> ID = new Id<>(Identifier.of(Waynav.MOD_ID, "set_name"));
public static final PacketCodec<RegistryByteBuf, SetNamePayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, SetNamePayload::pos,PacketCodecs.STRING, SetNamePayload::name,SetNamePayload::new);
@Override
public Id<? extends CustomPayload> getId(){
return ID;
}
}