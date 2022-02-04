package darkguardsman.interview.content.reg;

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
public class ReceipeReg
{
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        ChargeFuelReg.addItem(ItemReg.purpleMetal, 100);
        ChargeFuelReg.addItem(ItemReg.redMetal, 30);
        ChargeFuelReg.addItem(ItemReg.greenMetal, 5);
        ChargeFuelReg.addItem(ItemReg.blueMetal, 1);
    }
}
