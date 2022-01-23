package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.charger.ChargeItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Created by Robin Seifert on 12/21/2021.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InterviewMod.ID);
    //Charge fuels
    public static final RegistryObject<Item> BLUE_METAL = ITEMS.register("blue_metal", () -> new Item(new Item.Properties().tab(ModCreativeTab.TAB)));
    public static final RegistryObject<Item> GREEN_METAL = ITEMS.register("green_metal", () -> new Item(new Item.Properties().tab(ModCreativeTab.TAB)));
    public static final RegistryObject<Item> PURPLE_METAL = ITEMS.register("purple_metal", () -> new Item(new Item.Properties().tab(ModCreativeTab.TAB)));
    public static final RegistryObject<Item> RED_METAL = ITEMS.register("red_metal", () -> new Item(new Item.Properties().tab(ModCreativeTab.TAB)));

    //Charge item
    public static final RegistryObject<Item> CHARGE = ITEMS.register("charge_item", () -> new ChargeItem(new Item.Properties().tab(ModCreativeTab.TAB).stacksTo(1)));

    //Spawn eggs
    public static final RegistryObject<Item> GHOUL_EGG = ITEMS.register("ghoul_egg", () -> new ForgeSpawnEggItem(ModEntities.GHOUL, 0x9ac55e, 0x92978c, new Item.Properties().tab(ModCreativeTab.TAB)));

    //Block that charges item via fuel crafting
    public static final RegistryObject<Item> CHARGER_BLOCK = fromBlock(ModBlocks.CHARGER);

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeTab.TAB)));
    }
}
