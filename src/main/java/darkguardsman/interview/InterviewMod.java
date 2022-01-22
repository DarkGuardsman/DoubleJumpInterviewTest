package darkguardsman.interview;

import darkguardsman.interview.client.InterviewClient;
import darkguardsman.interview.content.ModBlockEntities;
import darkguardsman.interview.content.ModBlocks;
import darkguardsman.interview.content.ModItems;
import darkguardsman.interview.content.ModMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(InterviewMod.ID)
public final class InterviewMod
{
    public static final String ID = "djinterview";

    public InterviewMod()
    {
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlockEntities.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModMenu.MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InterviewClient::subscribeClientEvents);
    }
}
