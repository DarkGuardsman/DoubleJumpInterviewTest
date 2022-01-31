package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.api.ChargeFuelReg;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Robin Seifert on 1/27/2022.
 */
@Mod.EventBusSubscriber(modid = InterviewMod.ID)
public class ModRecipes
{
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        ChargeFuelReg.addItem(ModItems.purpleMetal, 100);
        ChargeFuelReg.addItem(ModItems.redMetal, 30);
        ChargeFuelReg.addItem(ModItems.greenMetal, 5);
        ChargeFuelReg.addItem(ModItems.blueMetal, 1);
    }
}
