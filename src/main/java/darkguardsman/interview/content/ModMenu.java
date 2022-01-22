package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.charger.gui.ChargeCrafterMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ModMenu
{
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, InterviewMod.ID);

    public static final RegistryObject<MenuType<ChargeCrafterMenu>> CHARGER = MENUS.register("charger",
            () -> IForgeMenuType.create((windowId, inv, data) -> new ChargeCrafterMenu(windowId, data.readBlockPos(), inv, inv.player)));
}
