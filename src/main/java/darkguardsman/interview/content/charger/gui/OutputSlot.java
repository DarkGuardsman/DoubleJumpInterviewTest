package darkguardsman.interview.content.charger.gui;

import darkguardsman.interview.content.charger.InventoryChargeCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class OutputSlot extends SlotItemHandler
{
    private final IItemHandler mainInv;

    public OutputSlot(IItemHandler mainInv, IItemHandler fakeInv, int index, int xPosition, int yPosition)
    {
        super(fakeInv, index, xPosition, yPosition);
        this.mainInv = mainInv;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn)
    {
        return super.canTakeStack(playerIn)
                && !mainInv.extractItem(InventoryChargeCrafter.SLOT_CHARGE_ITEM, 1, true).isEmpty()
                && !mainInv.extractItem(InventoryChargeCrafter.SLOT_FUEL_ITEM, 1, true).isEmpty();
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack onTake(EntityPlayer player, ItemStack stack) {
        stack.onCrafting(player.world, player, stack.getCount());
        mainInv.extractItem(InventoryChargeCrafter.SLOT_CHARGE_ITEM, 1, false);
        mainInv.extractItem(InventoryChargeCrafter.SLOT_FUEL_ITEM, 1, false);
        return stack;
    }
}
