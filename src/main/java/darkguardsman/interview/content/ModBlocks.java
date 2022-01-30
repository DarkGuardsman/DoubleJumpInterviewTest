package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.charger.BlockChargeCrafter;
import darkguardsman.interview.content.charger.TileEntityChargeCrafter;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
@Mod.EventBusSubscriber(modid = InterviewMod.ID)
public class ModBlocks
{
    @GameRegistry.ObjectHolder(InterviewMod.ID + ":charger")
    public static Block charger;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new BlockChargeCrafter().setCreativeTab(ModCreativeTab.TAB).setUnlocalizedName(InterviewMod.ID + ":charger").setRegistryName(InterviewMod.ID, "charger"));

        GameRegistry.registerTileEntity(TileEntityChargeCrafter.class, new ResourceLocation(InterviewMod.ID, "charger"));
    }
}
