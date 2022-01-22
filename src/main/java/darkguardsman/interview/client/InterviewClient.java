package darkguardsman.interview.client;

import darkguardsman.interview.content.ModMenu;
import darkguardsman.interview.content.charger.gui.ChargeCrafterScreen;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.gui.screens.MenuScreens;
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
        MenuScreens.register(ModMenu.CHARGER.get(), ChargeCrafterScreen::new);
    }
}
