package darkguardsman.interview.client;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Created by Robin Seifert on 12/21/2021.
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class InterviewClient
{
    public static void subscribeClientEvents()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(InterviewClient::registerScreens);
    }

    public static void registerScreens(FMLClientSetupEvent event)
    {
        //MenuScreens.register(MarbleMenus.MARBLE.get(), MarbleColorItemScreen::new);
    }
}
