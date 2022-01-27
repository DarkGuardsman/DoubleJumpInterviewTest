package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.charger.ItemCharge;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Robin Seifert on 12/21/2021.
 */
@Mod.EventBusSubscriber(modid = InterviewMod.ID)
public class ModItems
{
    @GameRegistry.ObjectHolder(InterviewMod.ID + ":blue_metal")
    public static Item blueMetal;
    @GameRegistry.ObjectHolder(InterviewMod.ID + ":blue_metal")
    public static Item greenMetal;
    @GameRegistry.ObjectHolder(InterviewMod.ID + ":blue_metal")
    public static Item purpleMetal;
    @GameRegistry.ObjectHolder(InterviewMod.ID + ":blue_metal")
    public static Item redMetal;
    @GameRegistry.ObjectHolder(InterviewMod.ID + ":charge")
    public static Item charge;
    @GameRegistry.ObjectHolder(InterviewMod.ID + ":charger")
    public static Item chargerBlock;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        //Charge fuels
        event.getRegistry().register(new Item().setCreativeTab(ModCreativeTab.TAB).setRegistryName(InterviewMod.ID, "blue_metal"));
        event.getRegistry().register(new Item().setCreativeTab(ModCreativeTab.TAB).setRegistryName(InterviewMod.ID, "green_metal"));
        event.getRegistry().register(new Item().setCreativeTab(ModCreativeTab.TAB).setRegistryName(InterviewMod.ID, "purple_metal"));
        event.getRegistry().register(new Item().setCreativeTab(ModCreativeTab.TAB).setRegistryName(InterviewMod.ID, "red_metal"));

        //Charge item
        event.getRegistry().register(new ItemCharge().setCreativeTab(ModCreativeTab.TAB).setMaxStackSize(1).setRegistryName(InterviewMod.ID, "charge"));

        //Spawn eggs
        //event.getRegistry().register("ghoul_egg", () -> new ForgeSpawnEggItem(ModEntities.GHOUL, 0x9ac55e, 0x92978c, new Item.Properties().tab(ModCreativeTab.TAB)));

        //Block that charges item via fuel crafting
        event.getRegistry().register(new ItemBlock(ModBlocks.charger).setCreativeTab(ModCreativeTab.TAB).setRegistryName(ModBlocks.charger.getRegistryName()));
    }
}
