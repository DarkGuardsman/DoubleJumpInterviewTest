package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModCreativeTab.TAB);

    //Charge fuels
    public static final RegistryObject<Item> BLUE_METAL = ITEMS.register("blue_metal", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> GREEN_METAL = ITEMS.register("green_metal", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> PURPLE_METAL = ITEMS.register("purple_metal", () -> new Item(ITEM_PROPERTIES));
    public static final RegistryObject<Item> RED_METAL = ITEMS.register("red_metal", () -> new Item(ITEM_PROPERTIES));

    //Charge item
    public static final RegistryObject<Item> CHARGE = ITEMS.register("charge_item", () -> new Item(ITEM_PROPERTIES));

    //Block that charges item via fuel crafting
    public static final RegistryObject<Item> CHARGER_BLOCK = fromBlock(ModBlocks.CHARGER);

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
