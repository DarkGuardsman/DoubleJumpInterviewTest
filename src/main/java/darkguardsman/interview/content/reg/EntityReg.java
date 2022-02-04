package darkguardsman.interview.content.reg;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.ghoul.EntityGhoul;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

/**
 * Created by Robin Seifert on 2/4/2022.
 */
@Mod.EventBusSubscriber(modid = InterviewMod.ID)
public class EntityReg
{
    public static final ResourceLocation GHOUL_ID = new ResourceLocation(InterviewMod.ID, "ghoul");

    private static int nextEntityID = 0;

    @SubscribeEvent
    public static void registerEntity(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(
                EntityEntryBuilder.create()
                        .name(GHOUL_ID.toString())
                        .id(GHOUL_ID, nextEntityID++)
                        .tracker(64, 1, true)
                        .entity(EntityGhoul.class)
                        .egg(0x9ac55e, 0x92978c)
                        .build()
        );
    }
}
