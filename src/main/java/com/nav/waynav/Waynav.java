package com.nav.waynav;
import com.nav.waynav.block.WaypointBlock;
import com.nav.waynav.block.entity.WaypointBlockEntity;
import com.nav.waynav.networking.ModPackets;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Waynav implements ModInitializer{
public static final String MOD_ID = "waynav";
public static final Block WAYPOINT_STONE = Registry.register(Registries.BLOCK,Identifier.of(MOD_ID, "waypoint_stone"),new WaypointBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).strength(3.5f, 3.5f).requiresTool().nonOpaque()));
public static final Item WAYPOINT_ITEM = Registry.register(Registries.ITEM,Identifier.of(MOD_ID, "waypoint_stone"),new BlockItem(WAYPOINT_STONE, new Item.Settings()));
public static final BlockEntityType<WaypointBlockEntity> WAYPOINT_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,Identifier.of(MOD_ID, "waypoint_be"),BlockEntityType.Builder.create(WaypointBlockEntity::new, WAYPOINT_STONE).build(null));
@Override
public void onInitialize(){
ModPackets.registerC2SPackets();
ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries->{
entries.add(WAYPOINT_ITEM);
  });
 }
}