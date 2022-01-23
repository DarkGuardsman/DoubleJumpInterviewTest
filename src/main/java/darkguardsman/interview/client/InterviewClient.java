package darkguardsman.interview.client;

import darkguardsman.interview.content.ModEntities;
import darkguardsman.interview.content.ModMenu;
import darkguardsman.interview.content.charger.gui.ChargeCrafterScreen;
import darkguardsman.interview.content.ghoul.render.GhoulModel;
import darkguardsman.interview.content.ghoul.render.GhoulRenderer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
        modEventBus.addListener(InterviewClient::registerEntityRenderers);
        modEventBus.addListener(InterviewClient::registerEntityModels);
    }

    public static void registerScreens(FMLClientSetupEvent event)
    {
        MenuScreens.register(ModMenu.CHARGER.get(), ChargeCrafterScreen::new);
    }

    public static void registerEntityModels(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(GhoulModel.LAYER_LOCATION, GhoulModel::createBodyLayer);
    }

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers.RegisterRenderers event)
    {
        EntityRenderers.register(ModEntities.GHOUL.get(), GhoulRenderer::new);
    }
}
