package darkguardsman.interview.content.charger.gui;

import darkguardsman.interview.content.charger.InventoryChargeCrafter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

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
    public boolean mayPickup(Player playerIn)
    {
        return super.mayPickup(playerIn)
                && !mainInv.extractItem(InventoryChargeCrafter.SLOT_CHARGE_ITEM, 1, true).isEmpty()
                && !mainInv.extractItem(InventoryChargeCrafter.SLOT_FUEL_ITEM, 1, true).isEmpty();
    }

    @Override
    public boolean mayPlace(ItemStack p_39553_)
    {
        return false;
    }

    @Override
    public void onTake(Player player, ItemStack stack)
    {
        stack.onCraftedBy(player.level, player, stack.getCount());
        mainInv.extractItem(InventoryChargeCrafter.SLOT_CHARGE_ITEM, 1, false);
        mainInv.extractItem(InventoryChargeCrafter.SLOT_FUEL_ITEM, 1, false);
    }
}
