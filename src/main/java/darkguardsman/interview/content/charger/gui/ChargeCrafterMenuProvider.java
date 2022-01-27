package darkguardsman.interview.content.charger.gui;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.charger.TileEntityChargeCrafter;
import lombok.AllArgsConstructor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
@AllArgsConstructor
public class ChargeCrafterMenuProvider implements MenuProvider
{
    private final TileEntityChargeCrafter host;

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("screen." + InterviewMod.ID + ".charger");
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
        return new ChargeCrafterMenu(windowId, host.getBlockPos(), playerInventory, playerEntity);
    }
}
